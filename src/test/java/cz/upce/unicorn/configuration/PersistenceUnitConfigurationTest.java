package cz.upce.unicorn.configuration;

import cz.upce.unicorn.AbstractIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.util.Properties;


public class PersistenceUnitConfigurationTest extends AbstractIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    private Properties jpaProperties;

    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private Environment environment;

    @Test
    public void checkIfDataSourceIsInitialized() {
        Assert.assertNotNull(dataSource);
    }

    @Test
    public void checkIfJpaVendorIsInitalized() {
        Assert.assertNotNull(jpaVendorAdapter);
    }

    @Test
    public void checkIfJpaPropertiesAreInitialized() {
        Assert.assertNotNull(jpaProperties);
    }

    @Test
    public void checkIfAllJpaPropertiesAreLoaded() {
        Assert.assertEquals(4, jpaProperties.size());
    }

    @Test
    public void checkIfEntityManagerFactoryBeanIsInitialized() {
        Assert.assertNotNull(localContainerEntityManagerFactoryBean);
    }


    @Test
    public void checkIfTransactionManagerIsInitialized() {
        Assert.assertNotNull(platformTransactionManager);
    }
}