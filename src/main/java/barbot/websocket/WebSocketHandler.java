package barbot.websocket;

import barbot.utils.Constants;
import barbot.websocket.command.GetRecipesForBarbot;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
            sendError(session, "Failed to parse message");
            e.printStackTrace();
        }

        if(msg.containsKey(Constants.KEY_COMMAND)) {
            switch(msg.get(Constants.KEY_COMMAND).toString()) {
                case Constants.CMD_GET_RECIPES_FOR_BARBOT:
                    GetRecipesForBarbot getRecipesForBarbot = new GetRecipesForBarbot(msg);
                    if(getRecipesForBarbot.validate()) {
                        sendMessage(session, getRecipesForBarbot.execute());
                    } else {
                        sendError(session, "Invalid command parameters");
                    }
                    break;
                default:
                    sendMessage(session, "Command not recognized");
                    break;
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessionMap.remove(session.getId());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionMap.put(session.getId(), session);
    }

    private void sendMessage(WebSocketSession session, Object response) {
        HashMap responseMap = new HashMap();
        responseMap.put(Constants.KEY_RESULT, Constants.KEY_SUCCESS);
        responseMap.put(Constants.KEY_DATA, response);
        try {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(responseMap)));
        } catch (IOException e) {
            sendError(session, "Failed to create message");
            e.printStackTrace();
        }
    }

    private void sendError(WebSocketSession session, String error) {
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
