/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.defaultmethod;

/**
 * Interface that overrides the {@link BaseHelloInterfaz#hello()} to make it {@link AbstractingHelloInterface}
 *
 * @author Marcelo
 * @since Jul 30, 2015
 */
public interface AbstractingHelloInterface extends BaseHelloInterfaz {
    @Override
    String hello();
}
