package lambda.aux;

import lambda.Application;
import lambda.Expression;
import lambda.Function;
import lambda.RawVariable;

public interface Functions {
    // identity
    // λx.x
    Expression I = new Function<>(new RawVariable("x"), new RawVariable("x"));
    // successor, addition
    // λn.λf.λx.(f ((n f) x))
    Expression S = new Function<>(new RawVariable("n"), new Function<>(new RawVariable("f"), new Function<>(new RawVariable("x"), new Application(new RawVariable("f"), new Application(new Application(new RawVariable("n"), new RawVariable("f")), new RawVariable("x"))))));
    // predecessor
    // λn.λf.λx.(((n λg.λh.(h (g f))) λu.x) λu.u)
    Expression P = new Function<>(new RawVariable("n"), new Function<>(new RawVariable("f"), new Function<>(new RawVariable("x"), new Application(new Application(new Application(new RawVariable("n"), new Function<>(new RawVariable("g"), new Function<>(new RawVariable("h"), new Application(new RawVariable("h"), new Application(new RawVariable("g"), new RawVariable("f")))))), new Function<>(new RawVariable("u"), new RawVariable("x"))), new Function<>(new RawVariable("u"), new RawVariable("u"))))));
    // multiplication
    // λm.λn.λf.λx.((m (n f)) x)
    Expression M = new Function<>(new RawVariable("m"), new Function<>(new RawVariable("n"), new Function<>(new RawVariable("f"), new Function<>(new RawVariable("x"), new Application(new Application(new RawVariable("m"), new Application(new RawVariable("n"), new RawVariable("f"))), new RawVariable("x"))))));
    // true
    // λx.λy.x
    Expression T = new Function<>(new RawVariable("x"), new Function<>(new RawVariable("y"), new RawVariable("x")));
    // false
    // λx.λy.y
    Expression F = new Function<>(new RawVariable("x"), new Function<>(new RawVariable("y"), new RawVariable("y")));
    // and
    // λp.λq.((p q) p)
    Expression AND = new Function<>(new RawVariable("p"), new Function<>(new RawVariable("q"), new Application(new Application(new RawVariable("p"), new RawVariable("q")), new RawVariable("p"))));
    // if then else
    // λp.λa.λb.((p a) b)
    Expression IF_THEN_ELSE = new Function<>(new RawVariable("p"), new Function<>(new RawVariable("a"), new Function<>(new RawVariable("b"), new Application(new Application(new RawVariable("p"), new RawVariable("a")), new RawVariable("b")))));
    Expression IS_ZERO = new Function<>(new RawVariable("n"), new Application(new Application(new RawVariable("n"), new Function<>(new RawVariable("x"), Functions.F)), Functions.T));
}
