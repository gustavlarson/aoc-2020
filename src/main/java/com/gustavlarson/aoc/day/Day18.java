package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day18 implements Day {

    private final List<String> input;

    public Day18(final List<String> input) {
        this.input = input.stream().map(line -> line.replace(" ", "")).collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        return input.stream().mapToLong(Day18::evaluate).sum();
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
        String remainingExpression = expression.replaceFirst(m.group(0).replace("*", "\\*").replace("+", "\\+"), Long.toString(result));
        return evaluate(remainingExpression);
    }

    @Override
    public long solvePart2() {
        return input.stream().mapToLong(Day18::evaluateAdvanced).sum();
    }

    private static long evaluateAdvanced(String expression) {
        expression = evaluateBracketsAdvanced(expression);

        expression = evaluateAddition(expression);

        expression = evaluateMultiplication(expression);

        return Long.parseLong(expression);
    }

    private static String evaluateBracketsAdvanced(String expression) {
        Matcher m = brackets.matcher(expression);
        while (m.find()) {
            long result = evaluateAdvanced(m.group("expression"));
            expression = expression.replace(m.group(0), Long.toString(result));
            m = brackets.matcher(expression);
        }
        return expression;
    }

    private static final Pattern addition = Pattern.compile("(?<left>\\d+)(?<op>\\+)(?<right>\\d+)");

    private static String evaluateAddition(String expression) {
        Matcher m = addition.matcher(expression);
        String remainingExpression = expression;
        while (m.find()) {
            //Evaluate
            long left = Long.parseLong(m.group("left"));
            long right = Long.parseLong(m.group("right"));

            long result = left + right;
            remainingExpression = remainingExpression.replaceFirst(m.group(0).replace("*", "\\*").replace("+", "\\+"), Long.toString(result));
            m = addition.matcher(remainingExpression);
        }

        return remainingExpression;
    }

    private static final Pattern multiplication = Pattern.compile("(?<left>\\d+)(?<op>\\*)(?<right>\\d+)");

    private static String evaluateMultiplication(String expression) {
        Matcher m = multiplication.matcher(expression);
        String remainingExpression = expression;
        while (m.find()) {
            //Evaluate
            long left = Long.parseLong(m.group("left"));
            long right = Long.parseLong(m.group("right"));

            long result = left * right;
            remainingExpression = remainingExpression.replaceFirst(m.group(0).replace("*", "\\*").replace("+", "\\+"), Long.toString(result));
            m = multiplication.matcher(remainingExpression);
        }

        return remainingExpression;
    }
}
