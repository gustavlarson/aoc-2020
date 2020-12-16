package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {
    @Test
    public void testPart101() {
        final List<String> input = List.of("bright white bags contain 1 shiny gold bag.");
        final Day day = new Day07(input);
        assertEquals(1, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = List.of(
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "dotted black bags contain no other bags.");
        final Day day = new Day07(input);
        assertEquals(2, day.solvePart1());
    }

    @Test
    public void testPart103() {
        final List<String> input = getAsList("""
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.""");
        final Day day = new Day07(input);
        assertEquals(4, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final List<String> input = getAsList("""
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.""");
        final Day day = new Day07(input);
        assertEquals(32, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(7);
        final Day day = new Day07(input);
        assertEquals(289, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(7);
        final Day day = new Day07(input);
        assertEquals(30055, day.solvePart2());
    }

}
