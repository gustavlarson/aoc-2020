package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {
    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                1
                2
                3
                4
                5
                6
                7
                8
                9
                10
                11
                12
                13
                14
                15
                16
                17
                18
                19
                20
                21
                22
                23
                24
                25
                26
                50
                99""");
        final Day day = new Day09(input);
        assertEquals(99, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                1
                2
                3
                4
                5
                6
                7
                8
                9
                10
                11
                12
                13
                14
                15
                16
                17
                18
                19
                20
                21
                22
                23
                24
                25
                26
                50
                99""");
        final Day day = new Day09(input);
        assertEquals(18, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(9);
        final Day day = new Day09(input);
        assertEquals(26796446, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(9);
        final Day day = new Day09(input);
        assertEquals(3353494, day.solvePart2());
    }
}
