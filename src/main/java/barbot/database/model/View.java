package barbot.database.model;

/**
 * Created by Naveen on 4/14/17.
 */
public class View {

    public interface Request {}

    public interface Response {}

    public interface Id {}

    public interface Summary extends Id {}

    public interface Detail extends Summary {}
}
