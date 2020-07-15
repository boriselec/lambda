package lambda.aux;

import lambda.RawVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class RawToNormalized {
    private final AtomicLong index;
    private final Map<RawVariable, Long> rawToIndex;

    public RawToNormalized() {
        this(new AtomicLong(0), new HashMap<>());
    }

    private RawToNormalized(AtomicLong index, Map<RawVariable, Long> map) {
        this.index = index;
        this.rawToIndex = map;
    }

    public NormalizedVariable get(RawVariable rawVariable) {
        if (rawToIndex.containsKey(rawVariable)) {
            return new NormalizedVariable(rawToIndex.get(rawVariable));
        } else {
            rawToIndex.put(rawVariable, index.longValue());
            return new NormalizedVariable(index.getAndIncrement());
        }
    }

    public boolean contains(RawVariable rawVariable) {
        return rawToIndex.containsKey(rawVariable);
    }

    public RawToNormalized copyExcept(RawVariable rawVariable) {
        Map<RawVariable, Long> map = new HashMap<>(rawToIndex);
        map.remove(rawVariable);
        return new RawToNormalized(index, map);
    }
}
