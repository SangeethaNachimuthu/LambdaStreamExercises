package lexicon;

@FunctionalInterface
public interface PersonRule {

    public boolean matches(Person person);
}
