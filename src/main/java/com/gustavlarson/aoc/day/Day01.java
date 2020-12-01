package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 implements Day {
    private static int solver(final List<String> input, final int count) {
        final List<Integer> expenses = input.parallelStream().map(Integer::parseInt).collect(Collectors.toList());

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
        return input.parallelStream().anyMatch(
                inner -> recursiveMatcher(input, inner + outer, count - 1)
        );
    }

    @Override
    public String solvePart1(final List<String> input) {
        return "" + solver(input, 2);
    }

    @Override
    public String solvePart2(final List<String> input) {
        return "" + solver(input, 3);
    }
}
