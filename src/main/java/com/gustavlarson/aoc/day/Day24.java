package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day24 implements Day {

    private static final Pattern p = Pattern.compile("(e|w|sw|se|nw|ne)");

    static class Tile {
        int x = 0;
        int y = 0;
        int z = 0;

        Tile(String input) {
            Matcher m = p.matcher(input);
            while (m.find()) {
                switch (m.group(1)) {
                    case "e" -> {
                        x++;
                        y--;
                    }
                    case "w" -> {
                        x--;
                        y++;
                    }
                    case "ne" -> {
                        x++;
                        z--;
                    }
                    case "sw" -> {
                        x--;
                        z++;
                    }
                    case "se" -> {
                        z++;
                        y--;
                    }
                    case "nw" -> {
                        z--;
                        y++;
                    }
                    default -> throw new IllegalArgumentException();
                }
                if (x + y + z != 0) throw new IllegalStateException("WTF");
            }
            if (x + y + z != 0) throw new IllegalStateException("WTF");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tile tile = (Tile) o;
            return x == tile.x &&
                    y == tile.y &&
                    z == tile.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }


    }

    private final List<Tile> input;

    public Day24(final List<String> input) {
        this.input = input.stream().map(Tile::new).collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        Map<Tile, Boolean> map = new HashMap<>();
        for (var tile : input) {
            var isFlipped = map.getOrDefault(tile, false);
            map.put(tile, !isFlipped);
        }
        return map.values().stream().filter(v -> v).count();
//        Map<Integer, List<Tile>> map = input.stream()
//                .collect(Collectors.groupingBy(Tile::hashCode));
//        return map
//                .values().stream()
//                .filter(tiles -> tiles.size() % 2 == 1)
//                .count();
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
