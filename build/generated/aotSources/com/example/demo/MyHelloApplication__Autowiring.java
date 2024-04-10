package com.example.demo;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link MyHelloApplication}.
 */
@Generated
public class MyHelloApplication__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static MyHelloApplication apply(RegisteredBean registeredBean,
      MyHelloApplication instance) {
    instance.userRepository = AutowiredFieldValueResolver.forRequiredField("userRepository").resolve(registeredBean);
    return instance;
  }
}
