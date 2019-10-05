package com.boriselec.lambda.util;

import com.boriselec.lambda.Var;
import org.junit.jupiter.api.Test;

import static com.boriselec.lambda.util.Functions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionsTest {
    @Test
    void testSuccessor() {
        assertEquals(Number.get(3), S.apply(Number.get(2)),
                "3 == ++2");
    }

    @Test
    void testPredecessor() {
        assertEquals(Number.get(2), P.apply(Number.get(3)),
                "2 == --3");
        assertEquals(Number.get(0), P.apply(Number.get(0)),
                "0 == --0");
    }

    @Test
    void testAddition() {
        assertEquals(Number.get(5), Number.get(2).apply(S).apply(Number.get(3)),
                "5 == 2 + 3");
    }

    @Test
    void testMultiplication() {
        assertEquals(Number.get(1200), M.apply(Number.get(40)).apply(Number.get(30)),
                "1200 == 40 * 30");
    }

    @Test
    void testAnd() {
        assertEquals(T, AND.apply(T).apply(T),
                "true == true and false");
        assertEquals(F, AND.apply(T).apply(F),
                "false == true and false");
        assertEquals(F, AND.apply(F).apply(T),
                "false == false and true");
        assertEquals(F, AND.apply(F).apply(F),
                "false == false and false");
    }

    @Test
    void testIf() {
        assertEquals(new Var('i'), IF_THEN_ELSE.apply(T).apply(new Var('i')).apply(new Var('j')),
                "i == if (true) then i else j");
        assertEquals(new Var('j'), IF_THEN_ELSE.apply(F).apply(new Var('i')).apply(new Var('j')),
                "j == if (false) then i else j");
    }

    @Test
    void testIsZero() {
        assertEquals(T, IS_ZERO.apply(Number.get(0)),
                "true == (0 == 0)");
        assertEquals(F, IS_ZERO.apply(Number.get(3)),
                "false == (3 == 0)");
    }
}