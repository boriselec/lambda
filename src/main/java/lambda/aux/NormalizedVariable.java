package lambda.aux;

import lambda.Variable;

import java.util.Objects;

public class NormalizedVariable implements Variable {
    private final long index;

    public NormalizedVariable(long index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalizedVariable that = (NormalizedVariable) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    @Override
    public String toString() {
        if (index < 28) {
            return String.valueOf((char) ('a' + index));
        } else {
            return "[" + index + "]";
        }
    }
}
