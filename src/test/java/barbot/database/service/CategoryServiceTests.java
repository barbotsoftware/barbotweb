package barbot.database.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import barbot.database.dao.CategoryDao;
import barbot.database.model.Category;

/**
 * Created by Naveen on 9/16/17.
 */
public class CategoryServiceTests extends BaseServiceTests {

    @InjectMocks
    CategoryService categoryService = new CategoryServiceImpl();

    @Mock
    CategoryDao categoryDao;

    private List<Category> categories;

    private List<Category> rootCategories;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        setUpTestData();
    }

    @Test
    public void testFindAll() {
        (Mockito.doReturn(categories)).when(categoryDao).findAll();

        List<Category> results = categoryService.findAll();

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(listSize);
        assertThat(results.get(0)).isEqualTo(categories.get(0));
    }

    @Test
    public void testFindAllRoots() {
        (Mockito.doReturn(rootCategories)).when(categoryDao).findAllRoots();

        List<Category> results = categoryService.findAllRoots();

        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(rootCategories.size());
        assertThat(results.get(0)).isEqualTo(rootCategories.get(0));
    }

    @Test
    public void testFindByUid() {
        Category recipe = categories.get(0);
        String uid = recipe.getUid();

        (Mockito.doReturn(recipe)).when(categoryDao).findByUid(uid);

        Category result = categoryService.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    private void setUpTestData() {
        categories = testDataHelper.createCategoryList(listSize);
        rootCategories = testDataHelper.createRootCategoryList(categories);
    }
}
