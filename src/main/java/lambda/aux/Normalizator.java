package lambda.aux;

import lambda.Application;
import lambda.Expression;
import lambda.Function;
import lambda.Variable;

/**
 * Ordering variables starting with 'a' for simplicity
 * i.e. λx.λx.x -> λa.λb.b
 */
public class Normalizator {
    private final RawToNormalized bound;

    public Normalizator() {
        this(new RawToNormalized());
    }

    public Normalizator(RawToNormalized bound) {
        this.bound = bound;
    }

    public Expression normalize(Expression expression) {
        if (expression instanceof Variable) {
            return normalize(((Variable) expression));
        } else if (expression instanceof Application) {
            return normalize(((Application) expression));
        } else if (expression instanceof Function) {
            return normalize(((Function) expression));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public NormalizedVariable normalize(Variable variable) {
        return bound.get(variable);
    }

    public Application normalize(Application application) {
        return new Application(normalize(application.getLeft()), normalize(application.getRight()));
    }

    public Function normalize(Function function) {
        if (bound.contains(function.getParam())) {
            //unbound variable
            return new Normalizator(bound.copyExcept(function.getParam())).normalize(function);
        }
        return new Function<>(normalize(function.getParam()), normalize(function.getBody()));
    }
}
