package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.List;
import java.util.stream.IntStream;

public class Day03 implements Day {

    @Override
    public String solvePart1(final List<String> input) {
        final int patternLength = input.get(0).length();
        return "" + IntStream.range(0, input.size()).filter(i -> input.get(i).charAt((i * 3) % patternLength) == '#'
        ).count();
        //return "0";
    }

    @Override
    public String solvePart2(final List<String> input) {
        return null;
    }
}
