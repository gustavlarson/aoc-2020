package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 implements Day {
    private static int solver(final List<String> input, final int count) {
        final List<Integer> expenses = input.parallelStream().map(Integer::parseInt).collect(Collectors.toList());

        // Find the two elements that adds to 2020
        final List<Integer> result = expenses.parallelStream().filter(
                outer -> expenses.parallelStream().anyMatch(inner -> inner + outer == 2020)
        ).collect(Collectors.toList());

        // Multiply the elements
        return result.stream().reduce(1, (a, b) -> a * b);
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
