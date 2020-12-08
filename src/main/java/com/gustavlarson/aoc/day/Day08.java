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
        Operation operation;
        final int argument;
        boolean executed = false;

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

        void flipOperation() {
            operation = switch (operation) {
                case JMP -> Operation.NOP;
                case NOP -> Operation.JMP;
                default -> operation;
            };
        }
    }

    private final List<Instruction> input;

    public Day08(final List<String> input) {
        this.input = input.stream().map(Instruction::new).collect(Collectors.toList());
    }

    private long getAccumulator(final List<Instruction> input) {
        int counter = 0;
        int instructionPointer = 0;
        while (instructionPointer < input.size()) {
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

    private void reset() {
        input.stream().parallel().forEach(instruction -> instruction.executed = false);
    }

    @Override
    public long solvePart1() {
        reset();
        return getAccumulator(input);
    }

    private boolean isInfiniteLoop(final List<Instruction> instructions) {
        int counter = 0;
        int instructionPointer = 0;
        while (instructionPointer < instructions.size()) {
            final Instruction currentInstruction = input.get(instructionPointer);
            if (currentInstruction.executed) {
                return true;
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
        return false;
    }

    @Override
    public long solvePart2() {
        for (var i = 0; i < input.size(); i++) {
            reset();
            input.get(i).flipOperation();
            if (!isInfiniteLoop(input)) {
                reset();
                return getAccumulator(input);
            }
            input.get(i).flipOperation();
        }
        throw new IllegalStateException();
    }

}
