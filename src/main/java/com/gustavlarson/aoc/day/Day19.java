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

    private String expandRule(final int ruleNumber, final StringBuilder sb) {
        String rule = rules.get(ruleNumber);
        if (rule.contains("\"")) {
            sb.append(rule.replace("\"", ""));
            return rule.replace("\"", "");
        }

        String s = "(";
        sb.append("(");

        for (var option : rule.split("\\|")) {
            for (var r : option.trim().split(" ")) {
                s += expandRule(Integer.parseInt(r), sb);
            }
            s += "|";
            sb.append("|");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return s + ")";
    }

    @Override
    public long solvePart1() {
        StringBuilder sb = new StringBuilder("^");
        String regex = expandRule(0, sb);
        sb.append("$");
        System.out.println(regex);
        System.out.println(sb);
        Pattern p = Pattern.compile(sb.toString());

        return messages.stream().filter(line ->
                p.matcher(line).find()).count();
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
