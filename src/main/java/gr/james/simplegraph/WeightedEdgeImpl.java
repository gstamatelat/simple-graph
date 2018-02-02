package gr.james.simplegraph;

import java.util.Arrays;

final class WeightedEdgeImpl implements WeightedEdge {
    private final int v;
    private final int w;
    private final double weight;

    WeightedEdgeImpl(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public int v() {
        return v;
    }

    @Override
    public int w() {
        return w;
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
        if (obj == null || !(obj instanceof WeightedEdge)) {
            return false;
        }
        final WeightedEdge that = (WeightedEdge) obj;
        return v() == that.v() &&
                w() == that.w() &&
                weight() == that.weight();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{v(), w(), weight()});
    }

    @Override
    public String toString() {
        return String.format("%d -- %d [%.2f]", v(), w(), weight());
    }
}
