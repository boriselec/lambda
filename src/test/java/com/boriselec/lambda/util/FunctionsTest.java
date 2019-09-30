package com.boriselec.lambda.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {
    @Test
    void testSuccessor() {
        assertEquals(Number.get(3), Functions.S.apply(Number.get(2)),
                "3 == ++2");
    }

    @Test
    void testAddition() {
        assertEquals(Number.get(5), Number.get(2).apply(Functions.S).apply(Number.get(3)),
                "5 == 2 + 3");
    }

    @Test
    void testMultiplication() {
        assertEquals(Number.get(1200), Functions.M.apply(Number.get(40)).apply(Number.get(30)),
                "1200 == 40 * 30");
    }
}