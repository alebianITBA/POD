package practicajava8;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Main {
    private static Consumer printer = (s) -> System.out.println(s);

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(i -> people.add(new Person("Test name " + i, i * 15)));
        int totalAges = people.stream().map(Person::getAge).reduce(0, (a, b) -> a + b);
        printer.accept("Total ages: " + totalAges);

        Map<Integer, Person> map = new HashMap<>();
        people.forEach(p -> map.put(p.getAge(), p));

        Optional<Integer> maxAge = people.stream().map(Person::getAge).max(Comparator.naturalOrder());
        IntStream.rangeClosed(1, maxAge.orElse(200)).forEach(i -> {
            // Person person = Optional.ofNullable(map.get(i)).orElse(Person.nullPerson());
            // printer.accept(person);
            Optional<Person> person = Optional.ofNullable(map.get(i));
            person.ifPresent(p -> printer.accept(p));
        });
    }
}
