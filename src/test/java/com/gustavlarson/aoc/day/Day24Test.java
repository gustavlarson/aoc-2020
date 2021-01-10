package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustavlarson.aoc.day.TestHelper.getAsList;
import static com.gustavlarson.aoc.day.TestHelper.getFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day24Test {

    public static final List<String> INPUT = getAsList("""
            sesenwnenenewseeswwswswwnenewsewsw
            neeenesenwnwwswnenewnwwsewnenwseswesw
            seswneswswsenwwnwse
            nwnwneseeswswnenewneswwnewseswneseene
            swweswneswnenwsewnwneneseenw
            eesenwseswswnenwswnwnwsewwnwsene
            sewnenenenesenwsewnenwwwse
            wenwwweseeeweswwwnwwe
            wsweesenenewnwwnwsenewsenwwsesesenwne
            neeswseenwwswnwswswnw
            nenwswwsewswnenenewsenwsenwnesesenew
            enewnwewneswsewnwswenweswnenwsenwsw
            sweneswneswneneenwnewenewwneswswnese
            swwesenesewenwneswnwwneseswwne
            enesenwswwswneneswsenwnewswseenwsese
            wnwnesenesenenwwnenwsewesewsesesew
            nenewswnwewswnenesenwnesewesw
            eneswnwswnwsenenwnwnwwseeswneewsenese
            neswnwewnwnwseenwseesewsenwsweewe
            wseweeenwnesenwwwswnew""");

    private static Day getDay(List<String> input) {
        return new Day24(input);
    }

    @Test
    public void testPart101() {
        final List<String> input = getAsList("""
                sesenwnenenewseeswwswswwnenewsewsw
                senenenewseeswwswswwnenewsewsw
                neeenesenwnwwswnenewnwwsewnenwseswesw
                """);
        final Day day = getDay(input);
        assertEquals(1, day.solvePart1());
    }

    @Test
    public void testPart102() {
        final List<String> input = getAsList("""
                sesenwnenenewseeswwswswwnenewsewsw
                senenenewseeswwswswwnenewsewsw
                sesenwnenenewseeswwswswwnenewsewsw
                neeenesenwnwwswnenewnwwsewnenwseswesw
                """);
        final Day day = getDay(input);
        assertEquals(2, day.solvePart1());
    }

    @Test
    public void testPart103() {
        final Day day = getDay(INPUT);
        assertEquals(10, day.solvePart1());
    }

    @Test
    public void testPart201() {
        final Day day = getDay(INPUT);
        assertEquals(2208, day.solvePart2());
    }
    
    @Test
    public void testPart1Real() {
        final List<String> input = getFromFile(24);
        final Day day = getDay(input);
        assertEquals(512, day.solvePart1());
    }

    @Test
    public void testPart2Real() {
        final List<String> input = getFromFile(24);
        final Day day = getDay(input);
        assertEquals(4120, day.solvePart2());
    }
}
