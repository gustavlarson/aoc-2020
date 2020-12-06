package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day06 implements Day {

    static long getNumberOfAnswersP1(final String input) {
        return input.chars().filter(c -> c >= 'a' && c <= 'z').distinct().count();
    }

    @Override
    public String solvePart1(final List<String> input) {
        final long res = Arrays
                .stream(String.join("\n", input).split("\n\n"))
                .parallel()
                .mapToLong(Day06::getNumberOfAnswersP1)
                .sum();

        return "" + res;
    }

    @Override
    public String solvePart2(final List<String> input) {
        final long res = Arrays
                .stream(String.join("\n", input).split("\n\n"))
                .parallel()
                .mapToLong(Day06::getNumberOfAnswersP2)
                .sum();

        return "" + res;
    }

    private static long getNumberOfAnswersP2(final String input) {
        final Map<Character, Integer> map = new HashMap<>();
        final List<String> lines = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        return IntStream.rangeClosed('a', 'z').filter(
                c -> lines.stream().allMatch(line -> line.contains("" + (char) c))
        ).count();

    }
}
