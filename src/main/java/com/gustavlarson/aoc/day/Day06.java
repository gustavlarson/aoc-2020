package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day06 implements Day {

    private final List<String> input;

    public Day06(final List<String> input) {
        this.input = input;
    }

    static long getNumberOfAnswersP1(final String input) {
        return input.chars().filter(c -> c >= 'a' && c <= 'z').distinct().count();
    }

    @Override
    public long solvePart1() {
        final long res = Arrays
                .stream(splitIntoGroups(input))
                .parallel()
                .mapToLong(Day06::getNumberOfAnswersP1)
                .sum();

        return res;
    }

    private static String[] splitIntoGroups(final List<String> input) {
        return String.join("\n", input).split("\n\n");
    }

    @Override
    public long solvePart2() {
        final long res = Arrays
                .stream(splitIntoGroups(input))
                .parallel()
                .mapToLong(Day06::getNumberOfAnswersP2)
                .sum();

        return res;
    }

    private static long getNumberOfAnswersP2(final String input) {
        final List<String> passengers = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        return IntStream.rangeClosed('a', 'z').filter(
                c -> passengers.stream().allMatch(line -> line.contains("" + (char) c))
        ).count();

    }
}
