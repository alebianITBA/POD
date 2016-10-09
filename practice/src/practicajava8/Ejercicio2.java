package practicajava8;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Ejercicio2 {
    public static void main(String[] args) {
        Collection<Country> countries = Arrays.asList(
                new Country("Argentina" , Continent.AMERICA, 43590400  , LocalDate.parse("1816-07-09")),
                new Country("China"     , Continent.ASIA   , 1377939144, null),
                new Country("Brazil"    , Continent.AMERICA, 206284591 , LocalDate.parse("1822-09-07")),
                new Country("Nigeria"   , Continent.AFRICA , 189636000 , LocalDate.parse("1960-10-01")),
                new Country("Japón"     , Continent.ASIA   , 126675000 , null),
                new Country("México"    , Continent.AMERICA, 122273000 , LocalDate.parse("1810-09-16")),
                new Country("Turquía"   , Continent.ASIA   , 79265000  , LocalDate.parse("1923-10-29")),
                new Country("Australia" , Continent.OCEANIA, 24117000  , null),
                new Country("Chile"     , Continent.AMERICA, 18192000  , LocalDate.parse("1810-09-18")),
                new Country("Bélgica"   , Continent.EUROPE , 11338000  , LocalDate.parse("1930-10-04")),
                new Country("Portugal"  , Continent.EUROPE , 10262000  , LocalDate.parse("1640-12-01")),
                new Country("Bolivia"   , Continent.AMERICA, 10985000  , LocalDate.parse("1825-08-06")),
                new Country("Suecia"    , Continent.EUROPE , 9824000   , LocalDate.parse("1523-06-06")),
                new Country("Israel"    , Continent.ASIA   , 8391000   , LocalDate.parse("1948-05-15")),
                new Country("Inglaterra", Continent.EUROPE , 53010000  , null),
                new Country("Serbia"    , Continent.EUROPE , 7071000   , LocalDate.parse("1804-02-15"))
        );
        // Print all countries
        countries.forEach(c -> System.out.println(c));
        System.out.println("\n--------------------------------------------------------------\n");
        // Print America's countries
        countries.stream()
                 .filter(c -> c.getContinent() == Continent.AMERICA)
                 .forEach(c -> System.out.println(c));
        System.out.println("\n--------------------------------------------------------------\n");
        // List America's countries
        List<Country> america = countries.stream()
                                         .filter(c -> c.getContinent() == Continent.AMERICA)
                                         .collect(Collectors.toList());
        System.out.println(america);
        System.out.println("\n--------------------------------------------------------------\n");
        // Set of countries with letter a
        Set<Country> countriesWithA = countries.stream()
                                               .filter(c -> c.getName().toLowerCase().contains("a"))
                                               .collect(Collectors.toSet());
        System.out.println(countriesWithA);
        System.out.println("\n--------------------------------------------------------------\n");
        // Map of continents and list of countries
        Map<Continent, List<Country>> countriesByContinent = countries.stream()
                                                                       .collect(Collectors.groupingBy(Country::getContinent));
        System.out.println(countriesByContinent);
        System.out.println("\n--------------------------------------------------------------\n");
        // Map of continents and list of number of habitants
        Map<Continent, Long> countriesWithPopulation = countries.stream()
                                                                .collect(Collectors.groupingBy(Country::getContinent,
                                                                         Collectors.summingLong(Country::getPopulation)));
        System.out.println(countriesWithPopulation);
        System.out.println("\n--------------------------------------------------------------\n");
        // Map of continents and list of habitants average
        Map<Continent, Double> countriesWithAveragePopulation = countries.stream()
                                                                         .collect(Collectors.groupingBy(Country::getContinent,
                                                                                  Collectors.averagingLong(Country::getPopulation)));
        System.out.println(countriesWithAveragePopulation);
        System.out.println("\n--------------------------------------------------------------\n");
        // Find a country with population larger than 1.000.000.000 and a in its name
        Country specialCountry = countries.stream()
                                           .filter(c -> c.getName().toLowerCase().contains("a"))
                                           .filter(c -> c.getPopulation() > 1000000000)
                                           .findAny()
                                           .orElse(null);
        System.out.println(specialCountry);
        System.out.println("\n--------------------------------------------------------------\n");
        // Country with largest population
        Country mostPopulated = countries.stream()
                                         .max(Comparator.comparing(Country::getPopulation)).get();
        System.out.println(mostPopulated);
        System.out.println("\n--------------------------------------------------------------\n");
        // Countries wich independence day was multiple of 4
        List<Country> weirdIndependence = countries.stream()
                                                   .filter(c -> c.getIndependenceDay().isPresent() &&
                                                                c.getIndependenceDay().get().isLeapYear())
                                                   .collect(Collectors.toList());
        System.out.println(weirdIndependence);
        System.out.println("\n--------------------------------------------------------------\n");
        // Map with centuries and a list of countries that independized
        Map<Integer, Long> centuriesAndIndependance = countries.stream()
                                                               .map(Country::getIndependenceDay)
                                                               .filter(Optional::isPresent)
                                                               .map(Optional::get)
                                                               .collect(Collectors.groupingBy(d -> d.getYear() - (d.getYear() % 100),
                                                                        Collectors.counting()));
        System.out.println(centuriesAndIndependance);
    }
}
