package barbot.utils;

/**
 * Created by alexh on 4/6/2017.
 */
public class Constants {

    /* Database Schema Name */
    public static final String DB_SCHEMA = "barbotdb";

    /* Database Table Names */
    public static final String TABLE_BARBOT = "barbot";
    public static final String TABLE_BARBOT_CONTAINER = "barbot_container";
    public static final String TABLE_BARBOT_IO_DEVICE = "barbot_io_device";
    public static final String TABLE_BARBOT_IO_DEVICE_TYPE = "barbot_io_device_type";
    public static final String TABLE_BARBOT_PUMP = "barbot_pump";
    public static final String TABLE_DRINK_ORDER = "drink_order";
    public static final String TABLE_INGREDIENT = "ingredient";
    public static final String TABLE_MIGRATION = "migration";
    public static final String TABLE_RECIPE = "recipe";
    public static final String TABLE_RECIPE_INGREDIENT = "recipe_ingredient";
    public static final String TABLE_USER = "user";

    /* Web Socket JSON Keys */
    public static final String KEY_TYPE = "type";
    public static final String KEY_REQUEST = "request";
    public static final String KEY_RESPONSE = "response";
    public static final String KEY_COMMAND = "command";
    public static final String KEY_RESULT = "result";
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_DATA = "data";
    public static final String KEY_ERROR = "error";

    /* Web Socket JSON Data Keys */
    public static final String KEY_DATA_BARBOT_ID = "barbot_id";
    public static final String KEY_DATA_RECIPE_ID = "recipe_id";
    public static final String KEY_DATA_INGREDIENT_ID = "ingredient_id";
    public static final String KEY_DATA_DRINK_ORDER_ID = "drink_order_id";
    public static final String KEY_DATA_ICE = "ice";
    public static final String KEY_DATA_GARNISH = "garnish";

    /* Web Socket Commands */
    public static final String CMD_GET_RECIPES_FOR_BARBOT = "get_recipes_for_barbot";
    public static final String CMD_GET_RECIPE_DETAILS = "get_recipe_details";
    public static final String CMD_GET_INGREDIENTS_FOR_BARBOT = "get_ingredients_for_barbot";
    public static final String CMD_CREATE_CUSTOM_DRINK = "create_custom_drink";
    public static final String CMD_ORDER_DRINK = "order_drink";
    public static final String CMD_POUR_DRINK = "pour_drink";
    public static final String CMD_AUTHENTICATE = "authenticate";

    /* Error Codes */
    public static final int ERROR_INVALID_COMMAND = 1;
    public static final int ERROR_PARSE_ERROR = 2;
    public static final int ERROR_CREATE_ERROR = 3;
    public static final int ERROR_COMMAND_NOT_RECOGNIZED = 4;
    public static final int ERROR_NOT_AUTHENTICATED = 5;

    /* Error Messages */
    public static final String ERROR_MSG_INVALID_COMMAND = "Invalid command";
    public static final String ERROR_MSG_PARSE_ERROR = "Failed to parse message";
    public static final String ERROR_MSG_CREATE_ERROR = "Failed to create message";
    public static final String ERROR_MSG_COMMAND_NOT_RECOGNIZED = "Command not recognized";
    public static final String ERROR_MSG_NOT_AUTHENTICATED = "User is not authenticated";

    /* Unique ID Constants */
    public static final String USER_UID_PREFIX = "user_";
    public static final int UID_LENGTH = 6;
}
