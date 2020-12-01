package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 implements Day {
    @Override
    public String solve(final List<String> input) {
        final List<Integer> expenses = input.parallelStream().map(Integer::parseInt).collect(Collectors.toList());

        final Integer result = expenses.stream().reduce(1, (a, b) -> a * b);
        return "" + result;
    }
}
