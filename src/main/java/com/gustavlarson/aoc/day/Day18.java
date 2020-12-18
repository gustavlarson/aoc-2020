package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18 implements Day {

    private final List<String> input;

    public Day18(final List<String> input) {
        this.input = input;
    }

    @Override
    public long solvePart1() {
        return input.stream().mapToLong(line -> evaluate(line)).sum();
    }

    private static final Pattern p = Pattern.compile("(?<left>\\d+)(?<op>[+*])(?<right>\\d+)");

    private static long evaluate(String line) {
        line = line.replace(" ", "");
        Matcher m = p.matcher(line);
        if (!m.find()) throw new IllegalStateException();
        //Check if we have more to evaluate
        int left = Integer.parseInt(m.group("left"));
        String operator = m.group("op");
        int right = Integer.parseInt(m.group("right"));

        int result = switch (operator) {
            case "+" -> left + right;
            case "*" -> left * right;
            default -> throw new ArithmeticException();
        };

        if (!m.group(0).equals(line)) {
            String remainingExpression = line.replace(m.group(0), Integer.toString(result));
            return evaluate(remainingExpression);
        }

        return result;
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
