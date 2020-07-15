import lambda.Function;
import lambda.aux.Normalizator;
import lambda.RawVariable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LambdaTest {
    @Test
    void testAlphaEquivalence() {
        Function i = new Normalizator().normalize(new Function<>(new RawVariable("x"), new RawVariable("x")));
        Function i2 = new Normalizator().normalize(new Function<>(new RawVariable("y"), new RawVariable("y")));
        Function x = new Normalizator().normalize(new Function<>(new RawVariable("y"), new RawVariable("x")));

        assertTrue(i.alphaEquivalent(i2));
        assertFalse(i.alphaEquivalent(x));

        // λx.λx.x
        Function f1 = new Normalizator().normalize(
            new Function<>(new RawVariable("x"), new Function<>(new RawVariable("x"), new RawVariable("x"))));
        // λx.λy.y
        Function f2 = new Normalizator().normalize(
            new Function<>(new RawVariable("x"), new Function<>(new RawVariable("y"), new RawVariable("y"))));
        // λx.λy.x
        Function f3 = new Normalizator().normalize(
            new Function<>(new RawVariable("x"), new Function<>(new RawVariable("y"), new RawVariable("x"))));

        assertTrue(f1.alphaEquivalent(f2));
        assertFalse(f1.alphaEquivalent(f3));
    }
}
