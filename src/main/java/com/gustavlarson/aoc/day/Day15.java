package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15 implements Day {

    private final List<Integer> input;

    public Day15(final List<String> input) {
        this.input = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        return solver(2020);

    }

    @Override
    public long solvePart2() {
        return solver(30000000);
    }

    private long solver(final int iterations) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (var i = 0; i < input.size(); i++) {
            cache.put(input.get(i), i);
        }

        var nextNumber = input.get(input.size() - 1);
        for (var i = input.size(); i < iterations; i++) {
            if (!cache.containsKey(nextNumber)) {
                cache.put(nextNumber, (i - 1));
                nextNumber = 0;
            } else {
                var diff = (i - 1) - cache.get(nextNumber);
                cache.put(nextNumber, (i - 1));
                nextNumber = diff;
            }
        }
        return nextNumber;
    }
}
