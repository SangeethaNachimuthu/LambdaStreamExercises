package lexicon;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static PersonRule isActive = Person::isActive;
    public static PersonRule isAdults = person -> person.getAge() >= 18;
    public static PersonRule livesInStockholm = person ->
            person.getCity().equalsIgnoreCase("Stockholm");
    public static PersonAction printNameAction = person -> System.out.println(person.getName());
    public static PersonAction sendEmailAction = person ->
            System.out.println("Send email to " + person.getName());
    public static PersonRule activeAndAdult = person ->
            (person.isActive() && person.getAge() >= 18);
    public static PersonRule adultOrLivesInStockholm = person ->
            (person.getAge() >= 18 || person.getCity().equalsIgnoreCase("Stockholm"));
    public static PersonRule notActive = person -> !person.isActive();


    void main() {

        List<Person> people = List.of(
                new Person("Amina", 22, "Stockholm", true),
                new Person("Erik", 17, "Uppsala", true),
                new Person("Noah", 34, "Stockholm", false),
                new Person("Sara", 29, "Gothenburg", true),
                new Person("Lina", 41, "Malmö", false),
                new Person("Omar", 19, "Stockholm", true)
        );

        printResult(people);

        System.out.println("\n----------------Results----------------");

        System.out.println("\nActive Persons: ");
        printResult(filterPeople(people, isActive));

        System.out.println("\nAdults: ");
        printResult(filterPeople(people, isAdults));

        System.out.println("\nPeople live in Stockholm: ");
        printResult(filterPeople(people, livesInStockholm));

        System.out.println("\nPeople Name: ");
        filterPeopleAndUpdate(people, isAdults, printNameAction);

        System.out.println("\nSend Email: ");
        filterPeopleAndUpdate(people, isAdults, sendEmailAction);

        System.out.println("\nActive And Adult: ");
        printResult(filterPeople(people, activeAndAdult));

        System.out.println("\nAdult or Lives in Stockholm: ");
        printResult(filterPeople(people, adultOrLivesInStockholm));

        System.out.println("\nNot Active Persons: ");
        printResult(filterPeople(people, notActive));
    }

    public static void printResult(List<Person> people) {
        for (Person p : people) {
            System.out.println(p);
        }
    }


    public static List<Person> filterPeople(List<Person> people, PersonRule rule) {

        List<Person> filteredPeople = new ArrayList<>();

        for (Person p : people) {
            if (rule.matches(p)) {
                filteredPeople.add(p);
            }
        }
        return filteredPeople;
    }

    public static void filterPeopleAndUpdate(List<Person> people, PersonRule rule, PersonAction action) {

        List<Person> filteredPeople = new ArrayList<>();

        for (Person p : people) {
            if (rule.matches(p)) {
                action.run(p);
                filteredPeople.add(p);
            }
        }
    }


}
