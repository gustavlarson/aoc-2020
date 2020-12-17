package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.*;

public class Day17 implements Day {

    private final static int CYCLES = 6;

    class ActiveCell {
        final int x, y, z, w;

        ActiveCell(int x, int y, int z, int w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ActiveCell that = (ActiveCell) o;
            return x == that.x &&
                    y == that.y &&
                    z == that.z &&
                    w == that.w;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, w);
        }
    }

    Set<ActiveCell> initialState = new HashSet<>();

    public Day17(final List<String> input) {
        for (var y = 0; y < input.size(); y++) {
            for (var x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == '#') {
                    initialState.add(new ActiveCell(x, y, 0, 0));
                }
            }
        }
    }

    @Override
    public long solvePart1() {
        Set<ActiveCell> activeCells = initialState;

        for (var i = 0; i < CYCLES; i++) {
            activeCells = getNextState(activeCells, false);
        }

        return activeCells.size();
    }

    private Set<ActiveCell> getNextState(final Set<ActiveCell> activeCells, boolean fourthDimension) {
        final Set<ActiveCell> nextState = new HashSet<>();

        for (var activeCell : activeCells) {
            var neighBours = getNeighbours(activeCell, activeCells, fourthDimension);
            for (var cell : neighBours.keySet()) {
                var count = neighBours.get(cell);
                if (activeCells.contains(cell)) {
                    if (count == 2 || count == 3) nextState.add(cell);
                } else {
                    if (count == 3) nextState.add(cell);
                }
            }
        }

        return nextState;
    }

    private Map<ActiveCell, Integer> getNeighbours(ActiveCell activeCell, Set<ActiveCell> activeCells, boolean fourthDimension) {
        Map<ActiveCell, Integer> result = new HashMap<>();
        for (var i = -1; i <= 1; i++) {
            for (var j = -1; j <= 1; j++) {
                for (var k = -1; k <= 1; k++) {
                    if (fourthDimension) {
                        for (var l = -1; l <= 1; l++) {
                            var neighbour = new ActiveCell(i + activeCell.x, j + activeCell.y, k + activeCell.z, l + activeCell.w);
                            var count = countActiveNeighbours(neighbour, activeCells, fourthDimension);
                            result.put(neighbour, count);
                        }
                    } else {
                        var neighbour = new ActiveCell(i + activeCell.x, j + activeCell.y, k + activeCell.z, 0);
                        var count = countActiveNeighbours(neighbour, activeCells, fourthDimension);
                        result.put(neighbour, count);
                    }
                }
            }
        }
        return result;
    }

    private int countActiveNeighbours(ActiveCell activeCell, Set<ActiveCell> activeCells, boolean fourthDimension) {
        var count = 0;

        for (var i = -1; i <= 1; i++) {
            for (var j = -1; j <= 1; j++) {
                for (var k = -1; k <= 1; k++) {
                    if (fourthDimension) {
                        for (var l = -1; l <= 1; l++) {
                            if (i == 0 && j == 0 && k == 0 && l == 0) continue;
                            var neighbour = new ActiveCell(i + activeCell.x, j + activeCell.y, k + activeCell.z, l + activeCell.w);
                            if (activeCells.contains(neighbour)) count++;
                        }
                    } else {
                        if (i == 0 && j == 0 && k == 0) continue;
                        var neighbour = new ActiveCell(i + activeCell.x, j + activeCell.y, k + activeCell.z, 0);
                        if (activeCells.contains(neighbour)) count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public long solvePart2() {
        Set<ActiveCell> activeCells = initialState;

        for (var i = 0; i < CYCLES; i++) {
            activeCells = getNextState(activeCells, true);
        }

        return activeCells.size();
    }
}
