package com.boriselec.lambda.util;

import com.boriselec.lambda.Application;
import com.boriselec.lambda.Lambda;
import com.boriselec.lambda.Var;

public interface Functions {
    // successor, addition
    // λn.λf.λx.(f ((n f) x))
    Lambda S = new Lambda(new Var('n'), new Lambda(new Var('f'), new Lambda(new Var('x'), new Application(new Var('f'), new Application(new Application(new Var('n'), new Var('f')), new Var('x'))))));
    // multiplication
    // λm.λn.λf.λx.((m (n f)) x)
    Lambda M = new Lambda(new Var('m'), new Lambda(new Var('n'), new Lambda(new Var('f'), new Lambda(new Var('x'), new Application(new Application(new Var('m'), new Application(new Var('n'), new Var('f'))), new Var('x'))))));
}
