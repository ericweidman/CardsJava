package com.theironyard;


import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {

    static HashSet<Card> createDeck() {
        HashSet<Card> deck = new HashSet<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card c = new Card(suit, rank);
                deck.add(c);
            }
        }
        return deck;
    }

    static HashSet<HashSet<Card>> createHands(HashSet<Card> deck) {
        HashSet<HashSet<Card>> hands = new HashSet<>();
        for (Card c1 : deck) {
            HashSet<Card> deck2 = (HashSet<Card>) deck.clone();
            deck2.remove(c1);
            for (Card c2 : deck2) {
                HashSet<Card> deck3 = (HashSet<Card>) deck2.clone();
                deck3.remove(c2);
                for (Card c3 : deck3) {
                    HashSet<Card> deck4 = (HashSet<Card>) deck3.clone();
                    deck4.remove(c3);
                    for (Card c4 : deck4) {
                        HashSet<Card> hand = new HashSet<>();
                        hand.add(c1);
                        hand.add(c2);
                        hand.add(c3);
                        hand.add(c4);
                        hands.add(hand);
                    }
                }
            }
        }
        return hands;
    }

    static boolean isFlush(HashSet<Card> hand) {
        HashSet<Card.Suit> suits =
                hand.stream()
                        .map(card -> {
                            return card.suit;
                        })
                        .collect(Collectors.toCollection(HashSet<Card.Suit>::new));
        return suits.size() == 1;
    }

//    static boolean isStraightFlush(HashSet<Card> hand){
//
//
//    }
//
//    static boolean isStraight(HashSet<Card> hand){
//
//
//    }

    static boolean isFourKind(HashSet<Card> hand) {
        HashSet<Card.Rank> ranks =
                hand.stream()
                        .map(card -> {
                            return card.rank;

                        })
                        .collect(Collectors.toCollection(HashSet<Card.Rank>::new));
        return ranks.size() == 1;
    }

    static boolean isThreeKind(HashSet<Card> hand) {
        HashSet<Card.Rank> ranks =
                hand.stream()
                        .map(card -> {
                            return card.rank;
                        })
                        .collect(Collectors.toCollection(HashSet<Card.Rank>::new));
        return ranks.size() == 2;

    }
//
//    static boolean twoPair(HashSet<Card> hand){
//
//
//    }


    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();

        HashSet<Card> deck = createDeck();
        HashSet<HashSet<Card>> hands = createHands(deck);
        hands = hands.stream()
                .filter(Main::isFourKind)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        System.out.println(hands.size());

        long endTime = System.currentTimeMillis();
        System.out.printf("Elapsed time: %d msecs\n", endTime - beginTime);

    }
}
