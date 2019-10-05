package com.boriselec.lambda.util;

import com.boriselec.lambda.Application;
import com.boriselec.lambda.Lambda;
import com.boriselec.lambda.Var;

public interface Functions {
    // identity
    // λx.x
    Lambda I = new Lambda(new Var('x'), new Var('x'));
    // successor, addition
    // λn.λf.λx.(f ((n f) x))
    Lambda S = new Lambda(new Var('n'), new Lambda(new Var('f'), new Lambda(new Var('x'), new Application(new Var('f'), new Application(new Application(new Var('n'), new Var('f')), new Var('x'))))));
    // predecessor
    // λn.λf.λx.(((n λg.λh.(h (g f))) λu.x) λu.u)
    Lambda P = new Lambda(new Var('n'), new Lambda(new Var('f'), new Lambda(new Var('x'), new Application(new Application(new Application(new Var('n'), new Lambda(new Var('g'), new Lambda(new Var('h'), new Application(new Var('h'), new Application(new Var('g'), new Var('f')))))), new Lambda(new Var('u'), new Var('x'))), new Lambda(new Var('u'), new Var('u'))))));
    // multiplication
    // λm.λn.λf.λx.((m (n f)) x)
    Lambda M = new Lambda(new Var('m'), new Lambda(new Var('n'), new Lambda(new Var('f'), new Lambda(new Var('x'), new Application(new Application(new Var('m'), new Application(new Var('n'), new Var('f'))), new Var('x'))))));
    // true
    // λx.λy.x
    Lambda T = new Lambda(new Var('x'), new Lambda(new Var('y'), new Var('x')));
    // false
    // λx.λy.y
    Lambda F = new Lambda(new Var('x'), new Lambda(new Var('y'), new Var('y')));
    // and
    // λp.λq.((p q) p)
    Lambda AND = new Lambda(new Var('p'), new Lambda(new Var('q'), new Application(new Application(new Var('p'), new Var('q')), new Var('p'))));
    // if then else
    // λp.λa.λb.((p a) b)
    Lambda IF_THEN_ELSE = new Lambda(new Var('p'), new Lambda(new Var('a'), new Lambda(new Var('b'), new Application(new Application(new Var('p'), new Var('a')), new Var('b')))));
    Lambda IS_ZERO = new Lambda(new Var('n'), new Application(new Application(new Var('n'), new Lambda(new Var('x'), Functions.F)), Functions.T));
}
