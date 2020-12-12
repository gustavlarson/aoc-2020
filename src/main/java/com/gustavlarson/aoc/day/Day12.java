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

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int manhattanDistance() {
            return Math.abs(x) + Math.abs(y);
        }
    }

    private final List<Instruction> input;

    public Day12(final List<String> input) {
        this.input = input.stream().map(Instruction::new).collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        Point position = new Point(0, 0);
        int direction = 90;
        for (final var instruction : input) {
            switch (instruction.action) {
                case NORTH -> position.y += instruction.value;
                case SOUTH -> position.y -= instruction.value;
                case EAST -> position.x += instruction.value;
                case WEST -> position.x -= instruction.value;
                case FORWARD -> {
                    switch (direction % 360) {
                        case 0 -> position.y += instruction.value;
                        case 180 -> position.y -= instruction.value;
                        case 90 -> position.x += instruction.value;
                        case 270 -> position.x -= instruction.value;
                        default -> throw new IllegalStateException();
                    }
                }
                case RIGHT -> direction += instruction.value;
                case LEFT -> direction -= instruction.value;
            }
        }
        return position.manhattanDistance();
    }

    @Override
    public long solvePart2() {
        Point shipPosition = new Point(0, 0);
        Point waypoint = new Point(10, 1);

        for (final var instruction : input) {
            switch (instruction.action) {
                case NORTH -> waypoint.y += instruction.value;
                case SOUTH -> waypoint.y -= instruction.value;
                case EAST -> waypoint.x += instruction.value;
                case WEST -> waypoint.x -= instruction.value;
                case FORWARD -> {
                    shipPosition.x += waypoint.x * instruction.value;
                    shipPosition.y += waypoint.y * instruction.value;
                }
                case RIGHT -> {
                    for (var i = 0; i < instruction.value / 90; i++) {
                        var tmp = -waypoint.x;
                        waypoint.x = waypoint.y;
                        waypoint.y = tmp;
                    }
                }
                case LEFT -> {
                    for (var i = 0; i < instruction.value / 90; i++) {
                        var tmp = waypoint.x;
                        waypoint.x = -waypoint.y;
                        waypoint.y = tmp;
                    }
                }
            }
        }
        return shipPosition.manhattanDistance();
    }
}
