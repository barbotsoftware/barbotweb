package barbot.websocket.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.model.Category;
import barbot.database.model.Recipe;
import barbot.database.service.CategoryService;

/**
 * Created by Naveen on 9/17/17.
 */
public class GetCategoryTests extends CommandTests {

    @Mock
    private CategoryService categoryService;

    private Category category;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testExecute() {
        (Mockito.doReturn(category)).when(categoryService).findByUid(category.getUid());

        HashMap result = (HashMap) command.execute();

        Category resultCategory = (Category) result.get("category");

        assertThat(resultCategory).isNotNull();
        assertThat(resultCategory.getUid()).isEqualTo(category.getUid());
        assertThat(resultCategory.getId()).isEqualTo(category.getId());
        assertThat(resultCategory.getName()).isEqualTo(category.getName());
    }

    @Test
    public void testValidate() {
        (Mockito.doReturn(true)).when(fieldValidator).validate((HashMap)msg.get("data"), fieldsToValidate);

        boolean result = command.validate();

        assertThat(result).isTrue();
    }

    private void setUpTestData() {
        category = new Category();
        category.setName("category1");
        category.setId(1);
        category.setUid("category_000001");

        HashMap data = new HashMap<>();
        data.put("category_id", category.getUid());

        msg.put("data", data);

        fieldsToValidate.put("category_id", "required|exists:category");

        command = new GetCategory(categoryService, fieldValidator, helperMethods, msg);
    }
}
