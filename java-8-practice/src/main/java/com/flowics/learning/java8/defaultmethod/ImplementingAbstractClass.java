/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.defaultmethod;

/**
 * Class that implements hello and it is not related to {@link BaseHelloInterfaz} (so a children can conflict
 * and resolve)
 *
 * @author Marcelo
 * @since Jul 30, 2015
 */
public abstract class ImplementingAbstractClass {
    public String hello() {
        return "Hello Class";
    }
}
