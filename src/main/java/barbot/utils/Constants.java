package barbot.utils;

/**
 * Created by alexh on 4/6/2017.
 */
public class Constants {
    /* Web Socket JSON Keys */
    public static final String KEY_COMMAND = "command";
    public static final String KEY_RESULT = "result";
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_DATA = "data";
    public static final String KEY_ERROR = "error";


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

    /* Error Messages */
    public static final String ERROR_MSG_INVALID_COMMAND = "Invalid command";
    public static final String ERROR_MSG_PARSE_ERROR = "Failed to parse message";
    public static final String ERROR_MSG_CREATE_ERROR = "Failed to create message";
    public static final String ERROR_MSG_COMMAND_NOT_RECOGNIZED = "Command not recognized";
}
