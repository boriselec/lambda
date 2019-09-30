package com.boriselec.lambda.util;

import com.boriselec.lambda.Lambda;
import com.boriselec.lambda.Var;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {
    @Test
    void testZero() {
        Lambda zero = new Lambda(new Var('f'), new Lambda(new Var('x'), new Var('x')));
        System.out.println(zero);

        assertEquals(zero, Number.get(0));
    }
}