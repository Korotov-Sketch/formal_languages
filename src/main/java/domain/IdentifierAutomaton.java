package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class IdentifierAutomaton implements Automaton {

    private Set<String> alphas;

    private Set<String> numbers;

    private Priority priority;

    public IdentifierAutomaton(Set<String> alphas, Set<String> numbers, Priority priority) {
        this.alphas = alphas;
        this.numbers = numbers;
        this.priority = priority;
    }

    @Override
    public Triple getResult(String str) {
        List<String> characters = Arrays.asList(str.split(""));

        for (int i = 0; i < characters.size() - 1; i++) {
            String s = characters.get(i);
            if (i == 0 && numbers.contains(s)) {
                return new Triple(0, false, priority);
            }
            if (!alphas.contains(s) && !numbers.contains(s)) {
                if (i != 0) {
                    return new Triple(i, true, priority);
                }
                return new Triple(0, false, priority);
            }
        }
        return new Triple(0, false, priority);
    }
}
