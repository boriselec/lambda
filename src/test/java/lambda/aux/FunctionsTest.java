package lambda.aux;

import lambda.Application;
import lambda.Expression;
import org.junit.jupiter.api.Test;

import static lambda.aux.Functions.AND;
import static lambda.aux.Functions.F;
import static lambda.aux.Functions.IF_THEN_ELSE;
import static lambda.aux.Functions.IS_ZERO;
import static lambda.aux.Functions.M;
import static lambda.aux.Functions.P;
import static lambda.aux.Functions.S;
import static lambda.aux.Functions.T;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionsTest {
    @Test
    void testSuccessor() {
        Expression three = new Normalizator().normalize(
            Number.get(3));
        Expression twoPlusOne = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(S, Number.get(2)))));

        assertTrue(three.alphaEquivalent(twoPlusOne),
            "3 == ++2");
    }

    @Test
    void testPredecessor() {
        Expression two = new Normalizator().normalize(
            Number.get(2));
        Expression threeMinusOne = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(P, Number.get(3)))));
        assertTrue(two.alphaEquivalent(threeMinusOne),
            "2 == --3");

        Expression zero = new Normalizator().normalize(
            Number.get(0));
        Expression zeroMinusOne = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(P, Number.get(0)))));
        assertTrue(zero.alphaEquivalent(zeroMinusOne),
            "0 == --0");
    }

    @Test
    void testAddition() {
        Expression five = new Normalizator().normalize(
            Number.get(5));
        Expression twoPlusThree = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        new Application(Number.get(2), S),
                        Number.get(3)))));
        assertTrue(five.alphaEquivalent(twoPlusThree),
            "5 == 2 + 3");
    }

    @Test
    void testMultiplication() {
        Expression twentyHundreds = new Normalizator().normalize(
            Number.get(1200));
        Expression fortyByThirty = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        new Application(M, Number.get(40)),
                        Number.get(30)))));
        assertTrue(twentyHundreds.alphaEquivalent(fortyByThirty),
            "1200 == 40 * 30");
    }

    @Test
    void testAnd() {
        Expression trueExpression = new Normalizator().normalize(T);
        Expression falseExpression = new Normalizator().normalize(F);

        Expression trueAndTrue = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        new Application(AND, trueExpression),
                        trueExpression))));
        Expression trueAndFalse = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        new Application(AND, trueExpression),
                        falseExpression))));
        Expression falseAndTrue = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        new Application(AND, falseExpression),
                        trueExpression))));
        Expression falseAndFalse = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        new Application(AND, falseExpression),
                        falseExpression))));


        assertTrue(trueExpression.alphaEquivalent(trueAndTrue),
            "true == true and true");
        assertTrue(falseExpression.alphaEquivalent(trueAndFalse),
            "false == true and false");
        assertTrue(falseExpression.alphaEquivalent(falseAndTrue),
            "false == false and true");
        assertTrue(falseExpression.alphaEquivalent(falseAndFalse),
            "false == false and false");
    }

    @Test
    void testIf() {
        Expression five = new Normalizator().normalize(Number.get(5));
        Expression six = new Normalizator().normalize(Number.get(6));

        Expression ifTrueThenFiveElseSix = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        new Application(
                            new Application(
                                IF_THEN_ELSE,
                                T),
                            five),
                        six))));
        assertTrue(ifTrueThenFiveElseSix.alphaEquivalent(five),
            "5 == if (true) then 5 else 6");

        Expression ifFalseThenFiveElseSix = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        new Application(
                            new Application(
                                IF_THEN_ELSE,
                                F),
                            five),
                        six))));
        assertTrue(ifFalseThenFiveElseSix.alphaEquivalent(six),
            "5 == if (false) then 5 else 6");
    }

    @Test
    void testIsZero() {
        Expression trueExpression = new Normalizator().normalize(T);
        Expression falseExpression = new Normalizator().normalize(F);

        Expression isZeroZero = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        IS_ZERO,
                        Number.get(0)))));

        assertTrue(trueExpression.alphaEquivalent(isZeroZero),
            "true == (0 == 0)");

        Expression isThreeZero = new Normalizator().normalize(
            new Reducer().reduce(
                new Normalizator().normalize(
                    new Application(
                        IS_ZERO,
                        Number.get(3)))));
        assertTrue(falseExpression.alphaEquivalent(isThreeZero),
            "false == (3 == 0)");
    }
}