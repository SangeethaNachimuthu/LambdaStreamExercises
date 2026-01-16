package lexicon;

@FunctionalInterface
public interface PersonRule {

    public boolean filterPeople(Person person);
}
