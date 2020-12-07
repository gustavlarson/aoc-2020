package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 implements Day {
    private final Pattern colorPattern = Pattern.compile("(?<name>.*) bags contain (?<rule>.*)");
    private final Pattern rulePattern = Pattern.compile("(?<number>\\d+) (?<name>[\\w\\s]+) bag");

    static Map<String, Map<String, Integer>> rules = new HashMap<>();

    class Rule {
        private final String name;
        private final Map<String, Integer> map = new HashMap<>();

        Rule(final String input) {
            final Matcher m = colorPattern.matcher(input);
            if (!m.find()) {
                throw new IllegalArgumentException();
            }
            name = m.group("name");
            final String rules = m.group("rule");

            final Matcher mr = rulePattern.matcher(rules);

            while (mr.find()) {
                map.put(mr.group("name"), Integer.parseInt(mr.group("number")));
            }

        }

//        boolean canContainGold() {
//            if (map.containsKey("shiny gold")) return true;
//
//            return map.keySet().stream().anyMatch(rule -> {
//
//            });
//        }
    }

    @Override
    public String solvePart1(final List<String> input) {
        input.forEach(line -> {
            final Matcher m = colorPattern.matcher(line);
            if (!m.find()) {
                throw new IllegalArgumentException();
            }
            final String name = m.group("name");
            final String rule = m.group("rule");

            final Matcher mr = rulePattern.matcher(rule);

            final Map<String, Integer> map = new HashMap<>();

            while (mr.find()) {
                map.put(mr.group("name"), Integer.parseInt(mr.group("number")));
            }
            rules.put(name, map);
        });

        return "" + rules.keySet().stream().filter(Day07::containsShinyGold).count();
    }

    private static boolean containsShinyGold(final String color) {
        if (rules.get(color) == null) return false;
        if (rules.get(color).containsKey("shiny gold")) return true;
        if (rules.get(color).isEmpty()) return false;
        return rules.get(color).keySet().stream().anyMatch(Day07::containsShinyGold);
    }

    @Override
    public String solvePart2(final List<String> input) {
        return null;
    }
}
