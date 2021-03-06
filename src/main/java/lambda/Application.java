package lambda;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Application implements Expression {
    private final Expression left;
    private final Expression right;

    public Application(Expression left, Expression right) {
        this.left = requireNonNull(left);
        this.right = requireNonNull(right);
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(left, that.left) &&
            Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + right.toString() + ")";
    }
}
