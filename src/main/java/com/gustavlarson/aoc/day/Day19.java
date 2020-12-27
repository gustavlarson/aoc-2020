package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day19 implements Day {

    private final List<String> messages;
    private final Map<Integer, String> rules;

    public Day19(final List<String> input) {
        rules = new HashMap<>();
        input.stream().takeWhile(line -> line.length() > 0).forEach(line -> {
            var tmp = line.split(": ");
            var ruleNumber = Integer.parseInt(tmp[0]);
            rules.put(ruleNumber, tmp[1]);
        });

        this.messages = input.stream().dropWhile(line -> line.length() > 0).collect(Collectors.toList());
    }

    private void expandRule(final int ruleNumber, final StringBuilder sb, final int recursion) {
        // Not proud of this solution :)
        if (recursion > 15) {
            return;
        }
        String rule = rules.get(ruleNumber);
        if (rule.contains("\"")) {
            sb.append(rule.replace("\"", ""));
            return;
        }

        sb.append("(?:");

        for (var option : rule.split("\\|")) {
            for (var r : option.trim().split(" ")) {
                expandRule(Integer.parseInt(r), sb, recursion + 1);
            }
            sb.append("|");
        }
        sb.deleteCharAt(sb.length() - 1);

        sb.append(")");
    }

    private void expandRule(final int ruleNumber, final StringBuilder regex) {
        expandRule(ruleNumber, regex, 0);
    }

    @Override
    public long solvePart1() {
        StringBuilder regex = new StringBuilder("^");
        expandRule(0, regex);
        regex.append("$");
        Pattern p = Pattern.compile(regex.toString());

        return messages.stream().filter(line -> p.matcher(line).find()).count();
    }

    @Override
    public long solvePart2() {
        rules.put(8, "42 | 42 8");
        rules.put(11, "42 31 | 42 11 31");
        StringBuilder regex = new StringBuilder("^");
        expandRule(0, regex);
        regex.append("$");
        Pattern p = Pattern.compile(regex.toString());

        return messages.stream().filter(line -> p.matcher(line).find()).count();
    }
}
