package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20Test {
    private static Day getDay(List<String> input) {
        return new Day20(input);
    }

    private static final List<String> INPUT;

    static {
        INPUT = new ArrayList<>(getAsList("""
                Tile 2311:
                ..##.#..#.
                ##..#.....
                #...##..#.
                ####.#...#
                ##.##.###.
                ##...#.###
                .#.#.#..##
                ..#....#..
                ###...#.#.
                ..###..###
                            
                Tile 1951:
                #.##...##.
                #.####...#
                .....#..##
                #...######
                .##.#....#
                .###.#####
                ###.##.##.
                .###....#.
                ..#.#..#.#
                #...##.#..
                            
                Tile 1171:
                ####...##.
                #..##.#..#
                ##.#..#.#.
                .###.####.
                ..###.####
                .##....##.
                .#...####.
                #.##.####.
                ####..#...
                .....##...
                            
                Tile 1427:
                ###.##.#..
                .#..#.##..
                .#.##.#..#
                #.#.#.##.#
                ....#...##
                ...##..##.
                ...#.#####
                .#.####.#.
                ..#..###.#
                ..##.#..#.
                            
                Tile 1489:
                ##.#.#....
                ..##...#..
                .##..##...
                ..#...#...
                #####...#.
                #..#.#.#.#
                ...#.#.#..
                ##.#...##.
                ..##.##.##
                ###.##.#..
                            
                Tile 2473:
                #....####.
                #..#.##...
                #.##..#...
                ######.#.#
                .#...#.#.#
                .#########
                .###.#..#.
                ########.#
                ##...##.#.
                ..###.#.#.
                            
                Tile 2971:
                ..#.#....#
                #...###...
                #.#.###...
                ##.##..#..
                .#####..##
                .#..####.#
                #..#.#..#.
                ..####.###
                ..#.#.###.
                ...#.#.#.#
                            
                Tile 2729:
                ...#.#.#.#
                ####.#....
                ..#.#.....
                ....#..#.#
                .##..##.#.
                .#.####...
                ####.#.#..
                ##.####...
                ##..#.##..
                #.##...##.
                            
                Tile 3079:
                #.#.#####.
                .#..######
                ..#.......
                ######....
                ####.#..#.
                .#...#.##.
                #.#####.##
                ..#.###...
                ..#.......
                ..#.###..."""));
        INPUT.add("");
    }

    @Test
    public void testPart101() {
        final Day day = getDay(INPUT);
        assertEquals(20899048083289L, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final Day day = getDay(INPUT);
        assertEquals(273, day.solvePart2());
    }

    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(20);
        final Day day = getDay(input);
        assertEquals(15003787688423L, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(20);
        final Day day = getDay(input);
        assertEquals(1705, day.solvePart2());
    }
}
