package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.*;

public class Day17 implements Day {

    private final static int CYCLES = 6;

    class ActiveCell {
        int x, y, z;

        ActiveCell(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ActiveCell that = (ActiveCell) o;
            return x == that.x &&
                    y == that.y &&
                    z == that.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }

    Set<ActiveCell> initialState = new HashSet<>();

    public Day17(final List<String> input) {
        for (var y = 0; y < input.size(); y++) {
            for (var x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == '#') {
                    initialState.add(new ActiveCell(x, y, 0));
                }
            }
        }
    }

    @Override
    public long solvePart1() {
        Set<ActiveCell> activeCells = initialState;

        for (var i = 0; i < CYCLES; i++) {
            activeCells = getNextState(activeCells);
        }

        return activeCells.size();
    }

    private Set<ActiveCell> getNextState(final Set<ActiveCell> activeCells) {
        final Set<ActiveCell> nextState = new HashSet<>();

        for (var activeCell : activeCells) {
            var neighBours = getNeighbours(activeCell, activeCells);
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

    private Map<ActiveCell, Integer> getNeighbours(ActiveCell activeCell, Set<ActiveCell> activeCells) {
        Map<ActiveCell, Integer> result = new HashMap<>();
        for (var i = -1; i <= 1; i++) {
            for (var j = -1; j <= 1; j++) {
                for (var k = -1; k <= 1; k++) {
                    var neighbour = new ActiveCell(i + activeCell.x, j + activeCell.y, k + activeCell.z);
                    var count = countActiveNeighbours(neighbour, activeCells);
                    result.put(neighbour, count);
                }
            }
        }
        return result;
    }

    private int countActiveNeighbours(ActiveCell activeCell, Set<ActiveCell> activeCells) {
        var count = 0;

        for (var i = -1; i <= 1; i++) {
            for (var j = -1; j <= 1; j++) {
                for (var k = -1; k <= 1; k++) {
                    if (i == 0 && j == 0 && k == 0) continue;
                    var neighbour = new ActiveCell(i + activeCell.x, j + activeCell.y, k + activeCell.z);
                    if (activeCells.contains(neighbour)) count++;
                }
            }
        }
        return count;
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
