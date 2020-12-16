package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    public static final List<String> INPUT1 = getAsList("""
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
    public static final List<String> INPUT2 = getAsList("""
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

    @Test
    public void testPart101() {
        final List<String> input = INPUT1;
        final Day day = new Day10(input);
        assertEquals(5 * 7, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = INPUT2;
        final Day day = new Day10(input);
        assertEquals(22 * 10, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = INPUT1;
        final Day day = new Day10(input);
        assertEquals(8, day.solvePart2());
    }

    @Test
    public void testPart202() {
        final List<String> input = INPUT2;
        final Day day = new Day10(input);
        assertEquals(19208, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(10);
        final Day day = new Day10(input);
        assertEquals(1848, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(10);
        final Day day = new Day10(input);
        assertEquals(8099130339328L, day.solvePart2());
    }
}
