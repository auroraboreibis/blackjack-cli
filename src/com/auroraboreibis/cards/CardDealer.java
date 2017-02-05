package com.auroraboreibis.cards;

import java.util.ArrayList;

public class CardDealer {
    private Deck deck;

    public CardDealer(Deck deck){
        this.deck = deck;
    }

    public ArrayList<Card> dealCards(int n){
        if (deck.getDeck().size() <= 0 || n > 52){
            throw new ArrayIndexOutOfBoundsException("No cards left in deck.");
        }

        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < n; i++){
            cards.add(i,deck.getDeck().get(deck.getCurrentCardIndex()));

            deck.getDeck().remove(deck.getCurrentCardIndex());
            deck.setCurrentCardIndex(deck.getDeck().size()-1);
        }

        return cards;
    }
}
