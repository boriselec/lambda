package lambda.aux;

import lambda.Application;
import lambda.Expression;
import lambda.Function;
import lambda.RawVariable;
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
        if (expression instanceof RawVariable) {
            return normalize(((RawVariable) expression));
        } else if (expression instanceof Application) {
            return normalize(((Application) expression));
        } else if (expression instanceof Function) {
            return normalize(((Function) expression));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public NormalizedVariable normalize(Variable variable) {
        if (variable instanceof RawVariable) {
            return bound.get((RawVariable) variable);
        } else if (variable instanceof NormalizedVariable) {
            return (NormalizedVariable) variable;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Application normalize(Application application) {
        return new Application(normalize(application.getLeft()), normalize(application.getRight()));
    }

    public Function<NormalizedVariable> normalize(Function<RawVariable> function) {
        if (bound.contains(function.getArg())) {
            //unbound variable
            return new Normalizator(bound.copyExcept(function.getArg())).normalize(function);
        }
        return new Function<>(normalize(function.getArg()), normalize(function.getBody()));
    }
}
