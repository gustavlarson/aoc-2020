package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21Test {
    private static Day getDay(List<String> input) {
        return new Day21(input);
    }

    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                trh fvjkl sbzzf mxmxvkd (contains dairy)
                sqjhc fvjkl (contains soy)
                sqjhc mxmxvkd sbzzf (contains fish)""");
        final Day day = getDay(input);
        assertEquals(5, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                1
                2
                """);
        final Day day = getDay(input);
        assertEquals(0, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(21);
        final Day day = getDay(input);
        assertEquals(0, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(21);
        final Day day = getDay(input);
        assertEquals(0, day.solvePart2());
    }
}
