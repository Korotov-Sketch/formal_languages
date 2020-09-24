package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class NumberAutomaton implements Automaton {

    private Set<String> signs;

    private Set<String> numbers;

    private Priority priority;

    public NumberAutomaton(Set<String> signs, Set<String> numbers, Priority priority) {
        this.signs = signs;
        this.numbers = numbers;
        this.priority = priority;
    }

    @Override
    public Triple getResult(String str) {
        List<String> characters = Arrays.asList(str.split(""));
        int i;
        for (i = 0; i < characters.size() - 1; i++) {
            String s = characters.get(i);
            if (i != 0 && (signs.contains(s) && !s.equals(".") || !s.equals(".") && !numbers.contains(s))) {
                return new Triple(i, true, priority);
            }
            if (i == 0 && !(signs.contains(s) || numbers.contains(s))) {
                return new Triple(0, false, priority);
            }
        }
        return new Triple(0, false, priority);
    }
}
