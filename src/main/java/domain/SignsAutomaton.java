package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SignsAutomaton implements Automaton {

    private Set<String> signs;

    private Priority priority;

    public SignsAutomaton(Set<String> signs, Priority priority) {
        this.signs = signs;
        this.priority = priority;
    }

    @Override
    public Triple getResult(String str) {
        List<String> characters = Arrays.asList(str.split(""));
        if (signs.contains(characters.get(0))) {
            return new Triple(1, true, priority);
        } else {
            return new Triple(0, false, priority);
        }
    }
}
