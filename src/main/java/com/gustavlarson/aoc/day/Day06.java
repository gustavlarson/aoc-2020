package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.List;

public class Day06 implements Day {

    static long getNumberOfAnswers(final String input) {
        return input.chars().filter(c -> c >= 'a' && c <= 'z').distinct().count();
    }

    @Override
    public String solvePart1(final List<String> input) {
        final long res = Arrays
                .stream(String.join(" \n", input).split("\n \n"))
                .parallel()
                .mapToLong(Day06::getNumberOfAnswers)
                .sum();

        return "" + res;
    }

    @Override
    public String solvePart2(final List<String> input) {
        return null;
    }
}
