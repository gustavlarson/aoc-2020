package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 implements Day {

    private final List<Integer> input;

    public Day10(final List<String> input) {
        this.input = input.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        this.input.add(0, 0); // Add start outlet
        this.input.add(this.input.size(), this.input.get(this.input.size() - 1) + 3); //Add built-in adapter
    }

    @Override
    public long solvePart1() {
        final List<Integer> diffs = new ArrayList<>();
        for (var i = 1; i < input.size(); i++) {
            diffs.add(input.get(i) - input.get(i - 1));
        }
        final var ones = diffs.stream().filter(i -> i == 1).count();
        final var threes = diffs.stream().filter(i -> i == 3).count();
        return ones * threes;
    }

    @Override
    public long solvePart2() {
        final long[] dp = new long[input.size()];
        Arrays.fill(dp, -1);
        return count(0, dp);
    }

    private long count(final int index, final long[] dp) {
        if (index == input.size() - 1) {
            return 1;
        }

        if (dp[index] != -1) {
            return dp[index];
        } else {
            long count = 0;
            for (var i = index + 1; i <= Math.min(index + 3, input.size() - 1); i++) {
                if (input.get(index) + 3 >= input.get(i)) {
                    count += count(i, dp);
                }
            }
            dp[index] = count;
            return count;
        }

    }
}
