package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 implements Day {
    @Override
    public String solve(final List<String> input) {
        final List<Integer> expenses = input.parallelStream().map(Integer::parseInt).collect(Collectors.toList());

        // Find the two elements that adds to 2020
        final List<Integer> result = expenses.parallelStream().filter(
                outer -> expenses.parallelStream().anyMatch(inner -> inner + outer == 2020)
        ).collect(Collectors.toList());

        // Multiply the elements
        return "" + result.stream().reduce(1, (a, b) -> a * b);
    }
}
