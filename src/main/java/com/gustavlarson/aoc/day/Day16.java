package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day16 implements Day {

    private final List<Rule> rules;
    private final Ticket myTicket;
    private final List<Ticket> nearbyTickets;

    class ValidInput {
        final int min;
        final int max;

        ValidInput(final int min, final int max) {
            this.min = min;
            this.max = max;
        }

        ValidInput(final String min, final String max) {
            this(Integer.parseInt(min), Integer.parseInt(max));
        }
    }

    Pattern rulePattern = Pattern.compile("(.*): (\\d+)-(\\d+) or (\\d+)-(\\d+)");

    class Rule {
        private final String field;
        private final List<ValidInput> validInputs = new ArrayList<>();

        Rule(String line) {
            Matcher m = rulePattern.matcher(line);
            if (!m.find()) throw new IllegalArgumentException();
            field = m.group(1);
            validInputs.add(new ValidInput(m.group(2), m.group(3)));
            validInputs.add(new ValidInput(m.group(4), m.group(5)));
        }
    }

    class Ticket {
        private final List<Integer> fields;

        Ticket(String line) {
            fields = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        }
    }

    public Day16(final List<String> input) {
        rules = input.stream().takeWhile(line -> line.length() > 0).map(Rule::new).collect(Collectors.toList());
        myTicket = new Ticket(input.stream().dropWhile(line -> line.length() > 0).skip(2).findFirst().orElseThrow());
        nearbyTickets = input.stream().dropWhile(line -> line.length() > 0).skip(5).map(Ticket::new).collect(Collectors.toList());
    }

    private ToIntFunction<Ticket> sumOfInvalidFields() {
        return ticket -> ticket.fields.stream()
                .mapToInt(Integer::intValue)
                .filter(value -> !isValid(value))
                .sum();
    }

    @Override
    public long solvePart1() {
        return nearbyTickets.stream().parallel()
                .mapToInt(sumOfInvalidFields())
                .sum();
    }

    private boolean isValid(int value) {
        for (var rule : rules) {
            if (isValid(rule, value)) return true;
        }
        return false;
    }

    private boolean isValid(Rule rule, int value) {
        for (var valid : rule.validInputs) {
            if (value >= valid.min && value <= valid.max) return true;
        }
        return false;
    }

    private boolean isValid(Ticket ticket) {
        return ticket.fields.stream().mapToInt(Integer::intValue).allMatch(this::isValid);
    }

    @Override
    public long solvePart2() {
        var validTickets = nearbyTickets.stream().filter(this::isValid).collect(Collectors.toList());
        var possiblePositions = getPossiblePositions(validTickets);

        reducePositions(possiblePositions);

        return rules.stream().parallel()
                .filter(rule -> rule.field.startsWith("departure"))
                .mapToLong(rule -> myTicket.fields.get(possiblePositions.get(rule).get(0)))
                .reduce(1, (a, b) -> a * b);
    }

    private Map<Rule, List<Integer>> getPossiblePositions(final List<Ticket> validTickets) {
        Map<Rule, List<Integer>> possiblePositions = new HashMap<>();
        for (var rule : rules) {
            List<Integer> possibilities = new ArrayList<>();
            for (var i = 0; i < myTicket.fields.size(); i++) {
                if (!allTicketsMatchRule(validTickets, rule, i)) continue;
                possibilities.add(i);
            }
            possiblePositions.put(rule, possibilities);
        }
        return possiblePositions;
    }

    private boolean allTicketsMatchRule(final List<Ticket> validTickets, final Rule rule, final int field) {
        for (var ticket : validTickets) {
            if (!isValid(rule, ticket.fields.get(field))) {
                return false;
            }
        }
        return isValid(rule, myTicket.fields.get(field));
    }

    private void reducePositions(final Map<Rule, List<Integer>> possiblePositions) {
        var loop = true;
        while (loop) {
            loop = false;
            for (var rule : rules) {
                if (possiblePositions.get(rule).size() == 1) {
                    var position = possiblePositions.get(rule).get(0);
                    removePossibilities(possiblePositions, position);
                } else {
                    loop = true;
                }
            }
        }
    }

    private void removePossibilities(final Map<Rule, List<Integer>> possiblePositions, final Integer position) {
        for (var rule : rules) {
            if (possiblePositions.get(rule).size() > 1) {
                possiblePositions.get(rule).remove(position);
            }
        }
    }
}
