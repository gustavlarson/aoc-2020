package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day20 implements Day {

    private final List<Tile> tiles = new ArrayList<>();
    Pattern TILE_HEADER = Pattern.compile("Tile (\\d+):");

    public Day20(final List<String> input) {

        List<char[]> grid = new ArrayList<>();
        var id = 0;
        for (var line : input) {
            if (line.isEmpty()) {
                tiles.add(new Tile(id, grid));
                continue;
            }
            if (line.startsWith("Tile")) {
                Matcher m = TILE_HEADER.matcher(line);
                if (!m.find()) throw new IllegalArgumentException();
                id = Integer.parseInt(m.group(1));
                grid = new ArrayList<>();
                continue;
            }
            grid.add(line.toCharArray());
        }

        createImage();
    }

    static class Tile {
        private static final int SIZE = 10;

        public final int id;
        public Tile up;
        public Tile right;
        public Tile down;
        public Tile left;

        private boolean[][] grid = new boolean[SIZE][SIZE];

        public Tile(int id, List<char[]> grid) {
            this.id = id;
            for (int x = 0; x < SIZE; x++) {
                for (int y = 0; y < SIZE; y++) {
                    this.grid[x][y] = grid.get(x)[y] == '#';
                }
            }
        }

        boolean isCorner() {
            var count = 0;
            if (up != null) count++;
            if (right != null) count++;
            if (down != null) count++;
            if (left != null) count++;
            return count == 2;
        }

        private void rotate() {
            final boolean[][] rotatedGrid = new boolean[SIZE][SIZE];
            for (var i = 0; i < SIZE; i++) {
                for (var j = 0; j < SIZE; j++) {
                    rotatedGrid[i][j] = grid[SIZE - 1 - j][i];
                }
            }
            grid = rotatedGrid;
        }

        private void flip() {
            for (var i = 0; i <= SIZE / 2 - 1; i++) {
                final boolean[] tmp = grid[i];
                grid[i] = grid[SIZE - 1 - i];
                grid[SIZE - 1 - i] = tmp;
            }
        }

        public boolean findMatch(Tile tile) {
            if (up == null && matchesUp(tile)) {
                up = tile;
                tile.down = this;

                if (left != null && left.up != null) {
                    tile.left = left.up;
                    left.up.right = tile;
                }

                if (tile.left != null && tile.left.down != null) {
                    left = tile.left.down;
                    tile.left.down.right = this;
                }

                if (right != null && right.up != null) {
                    tile.right = right.up;
                    right.up.left = tile;
                }

                if (tile.right != null && tile.right.down != null) {
                    right = tile.right.down;
                    tile.right.down.left = this;
                }

                return true;
            }

            if (right == null && matchesRight(tile)) {
                right = tile;
                tile.left = this;

                if (up != null && up.right != null) {
                    tile.up = up.right;
                    up.right.down = tile;
                }

                if (tile.up != null && tile.up.left != null) {
                    up = tile.up.left;
                    tile.up.left.down = this;
                }

                if (down != null && down.right != null) {
                    tile.down = down.right;
                    down.right.up = tile;
                }

                if (tile.down != null && tile.down.left != null) {
                    down = tile.down.left;
                    tile.down.left.up = this;
                }

                return true;
            }


            if (down == null && matchesDown(tile)) {
                down = tile;
                tile.up = this;

                if (left != null && left.down != null) {
                    tile.left = left.down;
                    left.down.right = tile;
                }

                if (tile.left != null && tile.left.up != null) {
                    left = tile.left.up;
                    tile.left.up.right = this;
                }

                if (right != null && right.down != null) {
                    tile.right = right.down;
                    right.down.left = tile;
                }

                if (tile.right != null && tile.right.up != null) {
                    right = tile.right.up;
                    tile.right.up.left = this;
                }

                return true;
            }

            if (left == null && matchesLeft(tile)) {
                left = tile;
                tile.right = this;

                if (up != null && up.left != null) {
                    tile.up = up.left;
                    up.left.down = tile;
                }

                if (tile.up != null && tile.up.right != null) {
                    up = tile.up.right;
                    tile.up.right.down = this;
                }

                if (down != null && down.left != null) {
                    tile.down = down.left;
                    down.left.up = tile;
                }

                if (tile.down != null && tile.down.right != null) {
                    down = tile.down.right;
                    tile.down.right.up = this;
                }

                return true;
            }

            return false;
        }

        private boolean matchesUp(Tile tile) {
            for (int i = 0; i < SIZE; i++) {
                if (grid[0][i] != tile.grid[SIZE - 1][i]) return false;
            }
            return true;
        }

        private boolean matchesRight(Tile tile) {
            for (int i = 0; i < SIZE; i++) {
                if (grid[i][SIZE - 1] != tile.grid[i][0]) return false;
            }
            return true;
        }

        private boolean matchesDown(Tile tile) {
            for (int i = 0; i < SIZE; i++) {
                if (grid[SIZE - 1][i] != tile.grid[0][i]) return false;
            }
            return true;
        }

        private boolean matchesLeft(Tile tile) {
            for (int i = 0; i < SIZE; i++) {
                if (grid[i][0] != tile.grid[i][SIZE - 1]) return false;
            }
            return true;
        }
    }

    void createImage() {
        final Tile first = tiles.get(0);
        final Set<Tile> loose = Collections.newSetFromMap(new ConcurrentHashMap<>());
        loose.addAll(tiles);
        loose.remove(first);
        final Deque<Tile> queue = new ConcurrentLinkedDeque<>();
        queue.add(first);
        while (!queue.isEmpty()) {
            final Tile current = queue.pop();
            for (var tile : loose) {
                var id = tile.id;
                System.out.println(id);
                for (var i = 0; i < 2; i++) {

                    tile.flip();
                    for (int j = 0; j < 4; j++) {
                        if (current.findMatch(tile)) {
                            loose.remove(tile);
                            queue.add(tile);
                            i = 2;
                            break;
                        }
                        tile.rotate();
                    }
                }
            }
        }
    }

    @Override
    public long solvePart1() {
        return tiles.stream()
                .filter(Tile::isCorner)
                .mapToLong(tile -> tile.id)
                .peek(System.out::println)
                .reduce((a, b) -> a *= b).orElseThrow();
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
