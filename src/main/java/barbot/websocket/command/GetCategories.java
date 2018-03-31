package barbot.websocket.command;

import barbot.database.model.View;
import barbot.database.service.CategoryService;
import barbot.utils.FieldValidator;
import barbot.utils.HelperMethods;

import java.util.HashMap;

/**
 * Created by Alex on 9/10/2017.
 */
public class GetCategories extends BarbotCommand {

    private CategoryService categoryService;

    public GetCategories(CategoryService categoryService, FieldValidator validator, HelperMethods hlpr, HashMap msg) {
        super(msg, hlpr, validator);
        setJsonView(View.Detail.class);
        this.categoryService = categoryService;
    }

    @Override
    public Object execute() {
        HashMap result = new HashMap();
        result.put("categories", categoryService.findAllRoots());
        return result;
    }

    @Override
    public boolean validate() {
        return true;
    }

}
