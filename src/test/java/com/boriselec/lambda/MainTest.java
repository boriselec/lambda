package com.boriselec.lambda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testEqualsIdentity() {
        Lambda expected = new Lambda(new Var('x'), new Var('x'));
        System.out.println(expected);
        Lambda actual = new Lambda(new Var('y'), new Var('y'));
        System.out.println(actual);

        assertEquals(expected, actual);
    }

    @Test
    void testNotEqualsIdentityAndConstant() {
        Lambda unexpected = new Lambda(new Var('x'), new Var('x'));
        System.out.println(unexpected);
        Lambda actual = new Lambda(new Var('y'), new Var('z'));
        System.out.println(actual);

        assertNotEquals(unexpected, actual);
    }

    @Test
    void testIdentityAppliesToConstant() {
        Var z = new Var('z');
        System.out.println(z);
        Lambda lambda = new Lambda(new Var('x'), new Var('x'));
        System.out.println(lambda);

        assertEquals(z, lambda.apply(z));
    }
}