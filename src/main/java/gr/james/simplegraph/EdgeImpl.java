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
    public Edge swap() {
        Edge e = new EdgeImpl(w, v);
        assert this.equals(e);
        return e;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Edge)) {
            return false;
        }
        final Edge that = (Edge) obj;
        return Math.min(v(), w()) == Math.min(that.v(), that.w()) &&
                Math.max(v(), w()) == Math.max(that.v(), that.w());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{
                Math.min(v(), w()),
                Math.max(v(), w())});
    }

    @Override
    public String toString() {
        return String.format("%d -- %d", v(), w());
    }
}
