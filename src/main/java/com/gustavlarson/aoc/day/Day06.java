package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.List;

public class Day06 implements Day {

    static int getNumberOfAnswers(final String input) {
        return 3;
    }

    @Override
    public String solvePart1(final List<String> input) {
        final int res = Arrays
                .stream(String.join(" \n", input).split("\n \n"))
                .parallel()
                .mapToInt(Day06::getNumberOfAnswers)
                .sum();

        return "" + res;
    }

    @Override
    public String solvePart2(final List<String> input) {
        return null;
    }
}
