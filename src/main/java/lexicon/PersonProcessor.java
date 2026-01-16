package lexicon;

import java.util.ArrayList;
import java.util.List;

public class PersonProcessor {

    public static List<Person> findPeople(List<Person> people, PersonRule rule) {

        List<Person> filteredPeople = new ArrayList<>();

        for (Person p : people) {
            if (rule.matches(p)) {
                filteredPeople.add(p);
            }
        }
        return filteredPeople;
    }

    public static List<Person> applyToMatching(List<Person> people, PersonRule rule, PersonAction action) {

        List<Person> filteredPeople = new ArrayList<>();

        for (Person p : people) {
            if (rule.matches(p)) {
                action.run(p);
                filteredPeople.add(p);
            }
        }
        return filteredPeople;
    }
}
