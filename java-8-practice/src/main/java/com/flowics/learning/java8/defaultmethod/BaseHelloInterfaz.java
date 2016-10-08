/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.defaultmethod;

/**
 * Base interfaz to test default method.
 *
 * @author Marcelo
 * @since Jul 29, 2015
 */
public interface BaseHelloInterfaz {
    default String hello() {
        return "Hello Base";
    }
}
