package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.IntStream;

public class Day03 implements Day {

    private static final char TREE = '#';

    private static class Slope {
        final int right;
        final int down;

        Slope(final int right, final int down) {
            this.right = right;
            this.down = down;
        }
    }

    private static long solver(final List<String> input, final List<Slope> slopes) {
        final int patternLength = input.get(0).length();
        final int slopeLength = input.size();

        return slopes.parallelStream()
                // Compute number of trees in slope
                .mapToLong(
                        slope -> IntStream
                                .range(0, slopeLength)
                                .filter(i -> i % slope.down == 0)
                                .filter(i -> input.get(i).charAt(((i / slope.down) * slope.right) % patternLength) == TREE)
                                .count()
                )
                // Multiply number of trees in all slopes
                .reduce(1, (a, b) -> a * b);
    }

    @Override
    public String solvePart1(final List<String> input) {
        final List<Slope> slopes = List.of(new Slope(3, 1));

        return "" + solver(input, slopes);
    }

    @Override
    public String solvePart2(final List<String> input) {
        final List<Slope> slopes = List.of(
                new Slope(1, 1),
                new Slope(3, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2));

        return "" + solver(input, slopes);
    }
}
