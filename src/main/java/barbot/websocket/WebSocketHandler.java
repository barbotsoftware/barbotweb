package barbot.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import barbot.utils.Constants;
import barbot.websocket.command.BaseCommand;
import barbot.websocket.command.Command;
import barbot.websocket.command.CreateCustomDrink;
import barbot.websocket.command.GetIngredientsForBarbot;
import barbot.websocket.command.GetRecipeDetails;
import barbot.websocket.command.GetRecipesForBarbot;
import barbot.websocket.command.OrderDrink;
import barbot.websocket.command.PourDrink;

/**
 * Created by alexh on 4/6/2017.
 */
public class WebSocketHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessionMap;

    private ObjectMapper mapper;

    public WebSocketHandler() {
        mapper = new ObjectMapper();
        sessionMap = new HashMap<>();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        HashMap<String, Object> msg = new HashMap();
        try {
            msg = mapper.readValue(message.getPayload(), new TypeReference<HashMap<String,Object>>(){});
        } catch (IOException e) {
            sendError(session, Constants.ERROR_PARSE_ERROR, Constants.ERROR_MSG_PARSE_ERROR);
            e.printStackTrace();
        }

        if(msg.containsKey(Constants.KEY_COMMAND)) {
            Command command = getCommand(msg, session);

            if(command.validate()) {
                sendMessage(session, command.execute());
            } else {
                sendError(session, command.getError());
            }
        }
    }

    private Command getCommand(HashMap<String, Object> msg, WebSocketSession session) {
        Command command;
        switch(msg.get(Constants.KEY_COMMAND).toString()) {
            case Constants.CMD_GET_RECIPES_FOR_BARBOT:
                command = new GetRecipesForBarbot(msg);
                break;
            case Constants.CMD_GET_RECIPE_DETAILS:
                command = new GetRecipeDetails(msg);
                break;
            case Constants.CMD_GET_INGREDIENTS_FOR_BARBOT:
                command = new GetIngredientsForBarbot(msg);
                break;
            case Constants.CMD_CREATE_CUSTOM_DRINK:
                command = new CreateCustomDrink(msg);
                break;
            case Constants.CMD_ORDER_DRINK:
                command = new OrderDrink(msg);
                break;
            case Constants.CMD_POUR_DRINK:
                command = new PourDrink(msg);
                break;
            default:
                command = new BaseCommand(msg);
                sendError(session, Constants.ERROR_COMMAND_NOT_RECOGNIZED, Constants.ERROR_MSG_COMMAND_NOT_RECOGNIZED);
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

    private void sendMessage(WebSocketSession session, Object message) {
        HashMap responseMap = new HashMap();
        responseMap.put(Constants.KEY_RESULT, Constants.KEY_SUCCESS);
        responseMap.put(Constants.KEY_DATA, message);
        try {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(responseMap)));
        } catch (IOException e) {
            sendError(session, Constants.ERROR_CREATE_ERROR, Constants.ERROR_MSG_CREATE_ERROR);
            e.printStackTrace();
        }
    }

    private void sendError(WebSocketSession session, int errorCode, String error) {
        Map errorMap = new HashMap();
        errorMap.put(errorCode, error);
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

}