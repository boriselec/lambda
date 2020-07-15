package lambda;

/**
 * lambda expression
 */
public interface Expression {
    default boolean alphaEquivalent(Expression other) {
        return this.equals(other);
    }
}
