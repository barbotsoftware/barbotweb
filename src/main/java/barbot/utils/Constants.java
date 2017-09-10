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
    public static final String TABLE_DRINK_ORDER = "drink_order";
    public static final String TABLE_EMAIL_CAPTURE = "email_capture";
    public static final String TABLE_INGREDIENT = "ingredient";
    public static final String TABLE_MIGRATION = "migration";
    public static final String TABLE_RECIPE = "recipe";
    public static final String TABLE_RECIPE_INGREDIENT = "recipe_ingredient";
    public static final String TABLE_USER = "user";
    public static final String TABLE_RECIPE_CATEGORY = "recipe_category";

    /* Web Socket JSON Keys */
    public static final String KEY_REQUEST = "request";
    public static final String KEY_RESPONSE = "response";
    public static final String KEY_COMMAND = "command";
    public static final String KEY_RESULT = "result";
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_DATA = "data";
    public static final String KEY_ERROR = "error";
    public static final String KEY_MESSAGE_TYPE = "type";
    public static final String KEY_COMMAND_RESPONSE = "command_response";
    public static final String KEY_EVENT = "event";

    /* Web Socket JSON Data Keys */
    public static final String KEY_DATA_BARBOT_ID = "barbot_id";
    public static final String KEY_DATA_RECIPE_ID = "recipe_id";
    public static final String KEY_DATA_INGREDIENT_ID = "ingredient_id";
    public static final String KEY_DATA_DRINK_ORDER_ID = "drink_order_id";
    public static final String KEY_DATA_ICE = "ice";
    public static final String KEY_DATA_GARNISH = "garnish";
    public static final String KEY_DATA_BARBOT_CONTAINERS = "barbot_containers";
    public static final String KEY_DATA_NUMBER = "number";
    public static final String KEY_DATA_CURRENT_VOLUME = "current_volume";
    public static final String KEY_DATA_MAX_VOLUME = "max_volume";
    public static final String KEY_DATA_CATEGORY_ID = "category_id";

    /* Web Socket Commands */
    public static final String CMD_GET_RECIPES_FOR_BARBOT = "get_recipes_for_barbot";
    public static final String CMD_GET_RECIPE_DETAILS = "get_recipe_details";
    public static final String CMD_GET_INGREDIENTS_FOR_BARBOT = "get_ingredients_for_barbot";
    public static final String CMD_CREATE_CUSTOM_RECIPE = "create_custom_recipe";
    public static final String CMD_ORDER_DRINK = "order_drink";
    public static final String CMD_POUR_DRINK = "pour_drink";
    public static final String CMD_GET_CONTAINERS_FOR_BARBOT = "get_containers_for_barbot";
    public static final String CMD_SET_CONTAINERS_FOR_BARBOT = "set_containers_for_barbot";
    public static final String CMD_GET_CATEGORIES = "get_categories";
    public static final String CMD_GET_CATEGORY = "get_category";

    /* Web Socket Events */
    public static final String EVENT_DRINK_ORDERED = "drink_ordered";

    /* Error Messages */
    public static final String ERROR_MSG_PREFIX = "errorMsg.";
    public static final String ERROR_MSG_INVALID_COMMAND = "invalidCommand";
    public static final String ERROR_MSG_PARSE_ERROR = "parseError";
    public static final String ERROR_MSG_CREATE_ERROR = "createError";
    public static final String ERROR_MSG_COMMAND_NOT_RECOGNIZED = "commandNotRecognized";
    public static final String ERROR_MSG_NOT_AUTHENTICATED = "notAuthenticated";
    public static final String ERROR_MSG_EXECUTION_FAILED = "executionFailed";

    /* Unique ID Constants */
    public static final String USER_UID_PREFIX = "user_";
    public static final String RECIPE_UID_PREFIX = "recipe_";
    public static final String DRINK_ORDER_UID_PREFIX = "drinkOrder_";
    public static final int UID_LENGTH = 6;

    /* Misc */
    public static final String CUSTOM_IMG_URL = "dickbutt.png";
}
