package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 implements Day {
    private final List<Integer> expenses;

    public Day01(final List<String> input) {
        expenses = input.parallelStream().map(Integer::parseInt).collect(Collectors.toList());
    }

    private int solver(final int count) {
        // Find the n (count) elements that adds to 2020
        final List<Integer> result = expenses.parallelStream().filter(
                value -> recursiveMatcher(expenses, value, count)
        ).collect(Collectors.toList());

        // Multiply the elements
        return result.stream().reduce(1, (a, b) -> a * b);
    }

    private static boolean recursiveMatcher(final List<Integer> input, final int outer, final int count) {
        if (count == 1) {
            return outer == 2020;
        }
        return input.parallelStream()
                .filter(inner -> inner <= 2020 - outer) // Filter impossible candidates for performance improvement
                .anyMatch(inner -> recursiveMatcher(input, inner + outer, count - 1));
    }

    @Override
    public long solvePart1() {
        return solver(2);
    }

    @Override
    public long solvePart2() {
        return solver(3);
    }
}
