package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 implements Day {
    private static final Pattern colorPattern = Pattern.compile("(?<name>.*) bags contain (?<rule>.*)");
    private static final Pattern rulePattern = Pattern.compile("(?<number>\\d+) (?<name>[\\w\\s]+) bag");
    private final List<String> input;

    public Day07(final List<String> input) {
        this.input = input;
    }

    static Map<String, Map<String, Integer>> extractRules(final List<String> input) {
        final Map<String, Map<String, Integer>> rules = new HashMap<>();

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

        return rules;
    }

    private static boolean containsShinyGold(final Map<String, Map<String, Integer>> rules, final String color) {
        if (rules.get(color).containsKey("shiny gold")) return true;
        if (rules.get(color).isEmpty()) return false;
        return rules.get(color).keySet().stream().anyMatch(key -> containsShinyGold(rules, key));
    }

    @Override
    public String solvePart1() {
        final Map<String, Map<String, Integer>> rules = extractRules(input);

        return "" + rules.keySet().stream().filter(key -> containsShinyGold(rules, key)).count();
    }

    private static int countBags(final Map<String, Map<String, Integer>> rules, final String key) {
        final Map<String, Integer> map = rules.get(key);
        return map.keySet().stream().mapToInt(
                k -> map.get(k) * countBags(rules, k)
        ).sum() + 1;
    }

    @Override
    public String solvePart2() {
        final Map<String, Map<String, Integer>> rules = extractRules(input);

        return "" + (countBags(rules, "shiny gold") - 1); //Don't count the shiny gold bag itself.

    }


}
