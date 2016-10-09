/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.flowics.learning.java8.model.Car;
import com.flowics.learning.java8.model.Car.Type;
import com.flowics.learning.java8.model.Person;
import com.flowics.learning.java8.model.Person.Sex;

/**
 * Class to play with streams... the idea is to "pipelineize" the non java 8 actions of each test
 *
 * @author Marcelo
 * @since Aug 6, 2015
 */
public class StreamIntegrationTest {
    private List<Person> roster;

    @Before
    public final void before() {

        roster = Arrays.asList(new Person("jack", LocalDate.of(1999, 1, 1), Sex.MALE, "j@mail.com"),
                               new Person("danielle", LocalDate.of(1992, 12, 1), Sex.FEMALE, "d@mail.com"),
                               new Person("livy", LocalDate.of(1989, 5, 12), Sex.FEMALE, "l@mail.com"),
                               new Person("mark", LocalDate.of(1993, 1, 10), Sex.MALE, "m@mail.com"),
                               new Person("anna", LocalDate.of(1985, 3, 11), Sex.FEMALE, "a@mail.com"),
                               new Person("bree", LocalDate.of(1985, 5, 6), Sex.FEMALE, "b@mail.com"));
        new Car(roster.get(0), Type.TOWNCAR, "i-1");
        new Car(roster.get(1), Type.TOWNCAR, "i-2");
        new Car(roster.get(2), Type.PICKUP, "i-3");
        new Car(roster.get(3), Type.PICKUP, "i-4");
        new Car(roster.get(4), Type.PICKUP);
    }

    @Test
    public final void should_get_only_males() {
        final List<String> names = new ArrayList<>();
        roster.stream().filter(p -> p.getGender() == Sex.MALE).forEach(p -> names.add(p.getName()));
        assertEquals(Arrays.asList("jack", "mark"), names);
    }

    @Test
    public final void should_count_by_type() {
        final Map<Car.Type, List<Car>> countByType = roster.stream()
                                                           .filter(p -> p.getAge() > 18)
                                                           .filter(p -> p.getCar() != null)
                                                           .filter(p -> p.getCar().getInsuranceId() != null)
                                                           .map(p -> p.getCar())
                                                           .collect(Collectors.groupingBy(c -> c.getType()));

        assertEquals(new Integer(1), java.util.Optional.ofNullable(countByType.get(Type.TOWNCAR).size()).get());
        assertEquals(new Integer(2), java.util.Optional.ofNullable(countByType.get(Type.PICKUP).size()).get());
    }
}
