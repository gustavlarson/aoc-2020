package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19Test {

    public static final List<String> INPUT = getAsList("""
            0: 4 1 5
            1: 2 3 | 3 2
            2: 4 4 | 5 5
            3: 4 5 | 5 4
            4: "a"
            5: "b"

            ababbb
            bababa
            abbbab
            aaabbb
            aaaabbb""");

    private static Day getDay(List<String> input) {
        return new Day19(input);
    }

    @Test
    public void testPart101() {
        final Day day = getDay(INPUT);
        assertEquals(2, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = INPUT;
        final Day day = getDay(input);
        assertEquals(0, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(19);
        final Day day = getDay(input);
        assertEquals(198, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(19);
        final Day day = getDay(input);
        assertEquals(0, day.solvePart2());
    }
}
