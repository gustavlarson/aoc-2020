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
        final int i = 0;
        //System.out.println("Iteration " + ++i);
        //print(inputState);
        while (true) {
            final State[][] nextState = computeNextState(previousState, 4, 1);
            //System.out.println("Iteration " + ++i);
            //print(nextState);
            if (compareState(previousState, nextState)) {
                break;
            }
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

    private static State[][] computeNextState(final State[][] previousState, final int maxSeats, final int maxLos) {
        final State[][] nextState = new State[previousState.length][previousState[0].length];
        for (var x = 0; x < previousState.length; x++) {
            for (var y = 0; y < previousState[x].length; y++) {
                nextState[x][y] = switch (previousState[x][y]) {
                    case FLOOR -> State.FLOOR;
                    case EMPTY -> allSurroundingSeatsEmpty(previousState, x, y, maxLos) ? State.OCCUPIED : State.EMPTY;
                    case OCCUPIED -> tooManySurroundingSeatsOccupied(previousState, x, y, maxSeats, maxLos) ? State.EMPTY : State.OCCUPIED;
                };
            }
        }

        return nextState;
    }

    private static boolean tooManySurroundingSeatsOccupied(final State[][] previousState, final int x, final int y, final int maxSeats, final int maxLos) {
        final State[] seatFound = {null, null, null, null, null, null, null, null};
        for (var i = 1; i <= maxLos; i++) {
            if (x - i >= 0) {
                if (seatFound[0] == null && y - i >= 0) {
                    if (previousState[x - i][y - i] != State.FLOOR)
                        seatFound[0] = previousState[x - i][y - i];
                }
                if (seatFound[1] == null && previousState[x - i][y] != State.FLOOR)
                    seatFound[1] = previousState[x - i][y];
                if (seatFound[2] == null && y + i < previousState[x - i].length) {
                    if (previousState[x - i][y + i] != State.FLOOR) seatFound[2] = previousState[x - i][y + i];
                }
            }
            if (seatFound[3] == null && y - i >= 0) {
                if (previousState[x][y - i] != State.FLOOR) seatFound[3] = previousState[x][y - i];
            }
            if (seatFound[4] == null && y + i < previousState[x].length) {
                if (previousState[x][y + i] != State.FLOOR) seatFound[4] = previousState[x][y + i];
            }
            if (x + i < previousState.length) {
                if (seatFound[5] == null && y - i >= 0) {
                    if (previousState[x + i][y - i] != State.FLOOR) seatFound[5] = previousState[x + i][y - i];
                }
                if (seatFound[6] == null && previousState[x + i][y] != State.FLOOR)
                    seatFound[6] = previousState[x + i][y];
                if (seatFound[7] == null && y + i < previousState[x + i].length) {
                    if (previousState[x + i][y + i] != State.FLOOR) seatFound[7] = previousState[x + i][y + i];
                }
            }
        }

        return Arrays.stream(seatFound).filter(b -> b == State.OCCUPIED).count() >= maxSeats;
    }

    private static boolean allSurroundingSeatsEmpty(final State[][] previousState, final int x, final int y, final int maxLos) {
//        final State[] seatFound = {null, null, null, null, null, null, null, null};
//        for (var i = 1; i <= maxLos; i++) {
//            if (x - i >= 0) {
//                if (seatFound[0] == null && y - i >= 0) {
//                    if (previousState[x - i][y - i] == State.OCCUPIED) return false;
//                }
//                if (seatFound[1] == null && previousState[x - i][y] == State.OCCUPIED) return false;
//                if (seatFound[2] == null && y + i < previousState[x - i].length) {
//                    if (previousState[x - i][y + i] == State.OCCUPIED) return false;
//                }
//            }
//            if (seatFound[3] == null && y - i >= 0) {
//                if (previousState[x][y - i] == State.OCCUPIED) return false;
//            }
//            if (seatFound[4] == null && y + i < previousState[x].length) {
//                if (previousState[x][y + i] == State.OCCUPIED) return false;
//            }
//            if (x + i < previousState.length) {
//                if (seatFound[5] == null && y - i >= 0) {
//                    if (previousState[x + i][y - i] == State.OCCUPIED) return false;
//                }
//                if (seatFound[6] == null && previousState[x + i][y] == State.OCCUPIED) return false;
//                if (seatFound[7] == null && y + i < previousState[x + i].length) {
//                    if (previousState[x + i][y + i] == State.OCCUPIED) return false;
//                }
//            }
//        }
//
//        return true;
        final State[] seatFound = {null, null, null, null, null, null, null, null};
        for (var i = 1; i <= maxLos; i++) {
            if (x - i >= 0) {
                if (seatFound[0] == null && y - i >= 0) {
                    if (previousState[x - i][y - i] != State.FLOOR)
                        seatFound[0] = previousState[x - i][y - i];
                }
                if (seatFound[1] == null && previousState[x - i][y] != State.FLOOR)
                    seatFound[1] = previousState[x - i][y];
                if (seatFound[2] == null && y + i < previousState[x - i].length) {
                    if (previousState[x - i][y + i] != State.FLOOR) seatFound[2] = previousState[x - i][y + i];
                }
            }
            if (seatFound[3] == null && y - i >= 0) {
                if (previousState[x][y - i] != State.FLOOR) seatFound[3] = previousState[x][y - i];
            }
            if (seatFound[4] == null && y + i < previousState[x].length) {
                if (previousState[x][y + i] != State.FLOOR) seatFound[4] = previousState[x][y + i];
            }
            if (x + i < previousState.length) {
                if (seatFound[5] == null && y - i >= 0) {
                    if (previousState[x + i][y - i] != State.FLOOR) seatFound[5] = previousState[x + i][y - i];
                }
                if (seatFound[6] == null && previousState[x + i][y] != State.FLOOR)
                    seatFound[6] = previousState[x + i][y];
                if (seatFound[7] == null && y + i < previousState[x + i].length) {
                    if (previousState[x + i][y + i] != State.FLOOR) seatFound[7] = previousState[x + i][y + i];
                }
            }
        }

        return Arrays.stream(seatFound).allMatch(b -> b == State.EMPTY || b == null);
    }

    private static void print(final State[][] state) {
        Arrays.stream(state).forEach(line -> {
            Arrays.stream(line).forEach(System.out::print);
            System.out.println();
        });
    }

    @Override
    public long solvePart2() {
        var previousState = inputState;
        final int i = 0;
        //System.out.println("Iteration " + ++i);
        print(inputState);
        final var maxLos = Math.max(inputState.length, inputState[0].length);
        while (true) {
            final State[][] nextState = computeNextState(previousState, 5, maxLos);
            //System.out.println("Iteration " + ++i);
            print(nextState);
            if (compareState(previousState, nextState)) {
                break;
            }
            // if (i == 4) break;
            previousState = nextState;
        }

        return countOccupiedSeats(previousState);
    }
}
