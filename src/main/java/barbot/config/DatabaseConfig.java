package barbot.config;

import barbot.database.dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import javax.sql.DataSource;

import java.util.Properties;

/**
 * Created by alexh on 3/25/2017.
 */
@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.id.new_generator_mappings", "false");
        sessionFactory.setHibernateProperties(hibernateProperties);
        String[] pckage={"barbot"};
        sessionFactory.setPackagesToScan(pckage);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public MainDao mainDao() {
        MainDao mainDao = new MainDao();
        mainDao.setSessionFactory(sessionFactory().getObject());
        return mainDao;
    }

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setSessionFactory(sessionFactory().getObject());
        return userDao;
    }

    @Bean
    public DrinkOrderDao drinkOrderDao() {
        DrinkOrderDao drinkOrderDao = new DrinkOrderDao();
        drinkOrderDao.setSessionFactory(sessionFactory().getObject());
        return drinkOrderDao;
    }

    @Bean
    public IngredientDao ingredientDao() {
        IngredientDao ingredientDao = new IngredientDao();
        ingredientDao.setSessionFactory(sessionFactory().getObject());
        return ingredientDao;
    }

    @Bean
    public RecipeDao recipeDao() {
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.setSessionFactory(sessionFactory().getObject());
        return recipeDao;
    }

    @Bean
    public BarbotDao barbotDao() {
        BarbotDao barbotDao = new BarbotDao();
        barbotDao.setSessionFactory(sessionFactory().getObject());
        return barbotDao;
    }
}
