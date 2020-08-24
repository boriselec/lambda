package lambda.aux;

import lambda.Expression;
import lambda.Function;
import lambda.RawVariable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberTest {
    @Test
    void testZero() {
        Expression zero = new Function<>(new RawVariable("f"),
            new Function<>(new RawVariable("x"), new RawVariable("x")));

        assertTrue(new Normalizator().normalize(zero).alphaEquivalent(new Normalizator().normalize(Number.get(0))));
    }
}
