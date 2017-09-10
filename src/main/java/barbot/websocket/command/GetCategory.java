package barbot.websocket.command;

import barbot.database.model.View;
import barbot.database.service.CategoryService;
import barbot.utils.Constants;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

import java.util.HashMap;

/**
 * Created by Alex on 9/10/2017.
 */
public class GetCategory extends BarbotCommand {
    private CategoryService categoryService;

    public GetCategory(CategoryService categoryService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);
        setJsonView(View.Detail.class);
        this.categoryService = categoryService;
    }

    @Override
    public Object execute() {
        // Get Category ID from request
        String categoryId = (String) data.get(Constants.KEY_DATA_CATEGORY_ID);

        HashMap result = new HashMap();
        result.put("category", categoryService.findByUid(categoryId));
        return result;
    }

    @Override
    public boolean validate() {
        if(!super.validate())
            return false;

        HashMap fieldsToValidate = new HashMap();
        fieldsToValidate.put(Constants.KEY_DATA_CATEGORY_ID, "required|exists:category");

        if(!fieldValidator.validate((HashMap)message.get(Constants.KEY_DATA), fieldsToValidate)) {
            error = fieldValidator.getErrors();
            return false;
        }

        return true;
    }
}
