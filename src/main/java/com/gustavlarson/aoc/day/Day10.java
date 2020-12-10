package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 implements Day {

    private final List<Integer> input;

    public Day10(final List<String> input) {
        this.input = input.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        this.input.add(0, 0);
    }

    @Override
    public long solvePart1() {
        var one = 0;
        var three = 1; // Start with one
        for (var i = 1; i < input.size(); i++) {
            switch (input.get(i) - input.get(i - 1)) {
                case 1:
                    one++;
                    break;
                case 2:
                    break;
                case 3:
                    three++;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return one * three;
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
