package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.IntStream;

public class Day03 implements Day {

    @Override
    public String solvePart1(final List<String> input) {
        final int patternLength = input.get(0).length();
        return "" + IntStream.range(0, input.size()).filter(i -> input.get(i).charAt((i * 3) % patternLength) == '#').count();
        //return "0";
    }

    @Override
    public String solvePart2(final List<String> input) {
        final int patternLength = input.get(0).length();
        // {1, 2}
        // , {3, 1}, {5, 1}, {7, 1}
        final int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        long result = 1;
        for (final var slope : slopes) {
            final int right = slope[0];
            final int down = slope[1];
            final long trees = IntStream
                    .range(0, input.size())
                    .skip(1)
                    .filter(i -> (i) % down == 0)
                    .peek(i -> System.out.println(i + " " + input.get(i)))
                    .filter(i -> input.get(i).charAt(((i / down) * right) % patternLength) == '#')
                    .peek(i -> System.out.println(i))
                    .count();
            System.out.println(trees);
            result *= trees;
        }
        return "" + result;
    }
}
