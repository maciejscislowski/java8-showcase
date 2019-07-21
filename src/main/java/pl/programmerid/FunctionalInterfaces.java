package pl.programmerid;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class FunctionalInterfaces {

    private List<Person> people = Arrays.asList(new Person("John", 12), new Person("Jack", 22));

    void predicates() {
        Predicate<Person> isOfAge = person -> person.getAge() >= 18;
        Predicate<Person> firstNameFirstLetterIsJ = person -> "J".equalsIgnoreCase(person.getFirstName());

        boolean firstIsOfAge = isOfAge.test(people.get(0));

        List<Person> peopleAreOfAge = people.stream().filter(isOfAge.and(firstNameFirstLetterIsJ)).collect(Collectors.toList());
    }

    void functions() {
        Function<Person, Person> nullObject = person -> Objects.isNull(person) ? new Person("", 0) : person;
        Function<Person, String> toUpperFirstName = person -> person.getFirstName().toUpperCase();

        List<String> upperFirstNamesOption1 = people.stream().map(nullObject).map(toUpperFirstName).collect(Collectors.toList());
        List<String> upperFirstNamesOption2 = people.stream().map(nullObject.andThen(toUpperFirstName)).collect(Collectors.toList());
    }

    void suppliersAndConsumers() {
        Supplier<Person> newNullPerson = () -> new Person("", 0);
        Consumer<Person> printFirstName = person -> System.out.println(person.getFirstName());

        Person person = Optional.ofNullable(people.get(0)).orElseGet(newNullPerson);

        people.forEach(printFirstName);
    }

    void biFunctions() {
        BiFunction<Person, Person, Integer> summaryAgeOfTwoPeople = (p1, p2) -> p1.getAge() + p2.getAge();

        int summaryAge = summaryAgeOfTwoPeople.apply(people.get(0), people.get(1));

        int summaryAgeOfPeople = people.stream().mapToInt(Person::getAge).reduce(0, Integer::sum);
    }

}
