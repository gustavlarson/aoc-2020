package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 implements Day {

    private static final Pattern pattern = Pattern.compile("(?<min>\\d+)-(?<max>\\d+) (?<letter>[a-zA-Z]): (?<password>\\w+)");
    private final List<String> input;

    public Day02(final List<String> input) {
        this.input = input;
    }

    private static Matcher getMatcher(final String line) {
        final Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException();
        }
        return matcher;
    }

    private static boolean isValidPart1(final String line) {
        final Matcher matcher = getMatcher(line);
        final int minCount = Integer.parseInt(matcher.group("min"));
        final int maxCount = Integer.parseInt(matcher.group("max"));
        final char letter = matcher.group("letter").charAt(0);
        final String password = matcher.group("password");

        final long count = password.chars().parallel().filter(c -> c == letter).count();

        return count >= minCount && count <= maxCount;
    }

    @Override
    public long solvePart1() {
        return input.parallelStream().filter(Day02::isValidPart1).count();
    }

    private static boolean isValidPart2(final String line) {
        final Matcher matcher = getMatcher(line);
        final int firstPosition = Integer.parseInt(matcher.group("min"));
        final int secondPosition = Integer.parseInt(matcher.group("max"));
        final char letter = matcher.group("letter").charAt(0);
        final String password = matcher.group("password");

        final boolean firstMatches = password.charAt(firstPosition - 1) == letter;
        final boolean secondMatches = password.charAt(secondPosition - 1) == letter;

        return (firstMatches ^ secondMatches);
    }

    @Override
    public long solvePart2() {
        return input.parallelStream().filter(Day02::isValidPart2).count();
    }
}
