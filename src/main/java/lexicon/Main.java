package lexicon;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static PersonRule activePersons = Person::isActive;
    public static PersonRule adults = person -> person.getAge() >= 18;
    public static PersonRule livesInStockholm = person ->
            person.getCity().equalsIgnoreCase("Stockholm");
    public static PersonAction printName = person -> System.out.println(person.getName());
    public static PersonAction sendEmail = person ->
            System.out.println("Send email to " + person.getName());

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
        printResult(filterPeople(people, activePersons));

        System.out.println("\nAdults: ");
        printResult(filterPeople(people, adults));

        System.out.println("\nPeople live in Stockholm: ");
        printResult(filterPeople(people, livesInStockholm));

        System.out.println("\nPeople Name: ");
        filterPeopleAndUpdate(people, adults, printName);

        System.out.println("\nSend Email: ");
        filterPeopleAndUpdate(people, adults, sendEmail);
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
