package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {
    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                16
                10
                15
                5
                1
                11
                7
                19
                6
                12
                4""");
        final Day day = new Day10(input);
        assertEquals(5 * 7, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = getAsList("""
                28
                33
                18
                42
                31
                14
                46
                20
                48
                47
                24
                23
                49
                45
                19
                38
                39
                11
                1
                32
                25
                35
                8
                17
                7
                9
                4
                2
                34
                10
                3""");
        final Day day = new Day10(input);
        assertEquals(22 * 10, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                1
                2
                """);
        final Day day = new Day10(input);
        assertEquals(0, day.solvePart2());
    }

}
