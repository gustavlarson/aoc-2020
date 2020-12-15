package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day15 implements Day {

    private final List<Long> input;

    public Day15(final List<String> input) {
        this.input = Arrays.stream(input.get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        long rounds[] = new long[2020];
        for (var i = 0; i < input.size(); i++) {
            rounds[i] = input.get(i);
        }
        for (var i = input.size(); i < rounds.length; i++) {
            var lastNumber = rounds[i - 1];
            long nextNumber;
            if (Arrays.stream(rounds).limit(i).filter(num -> num == lastNumber).count() <= 1) {
                nextNumber = 0;
            } else {
                long lastOccurrance = lastOccurrance(rounds, lastNumber, i);
                nextNumber = (i - 1) - lastOccurrance;
            }

            rounds[i] = nextNumber;
        }
        return rounds[rounds.length - 1];
    }

    private static long lastOccurrance(long[] rounds, long lastNumber, int i) {
        for (var j = i - 2; j >= 0; j--) {
            if (rounds[j] == lastNumber) return j;
        }
        throw new IllegalStateException();
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
