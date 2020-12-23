package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day23 implements Day {

    private final List<Integer> input;

    public Day23(final List<String> input) {
        this.input = input.get(0).chars().boxed().map(c -> c - '0').collect(Collectors.toList());
    }

    @Override
    public long solvePart1() {
        List<Integer> state = input;
        System.out.println("Initial: " + state);

        for (int i = 1; i <= 100; i++) {
            state = move(state);
            System.out.println("Move " + i + " " + state);
        }

        var result = "";
        var index = state.indexOf(1);
        while (state.size() - 1 > index) {
            result += state.remove(index + 1);
        }
        for (var i = 0; i < index; i++) {
            result += state.remove(0);
        }
        return Integer.parseInt(result);
    }

    private static List<Integer> move(List<Integer> state) {
        List<Integer> pickup = new ArrayList<>();
        pickup.add(state.remove(1));
        pickup.add(state.remove(1));
        pickup.add(state.remove(1));

        int destination = state.get(0) - 1;
        System.out.println("Destination: " + destination);
        if (destination == 0) destination = 9;
        while (pickup.contains(destination)) {
            destination--;
            if (destination == 0) destination = 9;
        }

        List<Integer> newState = new ArrayList<>();
        // Add all to the left of destination and the destination
        while (state.get(0) != destination) {
            newState.add(state.remove(0));
        }
        newState.add(state.remove(0));
        // Insert the cups that were picked up
        newState.addAll(pickup);
        // Insert the rest of the cups at the back
        newState.addAll(state);

        // Shift by one
        newState.add(newState.remove(0));

        return newState;
    }

    @Override
    public long solvePart2() {
        return 0;
    }
}
