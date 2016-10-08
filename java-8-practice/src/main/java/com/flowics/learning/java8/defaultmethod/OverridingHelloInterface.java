/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.defaultmethod;

/**
 * Interfaz that overrides the default method
 *
 * @author Marcelo
 * @since Jul 29, 2015
 */
public interface OverridingHelloInterface extends BaseHelloInterfaz {
    @Override
    default String hello() {
        return "Override Hello";
    }
}
