package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 implements Day {
    Pattern pattern = Pattern.compile("(?<min>\\d+)-(?<max>\\d+) (?<letter>[a-zA-Z]): (?<password>\\w+)");

    private boolean isValidPart1(final String line) {
        final Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException();
        }
        final int minCount = Integer.parseInt(matcher.group("min"));
        final int maxCount = Integer.parseInt(matcher.group("max"));
        final String letter = matcher.group("letter");
        final String password = matcher.group("password");

        final long count = password.chars().parallel().filter(c -> c == letter.charAt(0)).count();

        return count >= minCount && count <= maxCount;
    }

    @Override
    public String solvePart1(final List<String> input) {
        final long validPasswords = input.parallelStream().filter(this::isValidPart1).count();
        return "" + validPasswords;
    }

    private boolean isValidPart2(final String line) {
        final Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException();
        }
        final int minCount = Integer.parseInt(matcher.group("min"));
        final int maxCount = Integer.parseInt(matcher.group("max"));
        final String letter = matcher.group("letter");
        final String password = matcher.group("password");

        return (password.charAt(minCount - 1) == letter.charAt(0) ^ password.charAt(maxCount - 1) == letter.charAt(0));
    }

    @Override
    public String solvePart2(final List<String> input) {
        final long validPasswords = input.parallelStream().filter(this::isValidPart2).count();
        return "" + validPasswords;
    }
}
