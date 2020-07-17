package lambda.aux;

import lambda.Application;
import lambda.Expression;
import lambda.Function;
import lambda.Variable;

/**
 * beta reduction
 */
public class Reducer {
    public Expression reduce(Expression expression) {
        Expression reduced = reduceStep(expression);
        if (reduced.alphaEquivalent(expression)) {
            return new Normalizator().normalize(expression);
        } else {
            return reduce(reduced);
        }
    }

    private Expression reduceStep(Expression expression) {
        if (expression instanceof Variable) {
            return expression;
        } else if (expression instanceof Application) {
            Application application = (Application) expression;
            if (application.getLeft() instanceof Function) {
                Function function = (Function) application.getLeft();
                return rename(function.getBody(), function.getParam(), application.getRight());
            } else {
                return new Application(application.getLeft(), application.getRight());
            }
        } else if (expression instanceof Function) {
            Function function = (Function) expression;
            return new Function<>(function.getParam(), reduceStep(function.getBody()));
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Expression rename(Expression in, Variable from, Expression to) {
        if (in instanceof Variable) {
            return in.equals(from) ? to : in;
        } else if (in instanceof Application) {
            Application application = (Application) in;
            return new Application(rename(application.getLeft(), from, to), rename(application.getRight(), from, to));
        } else if (in instanceof Function) {
            Function function = (Function) in;
            return new Function<>(function.getParam(), rename(function.getBody(), from, to));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
