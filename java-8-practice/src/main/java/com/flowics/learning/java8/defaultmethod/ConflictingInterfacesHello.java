/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.defaultmethod;

/**
 * Class that implements 2 interfaces that implement hello and conflict, so there is no remedy but to override
 * to solve
 *
 * @author Marcelo
 * @since Jul 30, 2015
 */
public class ConflictingInterfacesHello implements OverridingHelloInterface, ConflictingHelloInterface {
    @Override
    public String hello() {
        return OverridingHelloInterface.super.hello() + " " + ConflictingHelloInterface.super.hello();
    }
}
