package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day01Test {
    @Test
    public void test01() {
        final Day day = new Day01();
        final List<String> input = List.of("1721", "299");
        final String result = day.solvePart1(input);
        assertEquals(1721 * 299, Integer.parseInt(result));
    }

    @Test
    public void test02() {
        final Day day = new Day01();
        final List<String> input = List.of("1010", "1010");
        final String result = day.solvePart1(input);
        assertEquals(1010 * 1010, Integer.parseInt(result));
    }

    @Test
    public void test03() {
        final Day day = new Day01();
        final List<String> input = List.of("1721", "979", "366", "299", "675", "1456");
        final String result = day.solvePart1(input);
        assertEquals(1721 * 299, Integer.parseInt(result));
    }

    @Test
    public void test04() {
        final Day day = new Day01();
        final List<String> input = List.of("1721", "979", "366", "299", "675", "1456");
        final String result = day.solvePart2(input);
        assertEquals(979 * 366 * 675, Integer.parseInt(result));
    }
}
