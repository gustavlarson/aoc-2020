package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.Arrays;
import java.util.List;

public class Day11 implements Day {

    enum State {
        EMPTY('L'), OCCUPIED('#'), FLOOR('.');

        private final char icon;

        State(final char c) {
            this.icon = c;
        }

        @Override
        public String toString() {
            return "" + icon;
        }
    }

    private final State[][] inputState;

    public Day11(final List<String> input) {
        this.inputState = input.stream().map(row -> row.chars().mapToObj(c -> switch (c) {
            case 'L' -> State.EMPTY;
            case '#' -> State.OCCUPIED;
            case '.' -> State.FLOOR;
            default -> throw new IllegalArgumentException();
        }).toArray(State[]::new)).toArray(State[][]::new);
    }

    @Override
    public long solvePart1() {
        var previousState = inputState;
        int i = 0;
        System.out.println("Iteration " + ++i);
        print(inputState);
        while (true) {
            final State[][] nextState = computeNextState(previousState);
            System.out.println("Iteration " + ++i);
            print(nextState);
            if (compareState(previousState, nextState)) {
                break;
            }
            //if (i == 3) break;
            previousState = nextState;
        }

        return countOccupiedSeats(previousState);
    }

    private static long countOccupiedSeats(final State[][] previousState) {
        return Arrays.stream(previousState).mapToLong(row -> Arrays.stream(row).filter(state -> state == State.OCCUPIED).count()).sum();
    }

    private static boolean compareState(final State[][] previousState, final State[][] nextState) {
        for (var i = 0; i < previousState.length; i++) {
            for (var j = 0; j < previousState[i].length; j++) {
                if (previousState[i][j] != nextState[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static State[][] computeNextState(final State[][] previousState) {
        final State[][] nextState = new State[previousState.length][previousState[0].length];
        for (var i = 0; i < previousState.length; i++) {
            for (var j = 0; j < previousState[i].length; j++) {
                nextState[i][j] = switch (previousState[i][j]) {
                    case FLOOR -> State.FLOOR;
                    case EMPTY -> allSurroundingSeatsEmpty(previousState, i, j) ? State.OCCUPIED : State.EMPTY;
                    case OCCUPIED -> tooManySurroundingSeatsOccupied(previousState, i, j) ? State.EMPTY : State.OCCUPIED;
                };
            }
        }

        return nextState;
    }

    private static boolean tooManySurroundingSeatsOccupied(final State[][] previousState, final int i, final int j) {
        var count = 0;
        if (i - 1 >= 0) {
            if (j - 1 >= 0) {
                if (previousState[i - 1][j - 1] == State.OCCUPIED) count++;
            }
            if (previousState[i - 1][j] == State.OCCUPIED) count++;
            if (j + 1 < previousState[i - 1].length) {
                if (previousState[i - 1][j + 1] == State.OCCUPIED) count++;
            }
        }
        if (j - 1 >= 0) {
            if (previousState[i][j - 1] == State.OCCUPIED) count++;
        }
        if (j + 1 < previousState[i].length) {
            if (previousState[i][j + 1] == State.OCCUPIED) count++;
        }
        if (i + 1 < previousState.length) {
            if (j - 1 >= 0) {
                if (previousState[i + 1][j - 1] == State.OCCUPIED) count++;
            }
            if (previousState[i + 1][j] == State.OCCUPIED) count++;
            if (j + 1 < previousState[i + 1].length) {
                if (previousState[i + 1][j + 1] == State.OCCUPIED) count++;
            }
        }

        return count >= 4;
    }

    private static boolean allSurroundingSeatsEmpty(final State[][] previousState, final int i, final int j) {
        if (i - 1 >= 0) {
            if (j - 1 >= 0) {
                if (previousState[i - 1][j - 1] == State.OCCUPIED) return false;
            }
            if (previousState[i - 1][j] == State.OCCUPIED) return false;
            if (j + 1 < previousState[i - 1].length) {
                if (previousState[i - 1][j + 1] == State.OCCUPIED) return false;
            }
        }
        if (j - 1 >= 0) {
            if (previousState[i][j - 1] == State.OCCUPIED) return false;
        }
        if (j + 1 < previousState[i].length) {
            if (previousState[i][j + 1] == State.OCCUPIED) return false;
        }
        if (i + 1 < previousState.length) {
            if (j - 1 >= 0) {
                if (previousState[i + 1][j - 1] == State.OCCUPIED) return false;
            }
            if (previousState[i + 1][j] == State.OCCUPIED) return false;
            if (j + 1 < previousState[i + 1].length) {
                if (previousState[i + 1][j + 1] == State.OCCUPIED) return false;
            }
        }

        return true;
    }

    private static void print(final State[][] state) {
        Arrays.stream(state).forEach(line -> {
            Arrays.stream(line).forEach(System.out::print);
            System.out.println();
        });
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
