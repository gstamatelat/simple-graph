package gr.james.simplegraph;

import java.util.Arrays;

final class EdgeImpl implements Edge {
    private final int v;
    private final int w;

    EdgeImpl(int v, int w) {
        this.v = v;
        this.w = w;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EdgeImpl that = (EdgeImpl) o;
        return v == that.v &&
                w == that.w;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{v, w});
    }

    @Override
    public String toString() {
        return String.format("%d -- %d", v, w);
    }
}
