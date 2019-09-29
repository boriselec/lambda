package com.boriselec.lambda;

import java.util.Objects;

public class Application implements Term {
    private final Term left;
    private final Term right;

    public Application(Term left, Term right) {
        this.left = left;
        this.right = right;
    }

    public Term apply(Term param) {
        return new Application(this, param);
    }

    public Term reduce() {
        return left.reduce().apply(right.reduce());
    }

    public Term substitute(Var from, Term to) {
        return new Application(left.substitute(from, to), right.substitute(from, to));
    }

    @Override
    public String toString() {
        return "(" + left + " " + right + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }
}
