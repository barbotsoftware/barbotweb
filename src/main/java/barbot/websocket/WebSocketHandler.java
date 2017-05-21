package barbot.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import barbot.database.model.Barbot;
import barbot.database.model.View;
import barbot.event.BarbotEvent;
import barbot.utils.HelperMethods;
import barbot.websocket.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import barbot.database.model.User;
import barbot.utils.Constants;

/**
 * Created by alexh on 4/6/2017.
 */

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    CommandFactory commandFactory;

    @Autowired
    HelperMethods hlpr;

    private Map<String, WebSocketSession> sessionMap;

    private ObjectMapper mapper;

    public WebSocketHandler() {
        mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        mapper.setConfig(mapper.getSerializationConfig().withView(View.Detail.class).withView(View.Summary.class).withView(View.Id.class));
        mapper.registerModule(new Hibernate5Module());
        sessionMap = new HashMap<>();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        if(!isAuthenticated(session)) {
            sendError(session, Constants.ERROR_MSG_NOT_AUTHENTICATED);
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        HashMap<String, Object> msg = new HashMap();
        try {
            msg = mapper.readValue(message.getPayload(), new TypeReference<HashMap<String,Object>>(){});
        } catch (IOException e) {
            sendError(session, Constants.ERROR_MSG_PARSE_ERROR);
            e.printStackTrace();
        }

        if(msg.containsKey(Constants.KEY_COMMAND)) {
            Command command = getCommand(msg, session);

            try {
                if (command.validate()) {
                    sendMessage(session, command.execute(), command.getJsonView());
                } else {
                    sendError(session, command.getError());
                }
            } catch (Exception e) {
                sendError(session, Constants.ERROR_MSG_EXECUTION_FAILED);
            }
        }
    }

    private Command getCommand(HashMap<String, Object> msg, WebSocketSession session) {
        Command command;

        switch(msg.get(Constants.KEY_COMMAND).toString()) {
            case Constants.CMD_GET_RECIPES_FOR_BARBOT:
                command = commandFactory.create(GetRecipesForBarbot.class, msg);
                break;
            case Constants.CMD_GET_RECIPE_DETAILS:
                command = commandFactory.create(GetRecipeDetails.class, msg);
                break;
            case Constants.CMD_GET_INGREDIENTS_FOR_BARBOT:
                command = commandFactory.create(GetIngredientsForBarbot.class, msg);
                break;
            case Constants.CMD_CREATE_CUSTOM_RECIPE:
                command = commandFactory.create(CreateCustomRecipe.class, msg, getUser(session));
                break;
            case Constants.CMD_ORDER_DRINK:
                command = commandFactory.create(OrderDrink.class, msg, getUser(session));
                break;
            case Constants.CMD_POUR_DRINK:
                command = commandFactory.create(PourDrink.class, msg);
                break;
            default:
                command = new BaseCommand(msg, hlpr);
                sendError(session, Constants.ERROR_MSG_COMMAND_NOT_RECOGNIZED);
                break;
        }

        return command;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessionMap.remove(session.getId());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionMap.put(session.getId(), session);
    }

    @EventListener
    public void handleBarbotEvent(BarbotEvent event) {
        WebSocketSession session = getSessionForBarbot(event.getTarget());

        if(session != null) {
            HashMap message = new HashMap();
            message.put(Constants.KEY_MESSAGE_TYPE, Constants.KEY_EVENT);
            message.put(Constants.KEY_EVENT, event.getEventType());
            message.put(Constants.KEY_DATA, event.getMessage());
            try {
                session.sendMessage(new TextMessage(mapper.writerWithView(event.getJsonView()).writeValueAsString(message)));
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                sendError(session, Constants.ERROR_MSG_CREATE_ERROR);
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(WebSocketSession session, Object message, Class<?> jsonView) {
        HashMap responseMap = new HashMap();
        responseMap.put(Constants.KEY_MESSAGE_TYPE, Constants.KEY_COMMAND_RESPONSE);
        responseMap.put(Constants.KEY_RESULT, Constants.KEY_SUCCESS);
        responseMap.put(Constants.KEY_DATA, message);
        try {
            session.sendMessage(new TextMessage(mapper.writerWithView(jsonView).writeValueAsString(responseMap)));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            sendError(session, Constants.ERROR_MSG_CREATE_ERROR);
            e.printStackTrace();
        }
    }

    private void sendError(WebSocketSession session, String errorCode) {
        Map errorMap = new HashMap();
        errorMap.put(errorCode, hlpr.getMessage(Constants.ERROR_MSG_PREFIX + errorCode));
        sendError(session, errorMap);
    }

    private void sendError(WebSocketSession session, Map error) {
        try {
            HashMap responseMap = new HashMap();
            responseMap.put(Constants.KEY_RESULT, Constants.KEY_ERROR);
            responseMap.put(Constants.KEY_DATA, error);
            session.sendMessage(new TextMessage(mapper.writeValueAsString(responseMap)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isAuthenticated(WebSocketSession session) {
        SecurityContext securityContext = (SecurityContext) session.getAttributes().get("SPRING_SECURITY_CONTEXT");
        return securityContext != null && securityContext.getAuthentication() != null
                && securityContext.getAuthentication().isAuthenticated();
    }

    private User getUser(WebSocketSession session) {
        SecurityContext securityContext = (SecurityContext) session.getAttributes().get("SPRING_SECURITY_CONTEXT");
        return (User) securityContext.getAuthentication().getPrincipal();
    }

    private WebSocketSession getSessionForBarbot(Barbot barbot) {
        for(WebSocketSession session : sessionMap.values()) {
            if(barbot.equals(session.getAttributes().get("barbot"))) {
                return session;
            }
        }

        return null;
    }

}
