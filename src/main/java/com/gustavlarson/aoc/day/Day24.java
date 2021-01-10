package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day24 implements Day {

    private static final Pattern p = Pattern.compile("(e|w|sw|se|nw|ne)");

    static class Tile {
        int x = 0;
        int y = 0;
        int z = 0;

        Tile(int x, int y, int z) {
            if (x + y + z != 0) throw new IllegalStateException("Illegal state");
            this.x = x;
            this.y = y;
            this.z = z;
        }

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
                if (x + y + z != 0) throw new IllegalStateException("Illegal state");
            }
        }

        List<Tile> getNeighbours() {
            List<Tile> neighbours = new ArrayList<>();
            neighbours.add(new Tile(x + 1, y - 1, z));
            neighbours.add(new Tile(x - 1, y + 1, z));
            neighbours.add(new Tile(x + 1, y, z - 1));
            neighbours.add(new Tile(x - 1, y, z + 1));
            neighbours.add(new Tile(x, y + 1, z - 1));
            neighbours.add(new Tile(x, y - 1, z + 1));
            return neighbours;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tile tile = (Tile) o;
            return x == tile.x && y == tile.y && z == tile.z;
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
        Map<Tile, Boolean> tiles = new HashMap<>();
        for (var tile : input) {
            var isFlipped = tiles.getOrDefault(tile, false);
            tiles.put(tile, !isFlipped);
        }
        return tiles.values().stream().filter(v -> v).count();
    }

    @Override
    public long solvePart2() {
        Map<Tile, Boolean> tiles = new ConcurrentHashMap<>();
        for (var tile : input) {
            var isFlipped = tiles.getOrDefault(tile, false);
            tiles.put(tile, !isFlipped);
        }

        for (var i = 0; i < 100; i++) {
            Map<Tile, Boolean> newTiles = new ConcurrentHashMap<>();
            Map<Tile, Boolean> finalTiles = tiles;

            for (var tile : new ArrayList<>(tiles.keySet())) {
                tile.getNeighbours().forEach(neighbour -> finalTiles.putIfAbsent(neighbour, false));
            }

            for (var tile : finalTiles.keySet()) {
                newTiles.put(tile, tiles.get(tile));
                var blackNeighbours = tile.getNeighbours().stream().filter(neighbour -> finalTiles.getOrDefault(neighbour, false)).count();
                if (finalTiles.get(tile) && (blackNeighbours == 0 || blackNeighbours > 2)) {
                    newTiles.put(tile, false);
                }
                if (!finalTiles.get(tile) && blackNeighbours == 2) {
                    newTiles.put(tile, true);
                }
            }
            tiles = newTiles;
        }
        return tiles.values().stream().filter(v -> v).count();
    }
}
