package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.IntStream;

public class Day03 implements Day {

    public static final char TREE = '#';

    private class Slope {
        final int right;
        final int down;

        Slope(final int right, final int down) {
            this.right = right;
            this.down = down;
        }
    }

    @Override
    public String solvePart1(final List<String> input) {
        final int patternLength = input.get(0).length();
        final int slopeLength = input.size();

        final long trees = IntStream.range(0, slopeLength)
                .filter(i -> input.get(i).charAt((i * 3) % patternLength) == TREE)
                .count();
        return "" + trees;
    }

    @Override
    public String solvePart2(final List<String> input) {
        final int patternLength = input.get(0).length();
        final int slopeLength = input.size();

        final List<Slope> slopes = List.of(
                new Slope(1, 1),
                new Slope(3, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2));

        final long result = slopes.parallelStream().mapToLong(
                slope -> IntStream
                        .range(0, slopeLength)
                        .filter(i -> i % slope.down == 0)
                        .filter(i -> input.get(i).charAt(((i / slope.down) * slope.right) % patternLength) == TREE)
                        .count()

        ).reduce(1, (a, b) -> a * b);
        return "" + result;
    }
}
