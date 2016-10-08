/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.defaultmethod;

/**
 * Conflicting default interface
 *
 * @author Marcelo
 * @since Jul 29, 2015
 */
public interface ConflictingHelloInterface {
    default String hello() {
        return "Hello Conflict";
    }
}
