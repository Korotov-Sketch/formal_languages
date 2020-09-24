package domain;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Condition {
    private Set<Pair<List<Character>, Character>> previousNextChars;
    private Character current;

    public Condition(Character current) {
        this.previousNextChars = new HashSet<>();
        this.current = current;
    }

    public Set<Pair<List<Character>, Character>> getPreviousNextChars() {
        return previousNextChars;
    }

    public void setPreviousNextChars(Set<Pair<List<Character>, Character>> previousNextChars) {
        this.previousNextChars = previousNextChars;
    }

    public void  addPreviousNextChars(Pair<List<Character>, Character> pair) {
        this.previousNextChars.add(pair);
    }

    public Character getCurrent() {
        return current;
    }

    public void setCurrent(Character current) {
        this.current = current;
    }

}
