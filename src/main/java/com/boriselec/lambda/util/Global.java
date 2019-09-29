package com.boriselec.lambda.util;

import java.util.HashSet;
import java.util.Set;

public class Global {
    public static final Set<Character> USED_VAR = new HashSet<>();

    public static char getUnused() {
        char var = 'a';
        while (USED_VAR.contains(var)) {
            var++;
        }
        return var;
    }
}
