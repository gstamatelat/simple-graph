package gr.james.simplegraph;

import java.util.Arrays;

final class WeightedDirectedEdgeImpl implements WeightedDirectedEdge {
    private final int source;
    private final int target;
    private final double weight;

    WeightedDirectedEdgeImpl(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public int source() {
        return source;
    }

    @Override
    public int target() {
        return target;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WeightedDirectedEdge)) {
            return false;
        }
        final WeightedDirectedEdge that = (WeightedDirectedEdge) obj;
        return source == that.source() &&
                target == that.target() &&
                weight == that.weight();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{source, target, weight});
    }

    @Override
    public String toString() {
        return String.format("%d -> %d [%.2f]", source, target, weight);
    }
}
