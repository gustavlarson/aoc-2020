package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day03 implements Day {

    @Override
    public String solvePart1(final List<String> input) {
        final int patternLength = input.get(0).length();
        final long result = IntStream.range(0, input.size())
                .filter(i -> input.get(i).charAt((i * 3) % patternLength) == '#')
                .count();
        return "" + result;
    }

    @Override
    public String solvePart2(final List<String> input) {
        final int patternLength = input.get(0).length();

        // Slope in {right, down}
        final int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};

        final long result = Arrays.stream(slopes).parallel().mapToLong(
                slope -> {
                    final int right = slope[0];
                    final int down = slope[1];
                    return IntStream
                            .range(0, input.size())
                            .filter(i -> i % down == 0)
                            .filter(i -> input.get(i).charAt(((i / down) * right) % patternLength) == '#')
                            .count();
                }
        ).reduce(1, (a, b) -> a * b);
        return "" + result;
    }
}
