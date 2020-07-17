package lambda;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Function<T extends Variable> implements Expression {
    private final T param;
    private final Expression body;

    public Function(T param, Expression body) {
        this.param = requireNonNull(param);
        this.body = requireNonNull(body);
    }

    public T getParam() {
        return param;
    }

    public Expression getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Objects.equals(param, function.param) &&
            Objects.equals(body, function.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(param, body);
    }

    @Override
    public String toString() {
        return "λ" + param + "." + body;
    }
}
