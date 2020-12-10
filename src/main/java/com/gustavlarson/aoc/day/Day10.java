package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day10 implements Day {

    private final List<Integer> input;

    public Day10(final List<String> input) {
        this.input = input.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        var one = 0;
        var three = 1; // Start with one
        input.add(0, 0);
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
        return 0;
    }
}
