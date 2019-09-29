package com.boriselec.lambda;

import com.boriselec.lambda.util.Global;

public class Lambda implements Term {
    private final Var param;
    private final Term definition;

    public Lambda(Var param, Term definition) {
        this.param = param;
        this.definition = definition;
    }

    public Term apply(Term param) {
        return definition.substitute(this.param, param);
    }

    public Term reduce() {
        return new Lambda(param, definition.reduce());
    }

    @Override
    public Term substitute(Var from, Term to) {
        Var freeVar = new Var(Global.getUnused());
        return new Lambda(freeVar, definition.substitute(this.param, freeVar).substitute(from, to));
    }

    @Override
    public String toString() {
        return "λ" + param + "." + definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lambda lambda = (Lambda) o;
        return this.definition.equals(lambda.definition.substitute(lambda.param, this.param));
    }
}
