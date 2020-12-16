package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Override
    public long solvePart1() {
        return nearbyTickets.stream().mapToInt(ticket -> ticket.fields.stream().mapToInt(Integer::intValue).filter(value -> !isValid(value)).sum()).sum();
    }

    private boolean isValid(int value) {
        for (var rule : rules) {
            for (var valid : rule.validInputs) {
                if (value >= valid.min && value <= valid.max) return true;
            }
        }
        return false;
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
