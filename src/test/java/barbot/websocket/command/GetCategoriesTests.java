package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.Category;
import barbot.database.service.CategoryService;

/**
 * Created by Naveen on 9/17/17.
 */
public class GetCategoriesTests extends CommandTests {

    @Mock
    private CategoryService categoryService;

    private List<Category> categories;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {

        (Mockito.doReturn(categories)).when(categoryService).findAllRoots();

        HashMap result = (HashMap) command.execute();

        List<Category> categoryList = (List<Category>) result.get("categories");

        assertThat(categoryList).isNotNull();
        assertThat(categoryList).isEqualTo(categories);
    }

    @Test
    public void testValidate() {
        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        HashMap data = new HashMap<>();

        msg.put("data", data);

        categories = testDataHelper.createCategoryList(listSize);

        command = new GetCategories(categoryService, fieldValidator, helperMethods, msg);
    }
}
