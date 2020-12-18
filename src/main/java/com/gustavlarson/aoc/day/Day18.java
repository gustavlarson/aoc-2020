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

    private static String evaluateBrackets(String expression) {
        Matcher m = brackets.matcher(expression);
        while (m.find()) {
            long result = evaluate(m.group("expression"));
            expression = expression.replace(m.group(0), Long.toString(result));
        }
        return expression;
    }

    private static long evaluate(String expression) {
        expression = evaluateBrackets(expression);

        Matcher m = math.matcher(expression);
        //It's possible that we just have a number at this point, in that case just return it
        if (!m.find()) return Long.parseLong(expression);

        //Evaluate
        long left = Long.parseLong(m.group("left"));
        String operator = m.group("op");
        long right = Long.parseLong(m.group("right"));

        long result = switch (operator) {
            case "+" -> left + right;
            case "*" -> left * right;
            default -> throw new ArithmeticException();
        };

        //Check if whole expression has been evaluated
        if (m.group(0).equals(expression)) return result;

        //Evaluate remaining
        String remainingExpression = expression.replace(m.group(0), Long.toString(result));
        return evaluate(remainingExpression);
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
