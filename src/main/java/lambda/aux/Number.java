package lambda.aux;

import lambda.Application;
import lambda.Expression;
import lambda.Function;
import lambda.RawVariable;

public interface Number {
    static Expression get(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        Expression definition = new RawVariable("x");
        for (int j = 0;j < i;j++) {
            definition = new Application(new RawVariable("f"), definition);
        }
        return new Function<>(new RawVariable("f"), new Function<>(new RawVariable("x"), definition));
    }
}
