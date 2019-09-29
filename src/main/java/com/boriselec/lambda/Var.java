package com.boriselec.lambda;

import com.boriselec.lambda.util.Global;

public class Var implements Term {
    private final char name;

    public Var(char name) {
        this.name = name;
        Global.USED_VAR.add(name);
    }

    public Term apply(Term param) {
        return new Application(this, param);
    }

    public Term reduce() {
        return this;
    }

    public Term substitute(Var from, Term to) {
        if (this.equals(from)) {
            return to;
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Var var = (Var) o;
        return name == var.name;
    }
}
