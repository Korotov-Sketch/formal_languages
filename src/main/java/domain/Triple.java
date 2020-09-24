package domain;

import java.util.Comparator;
import java.util.Objects;

public class Triple implements Comparable<Triple>{
    public final Integer first;
    public final  Boolean second;
    public final Priority third;

    public Triple(Integer first,  Boolean second, Priority third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Integer getFirst() {
        return first;
    }

    public  Boolean getSecond() {
        return second;
    }

    public Priority getThird() {
        return third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triple)) return false;
        Triple triple = (Triple) o;
        return Objects.equals(first, triple.first) &&
                Objects.equals(second, triple.second) &&
                third == triple.third;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

    @Override
    public String toString() {
        return "Triple{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }

    @Override
    public int compareTo(Triple triple) {
        if (this.getThird().getPriorityLevel() < triple.getThird().getPriorityLevel()) {
            return 1;
        } else if (this.getThird().getPriorityLevel() > triple.getThird().getPriorityLevel()) {
            return -1;
        } else {
            return this.getFirst().compareTo(triple.getFirst());
        }
    }
    }
