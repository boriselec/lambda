package lambda;

import java.util.Objects;

public class RawVariable implements Variable {
    private final String name;

    public RawVariable(String name) {
        this.name = name;
    }

    @Override
    public boolean alphaEquivalent(Expression other) {
        throw new UnsupportedOperationException("Should be normalized");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawVariable that = (RawVariable) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
