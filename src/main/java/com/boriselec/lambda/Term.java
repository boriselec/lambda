package com.boriselec.lambda;

public interface Term {
    Term apply(Term param);
    Term reduce();
    Term substitute(Var from, Term to);
}
