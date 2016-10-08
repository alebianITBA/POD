/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.lambda;

import java.util.concurrent.Callable;

/**
 * the lexical scope of the {@link #r1} lambda
 * 
 * @author Marcelo
 * @since Jul 29, 2015
 */
public class LambdaScopingHelloWorld {
    Callable<String> r1 = () -> {
        // if we were using inner classes this would be the inner class
        return this.toString();
    };

    public String toString() {
        return "Hello, world!";
    }
}
