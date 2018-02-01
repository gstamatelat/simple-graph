package gr.james.simplegraph;

import java.util.Arrays;

final class DirectedEdgeImpl implements DirectedEdge {
    private final int source;
    private final int target;

    DirectedEdgeImpl(int source, int target) {
        this.source = source;
        this.target = target;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DirectedEdge)) {
            return false;
        }
        final DirectedEdge that = (DirectedEdge) obj;
        return source == that.source() &&
                target == that.target();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{source, target});
    }

    @Override
    public String toString() {
        return String.format("%d -> %d", source, target);
    }
}
