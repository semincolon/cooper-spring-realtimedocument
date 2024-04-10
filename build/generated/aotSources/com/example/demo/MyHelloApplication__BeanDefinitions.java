package com.example.demo;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link MyHelloApplication}.
 */
@Generated
public class MyHelloApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'myHelloApplication'.
   */
  public static BeanDefinition getMyHelloApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MyHelloApplication.class);
    beanDefinition.setTargetType(MyHelloApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(MyHelloApplication.class);
    InstanceSupplier<MyHelloApplication> instanceSupplier = InstanceSupplier.using(MyHelloApplication$$SpringCGLIB$$0::new);
    instanceSupplier = instanceSupplier.andThen(MyHelloApplication__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
