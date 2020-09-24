package domain;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KeyWordAutomaton implements Automaton {

    private Set<String> words;

    private HashMap<Character, Condition> charToCondition;

    private Priority priority;

    public KeyWordAutomaton() {
    }

    public KeyWordAutomaton(Set<String> words, Priority priority) {
        this.words = words;
        this.priority = priority;
        this.charToCondition = new HashMap<>();
        words.forEach(word -> {
            List<Character> characters = word.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            int size = characters.size();
            for (int i = 0; i < size; i++) {
                Character character = characters.get(i);

                Condition condition = charToCondition.get(character);
                Character nextChar = null;

                if (i < size - 1) {
                    nextChar = characters.get(i + 1);
                }

                if (condition != null) {
                    condition.addPreviousNextChars(new Pair<>(characters.subList(0, i), nextChar));
                } else {
                    Condition createdCondition = new Condition(character);
                    createdCondition.addPreviousNextChars(new Pair<>(characters.subList(0, i), nextChar));
                    charToCondition.put(character, createdCondition);
                }
            }
        });
    }

    public Set<String> getWords() {
        return words;
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Triple getResult(String str) {

        List<Character> characters = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        for (int i = 0; i < characters.size() - 1; i++) {
            Condition condition = charToCondition.get(characters.get(i));
            Character nextCharacter = characters.get(i + 1);
            if (condition != null && condition.getPreviousNextChars().contains(new Pair<>(characters.subList(0, i), nextCharacter))) {
                if (charToCondition.get(nextCharacter).getPreviousNextChars().contains(new Pair<>(characters.subList(0, i + 1), null))) {
                    return new Triple(i + 2, true, priority);
                }
            } else {
                return new Triple(i, false, priority);
            }
        }
        return new Triple(0, false, priority);
    }
}
