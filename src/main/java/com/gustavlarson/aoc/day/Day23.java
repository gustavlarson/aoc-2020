package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.*;
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

        return getLabels(state);
    }

    private static int getLabels(List<Integer> state) {
        StringBuilder result = new StringBuilder();
        var index = state.indexOf(1);
        while (state.size() - 1 > index) {
            result.append(state.remove(index + 1));
        }
        for (var i = 0; i < index; i++) {
            result.append(state.remove(0));
        }
        return Integer.parseInt(result.toString());
    }

    private static List<Integer> move(List<Integer> state) {
        List<Integer> pickup = new ArrayList<>();
        pickup.add(state.remove(1));
        pickup.add(state.remove(1));
        pickup.add(state.remove(1));

        int destination = state.get(0) - 1;
        //System.out.println("Destination: " + destination);
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

    //    private static final int SIZE = 10;
    private static final int SIZE = 1_000_000;
    //    private static final int ITERATIONS = 100;
    private static final int ITERATIONS = 10_000_000;

    static class Cup {
        final int label;
        Cup next;

        Cup(int label) {
            this.label = label;
        }
    }


    @Override
    public long solvePart2() {
        List<Integer> labels = new LinkedList<>(input);
        while (labels.size() < SIZE) {
            labels.add(labels.size() + 1);
        }

        Map<Integer, Cup> cups = new HashMap<>(SIZE + 1);

        Cup firstCup = null;
        Cup lastCup = null;
        for (var label : labels) {
            final Cup newCup = new Cup(label);
            cups.put(label, newCup);
            if (firstCup == null) firstCup = newCup;
            if (lastCup != null) lastCup.next = newCup;
            lastCup = newCup;
        }
        lastCup.next = firstCup;
        Cup current = firstCup;

        for (int i = 0; i < ITERATIONS; i++) {
            current = moveCup(cups, current);
        }

        Cup one = cups.get(1);
        return (long) one.next.label * (long) one.next.next.label;
    }

    private static Cup moveCup(Map<Integer, Cup> cups, Cup current) {
        Cup pickup = current.next;

        List<Integer> pickupValues = new ArrayList<>();
        pickupValues.add(pickup.label);
        pickupValues.add(pickup.next.label);
        pickupValues.add(pickup.next.next.label);

        var destinationLabel = current.label - 1;
        if (destinationLabel == 0) destinationLabel = SIZE;
        while (pickupValues.contains(destinationLabel)) {
            destinationLabel--;
            if (destinationLabel == 0) destinationLabel = SIZE;
        }

        current.next = current.next.next.next.next;
        Cup destinationCup = cups.get(destinationLabel);
        pickup.next.next.next = destinationCup.next;
        destinationCup.next = pickup;
        return current.next;
    }

}
