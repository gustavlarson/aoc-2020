package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day05 implements Day {

    @Override
    public String solvePart1(final List<String> input) {
        return "" + input.parallelStream().mapToInt(Day05::getSeatId).max().orElseThrow();
    }

    private static int getSeatId(final String boardingPass) {
        return 8 * getRow(boardingPass) + getColumn(boardingPass);
    }

    private static int getColumn(final String boardingPass) {
        final int[][] rows = {IntStream.range(0, 8).toArray()};
        boardingPass.chars().skip(7).forEach(c -> {
            if (c == 'L') {
                rows[0] = Arrays.stream(rows[0]).limit(rows[0].length / 2).toArray();
            } else if (c == 'R') {
                rows[0] = Arrays.stream(rows[0]).skip(rows[0].length / 2).toArray();
            }
        });

        return rows[0][0];
    }

    private static int getRow(final String boardingPass) {
        final int[][] rows = {IntStream.range(0, 128).toArray()};
        boardingPass.chars().limit(7).forEach(c -> {
            if (c == 'F') {
                rows[0] = Arrays.stream(rows[0]).limit(rows[0].length / 2).toArray();
            } else if (c == 'B') {
                rows[0] = Arrays.stream(rows[0]).skip(rows[0].length / 2).toArray();
            }
        });

        return rows[0][0];
    }

    @Override
    public String solvePart2(final List<String> input) {
        return null;
    }
}
