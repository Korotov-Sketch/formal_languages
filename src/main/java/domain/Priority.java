package domain;

public enum Priority {

    IDENTIFIER(0), NUMBER(1), SIGN(2), TYPE (3), KEYWORD (4);

    private int priorityLevel;

    Priority(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
}
