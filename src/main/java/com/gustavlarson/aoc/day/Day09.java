package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day09 implements Day {

    private final List<Long> input;
    private static final int PREAMBLE_LENGTH = 25;

    public Day09(final List<String> input) {
        this.input = input.stream().map(Long::parseLong).collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        OUTER:
        for (var i = 25; i < input.size(); i++) {
            for (var j = 1; j < PREAMBLE_LENGTH + 1; j++) {
                for (var k = 1; k < PREAMBLE_LENGTH + 1; k++) {
                    if (j == k) continue;
                    if (input.get(i - j) + input.get(i - k) == input.get(i)) {
                        continue OUTER;
                    }
                }
            }
            return input.get(i);
        }
        throw new IllegalStateException();
    }

    @Override
    public long solvePart2() {
        final long weakness = solvePart1();
        for (var i = 0; i < input.size(); i++) {
            long min = Integer.MAX_VALUE;
            long max = Integer.MIN_VALUE;
            long sum = 0;
            for (var j = i; j < input.size(); j++) {
                final var value = input.get(j);
                if (value < min) min = value;
                if (value > max) max = value;
                sum += value;
                if (sum == weakness) {
                    return min + max;
                }
            }
        }
        throw new IllegalStateException();
    }
}
