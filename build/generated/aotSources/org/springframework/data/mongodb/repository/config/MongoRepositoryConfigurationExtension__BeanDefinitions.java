package org.springframework.data.mongodb.repository.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link MongoRepositoryConfigurationExtension}.
 */
@Generated
public class MongoRepositoryConfigurationExtension__BeanDefinitions {
  /**
   * Get the bean definition for 'mongoRepositoryConfigurationExtension#0'.
   */
  public static BeanDefinition getMongoRepositoryConfigurationExtensionBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MongoRepositoryConfigurationExtension.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setLazyInit(true);
    beanDefinition.setInstanceSupplier(MongoRepositoryConfigurationExtension::new);
    return beanDefinition;
  }
}
