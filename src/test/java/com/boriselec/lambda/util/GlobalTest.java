package com.boriselec.lambda.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalTest {
    @Test
    void testGetUnused() {
        Global.USED_VAR.add('b');

        char var = Global.getUnused();
        assertEquals('a', var);
        Global.USED_VAR.add(var);

        var = Global.getUnused();
        assertEquals('c', var);
    }
}