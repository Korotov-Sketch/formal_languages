package domain;

import java.util.List;
import java.util.Map;

public class Automaton {

    private int rank;
    private String start;
    private String finish;
    private Map<String, List<String>> inputs;
    private Map<String, Map<String, String>> matrix;

    public Automaton() {
    }

    public Automaton(int rank, String start, String finish, Map<String, List<String>> inputs, Map<String, Map<String, String>> matrix) {
        this.rank = rank;
        this.start = start;
        this.finish = finish;
        this.inputs = inputs;
        this.matrix = matrix;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public Map<String, Map<String, String>> getMatrix() {
        return matrix;
    }

    public void setMatrix(Map<String, Map<String, String>> matrix) {
        this.matrix = matrix;
    }


    public Map<String, List<String>> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, List<String>> inputs) {
        this.inputs = inputs;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Map.Entry<Boolean, Integer> process(String s, int skip) {
        String condition = start;
        int counter = 0;
        boolean isCorrect = false;
        String oldCondition;
        for (int i = skip; i < s.length(); i++) {
            oldCondition = condition;
            condition = nextCondition(condition, String.valueOf(s.charAt(i)));
            if (condition == null) {
                condition = oldCondition;
                break;
            }
            counter++;
            isCorrect = true;
        }
        if (!finish.equals(condition)) {
            isCorrect = false;
        }
        return Map.entry(isCorrect, counter);

    }

    private String nextCondition(String from, String charStr) {
        Map<String, String> ways = matrix.get(from);
        if (ways != null) {
            for (Map.Entry<String, String> way : ways.entrySet()) {
                if (way.getKey().equals(charStr)) {
                    return way.getValue();
                } else if (inputs != null) {
                    if (inputs.get(way.getKey()).contains(charStr)) {
                        return way.getValue();
                    }
                }
            }
        }
        return null;
    }
}
