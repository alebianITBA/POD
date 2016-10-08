/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.functional;

/**
 * Interface with the {@link FunctionalInterface} annotation
 * 
 * @author Marcelo
 * @since Jul 29, 2015
 */
@FunctionalInterface
public interface NonFunctionalInterface {

    void methodA();

    // Uncomment and the class will not compile
    // String offenderMethodB();

    // seems that object methods are not counted as offending
    boolean equals(Object obj);

    String toString();
}
