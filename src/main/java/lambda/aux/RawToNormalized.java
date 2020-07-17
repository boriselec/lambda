package lambda.aux;

import lambda.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class RawToNormalized {
    private final AtomicLong index;
    private final Map<Variable, Long> rawToIndex;

    public RawToNormalized() {
        this(new AtomicLong(0), new HashMap<>());
    }

    private RawToNormalized(AtomicLong index, Map<Variable, Long> map) {
        this.index = index;
        this.rawToIndex = map;
    }

    public NormalizedVariable get(Variable rawVariable) {
        if (rawToIndex.containsKey(rawVariable)) {
            return new NormalizedVariable(rawToIndex.get(rawVariable));
        } else {
            rawToIndex.put(rawVariable, index.longValue());
            return new NormalizedVariable(index.getAndIncrement());
        }
    }

    public boolean contains(Variable rawVariable) {
        return rawToIndex.containsKey(rawVariable);
    }

    public RawToNormalized copyExcept(Variable rawVariable) {
        Map<Variable, Long> map = new HashMap<>(rawToIndex);
        map.remove(rawVariable);
        return new RawToNormalized(index, map);
    }
}
