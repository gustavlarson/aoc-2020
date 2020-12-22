package com.gustavlarson.aoc.day;

import com.gustavlarson.aoc.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day22 implements Day {

    final LinkedList<Integer> deck1;
    final LinkedList<Integer> deck2;


    public Day22(final List<String> input) {
        deck1 = input.stream().skip(1).takeWhile(line -> line.length() > 0).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
        deck2 = input.stream().dropWhile(line -> line.length() > 0).skip(2).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public long solvePart1() {
        final LinkedList<Integer> deck1 = new LinkedList<>(this.deck1);
        final LinkedList<Integer> deck2 = new LinkedList<>(this.deck2);

        while (!deck1.isEmpty() && !deck2.isEmpty()) {
            var draw1 = deck1.removeFirst();
            var draw2 = deck2.removeFirst();

            if (draw1 > draw2) {
                deck1.addLast(draw1);
                deck1.addLast(draw2);
            } else {
                deck2.addLast(draw2);
                deck2.addLast(draw1);
            }
        }

        var winner = (!deck1.isEmpty()) ? deck1 : deck2;
        return calculateScore(winner);

    }

    private static long calculateScore(LinkedList<Integer> deck) {
        var i = 1;
        var count = 0;
        while (deck.size() > 0) {
            count += deck.removeLast() * i;
            i++;
        }
        return count;
    }

    @Override
    public long solvePart2() {
        final LinkedList<Integer> deck1 = new LinkedList<>(this.deck1);
        final LinkedList<Integer> deck2 = new LinkedList<>(this.deck2);

        recursiveCombat(deck1, deck2);
        var winner = (!deck1.isEmpty()) ? deck1 : deck2;
        return calculateScore(winner);
    }

    private static boolean seenBefore(Set<List<LinkedList<Integer>>> seen, LinkedList<Integer> p1, LinkedList<Integer> p2) {
        List<LinkedList<Integer>> decks = new ArrayList<>();
        decks.add(p1);
        decks.add(p2);
        boolean result = seen.contains(decks);
        seen.add(decks);

        return result;
    }

    private static LinkedList<Integer> splitDeck(LinkedList<Integer> deck, int count) {
        LinkedList<Integer> oldDeck = new LinkedList<>(deck);
        LinkedList<Integer> newDeck = new LinkedList<>();

        for (var i = 0; i < count; i++) {
            newDeck.addLast(oldDeck.removeFirst());
        }

        return newDeck;
    }

    private static boolean recursiveCombat(LinkedList<Integer> deck1, LinkedList<Integer> deck2) {
        Set<List<LinkedList<Integer>>> uniqueHands = new HashSet<>();

        while (!deck1.isEmpty() && !deck2.isEmpty()) {
            if (seenBefore(uniqueHands, deck1, deck2)) return true;

            boolean deck1Wins;
            var draw1 = deck1.removeFirst();
            var draw2 = deck2.removeFirst();

            if (deck1.size() < draw1 || deck2.size() < draw2) {
                deck1Wins = draw1 > draw2;
            } else {
                LinkedList<Integer> newDeck1 = splitDeck(deck1, draw1);
                LinkedList<Integer> newDeck2 = splitDeck(deck2, draw2);

                deck1Wins = recursiveCombat(newDeck1, newDeck2);
            }

            if (deck1Wins) {
                deck1.addLast(draw1);
                deck1.addLast(draw2);
            } else {
                deck2.addLast(draw2);
                deck2.addLast(draw1);
            }
        }

        return !deck1.isEmpty();
    }
}
