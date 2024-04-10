package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link MainController}.
 */
@Generated
public class MainController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'mainController'.
   */
  private static BeanInstanceSupplier<MainController> getMainControllerInstanceSupplier() {
    return BeanInstanceSupplier.<MainController>forConstructor(UserService.class)
            .withGenerator((registeredBean, args) -> new MainController(args.get(0)));
  }

  /**
   * Get the bean definition for 'mainController'.
   */
  public static BeanDefinition getMainControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MainController.class);
    beanDefinition.setInstanceSupplier(getMainControllerInstanceSupplier());
    return beanDefinition;
  }
}
