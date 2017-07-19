package barbot.event;

import barbot.database.model.Barbot;
import barbot.database.model.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 5/18/2017.
 */
public class BarbotEvent {
    private Barbot target;
    private Object message;
    private String eventType;
    private Class jsonView;

    public BarbotEvent() {
        this(null, new HashMap(), null, View.Summary.class);
    }

    public BarbotEvent(Barbot target, Object message, String eventType) {
        this(target, message, eventType, View.Summary.class);
    }

    public BarbotEvent(Barbot target, Object message, String eventType, Class jsonView) {
        this.target = target;
        this.message = message;
        this.jsonView = jsonView;
        this.eventType = eventType;
    }

    public Barbot getTarget() {
        return target;
    }

    public void setTarget(Barbot target) {
        this.target = target;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Class getJsonView() {
        return jsonView;
    }

    public void setJsonView(Class jsonView) {
        this.jsonView = jsonView;
    }
}
