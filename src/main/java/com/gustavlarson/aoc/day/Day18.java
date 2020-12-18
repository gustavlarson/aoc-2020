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
        return input.stream().map(line -> line.replace(" ", "")).mapToLong(Day18::evaluate).sum();
    }

    private static final Pattern brackets = Pattern.compile("\\((?<expression>[\\d * +]+)\\)");
    private static final Pattern math = Pattern.compile("(?<left>\\d+)(?<op>[+*])(?<right>\\d+)");

    private static long evaluate(String line) {
        Matcher b = brackets.matcher(line);
        while (b.find()) {
            long result = evaluate(b.group("expression"));
            line = line.replace(b.group(0), Long.toString(result));
        }

        Matcher m = math.matcher(line);
        if (!m.find()) return Long.parseLong(line);
        //Check if we have more to evaluate
        long left = Long.parseLong(m.group("left"));
        String operator = m.group("op");
        long right = Long.parseLong(m.group("right"));

        long result = switch (operator) {
            case "+" -> left + right;
            case "*" -> left * right;
            default -> throw new ArithmeticException();
        };

        if (!m.group(0).equals(line)) {
            String remainingExpression = line.replace(m.group(0), Long.toString(result));
            return evaluate(remainingExpression);
        }

        return result;
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
