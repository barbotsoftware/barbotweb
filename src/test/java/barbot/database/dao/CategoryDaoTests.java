package barbot.database.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import barbot.config.TestDatabaseConfig;
import barbot.database.model.Category;

/**
 * Created by Naveen on 9/16/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class CategoryDaoTests extends BaseDaoTests {

    private CategoryDao categoryDao;

    private List<Category> categoryList;

    private List<Category> rootCategoryList;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        categoryDao = new CategoryDao();
        categoryDao.setHibernateTemplate(mockTemplate);

        setUpTestData();
    }

    @Test
    public void testFindAll() {
        (Mockito.doReturn(categoryList).when(mockTemplate))
                .find("FROM Category");

        List<Category> result = categoryDao.findAll();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(listSize);
    }

    @Test
    public void testFindAllRoots() {
        (Mockito.doReturn(rootCategoryList).when(mockTemplate))
                .find("FROM Category WHERE parent_category_id = NULL");

        List<Category> result = categoryDao.findAllRoots();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(rootCategoryList.size());
    }

    @Test
    public void testFindByUid() {
        String uid = categoryList.get(0).getUid();

        (Mockito.doReturn(categoryList).when(mockTemplate))
                .find("FROM Category WHERE uid = ?", uid);

        Category result = categoryDao.findByUid(uid);

        assertThat(result).isNotNull();
        assertThat(result.getUid()).isEqualTo(uid);
    }

    private void setUpTestData() {
        categoryList = testDataHelper.createCategoryList(listSize);
        rootCategoryList = testDataHelper.createRootCategoryList(categoryList);
    }
}
