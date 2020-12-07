package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;

public class Day05 implements Day {
    private final List<String> input;

    public Day05(final List<String> input) {
        this.input = input;
    }

    @Override
    public long solvePart1() {
        return input.parallelStream().mapToInt(Day05::getSeatId).max().orElseThrow();
    }

    private static int getSeatId(final String boardingPass) {
        return Integer.parseInt(toBinary(boardingPass), 2);
    }

    private static String toBinary(final String boardingPass) {
        return boardingPass
                .replace('F', '0')
                .replace('B', '1')
                .replace('R', '1')
                .replace('L', '0');
    }

    @Override
    public long solvePart2() {
        final int[] arr = input.parallelStream().mapToInt(Day05::getSeatId).sorted().toArray();
        for (var i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) == 2) {
                return (arr[i] + arr[i + 1]) / 2;
            }
        }
        throw new IllegalArgumentException();
    }
}
