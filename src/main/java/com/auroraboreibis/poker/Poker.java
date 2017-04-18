package com.auroraboreibis.poker;

import com.auroraboreibis.blackjack.Dealer;
import com.auroraboreibis.cards.Deck;
import com.auroraboreibis.cards.Hand;

public class Poker {
    public static void main(String[] args){
        Deck deck = new Deck();
        deck.shuffle();
        Dealer dealer = new Dealer(deck);
        Hand hand = new Hand(dealer.dealCards(5));

        PokerHandEvaluator he = new PokerHandEvaluator(hand);
        hand.printHand();

        System.out.println("Hand: " + he.getPattern());
    }
}