package gr.james.simplegraph;

/**
 * Helper graph utilities.
 */
final class Graphs {
    /**
     * Check is a weight value is legal.
     * <p>
     * A weight value is legal if it is not any of the following values:
     * <ul>
     * <li>{@link Double#NaN}</li>
     * <li>{@link Double#POSITIVE_INFINITY}</li>
     * <li>{@link Double#NEGATIVE_INFINITY}</li>
     * </ul>
     * <p>
     * Complexity: O(1)
     *
     * @param weight the weight value to check
     * @return {@code weight}
     * @throws IllegalArgumentException if {@code weight} is not legal
     */
    static double checkWeight(double weight) {
        if (!isWeightLegal(weight)) {
            throw new IllegalArgumentException();
        }
        return weight;
    }

    /**
     * Check is a weight value is legal.
     * <p>
     * A weight value is legal if it is not any of the following values:
     * <ul>
     * <li>{@link Double#NaN}</li>
     * <li>{@link Double#POSITIVE_INFINITY}</li>
     * <li>{@link Double#NEGATIVE_INFINITY}</li>
     * </ul>
     * <p>
     * Complexity: O(1)
     *
     * @param weight the weight value to check
     * @return {@code true} if {@code weight} is legal, otherwise {@code false}
     */
    static boolean isWeightLegal(double weight) {
        return !Double.isNaN(weight) && !Double.isInfinite(weight);
    }
}
