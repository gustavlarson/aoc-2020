package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day12 implements Day {
    private final Pattern pattern = Pattern.compile("(?<action>[NSEWLRF])(?<value>\\d+)");

    enum Action {
        NORTH, SOUTH, EAST, WEST, LEFT, RIGHT, FORWARD
    }

    class Instruction {
        private final Action action;
        private final int value;

        public Instruction(final String instruction) {
            final Matcher matcher = pattern.matcher(instruction);
            if (!matcher.find()) throw new IllegalArgumentException();
            action = switch (matcher.group("action")) {
                case "N" -> Action.NORTH;
                case "S" -> Action.SOUTH;
                case "E" -> Action.EAST;
                case "W" -> Action.WEST;
                case "L" -> Action.LEFT;
                case "R" -> Action.RIGHT;
                case "F" -> Action.FORWARD;
                default -> throw new IllegalStateException("Unexpected value: " + matcher.group("action"));
            };
            value = Integer.parseInt(matcher.group("value"));
        }
    }

    private final List<Instruction> input;

    public Day12(final List<String> input) {
        this.input = input.stream().map(Instruction::new).collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        int x = 0;
        int y = 0;
        int direction = 90;
        for (final var instruction : input) {
            switch (instruction.action) {
                case NORTH -> y += instruction.value;
                case SOUTH -> y -= instruction.value;
                case EAST -> x += instruction.value;
                case WEST -> x -= instruction.value;
                case FORWARD -> {
                    switch (direction % 360) {
                        case 0 -> y += instruction.value;
                        case 180 -> y -= instruction.value;
                        case 90 -> x += instruction.value;
                        case 270 -> x -= instruction.value;
                        default -> throw new IllegalStateException();
                    }
                }
                case RIGHT -> direction += instruction.value;
                case LEFT -> direction -= instruction.value;
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
