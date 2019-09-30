package com.boriselec.lambda.util;

import com.boriselec.lambda.Application;
import com.boriselec.lambda.Lambda;
import com.boriselec.lambda.Term;
import com.boriselec.lambda.Var;

public interface Number {
    static Lambda get(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        Term definition = new Var('x');
        for (int j = 0; j < i; j++) {
            definition = new Application(new Var('f'), definition);
        }
        return new Lambda(new Var('f'), new Lambda(new Var('x'), definition));
    }
}
