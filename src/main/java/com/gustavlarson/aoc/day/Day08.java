package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day08 implements Day {

    private static final Pattern pattern = Pattern.compile("(?<operation>nop|acc|jmp) (?<argument>\\+?-?\\d+)");

    enum Operation {
        NOP, ACC, JMP
    }

    class Instruction {
        final Operation operation;
        final int argument;
        boolean executed = false;

        Instruction(final Operation operation, final int argument) {
            this.operation = operation;
            this.argument = argument;
        }

        Instruction(final String input) {
            final Matcher m = pattern.matcher(input);
            if (!m.find()) {
                throw new IllegalArgumentException();
            }
            operation = switch (m.group("operation")) {
                case "nop" -> Operation.NOP;
                case "acc" -> Operation.ACC;
                case "jmp" -> Operation.JMP;
                default -> throw new IllegalArgumentException();
            };
            argument = Integer.parseInt(m.group("argument"));
        }
    }

    private final List<Instruction> input;

    public Day08(final List<String> input) {
        this.input = input.stream().map(Instruction::new).collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        int counter = 0;
        int instructionPointer = 0;
        while (true) {
            final Instruction currentInstruction = input.get(instructionPointer);
            if (currentInstruction.executed) {
                break;
            }
            currentInstruction.executed = true;
            switch (currentInstruction.operation) {
                case NOP -> instructionPointer++;
                case JMP -> instructionPointer += currentInstruction.argument;
                case ACC -> {
                    counter += currentInstruction.argument;
                    instructionPointer++;
                }
            }
        }
        return counter;
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
