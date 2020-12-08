package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day08 implements Day {

    private static final Pattern pattern = Pattern.compile("(?<operation>nop|acc|jmp) (?<argument>\\+?-?\\d+)");

    static class Instruction {
        enum Operation {
            NOP, ACC, JMP
        }

        private Operation operation;
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

    static class Result {
        final int accumulator;
        final boolean infiniteLoop;

        Result(final boolean infiniteLoop, final int accumulator) {
            this.infiniteLoop = infiniteLoop;
            this.accumulator = accumulator;
        }
    }

    private final List<Instruction> input;

    public Day08(final List<String> input) {
        this.input = input.stream().map(Instruction::new).collect(Collectors.toList());
    }

    private static Result executeInstructions(final List<Instruction> input) {
        int accumulator = 0;
        int instructionPointer = 0;
        while (instructionPointer < input.size()) {
            final Instruction currentInstruction = input.get(instructionPointer);
            if (currentInstruction.executed) {
                return new Result(true, accumulator);
            }
            currentInstruction.executed = true;
            switch (currentInstruction.operation) {
                case NOP -> instructionPointer++;
                case JMP -> instructionPointer += currentInstruction.argument;
                case ACC -> {
                    accumulator += currentInstruction.argument;
                    instructionPointer++;
                }
            }
        }
        return new Result(false, accumulator);
    }

    private void reset() {
        input.stream().parallel().forEach(instruction -> instruction.executed = false);
    }

    @Override
    public long solvePart1() {
        reset();
        return executeInstructions(input).accumulator;
    }

    @Override
    public long solvePart2() {
        final Result validResult = input.stream()
                .filter(instruction -> instruction.operation != Instruction.Operation.ACC)
                .map(instruction -> {
                    reset();
                    instruction.flipOperation();
                    final Result result = executeInstructions(input);
                    instruction.flipOperation();
                    return result;
                })
                .filter(result -> !result.infiniteLoop)
                .findAny().orElseThrow();
        return validResult.accumulator;
    }

}
