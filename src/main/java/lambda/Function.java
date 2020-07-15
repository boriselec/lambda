package lambda;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Function<T extends Variable> implements Expression {
    private final T arg;
    private final Expression body;

    public Function(T arg, Expression body) {
        this.arg = requireNonNull(arg);
        this.body = requireNonNull(body);
    }

    public T getArg() {
        return arg;
    }

    public Expression getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Objects.equals(arg, function.arg) &&
            Objects.equals(body, function.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arg, body);
    }

    @Override
    public String toString() {
        return "λ" + arg + "." + body;
    }
}
