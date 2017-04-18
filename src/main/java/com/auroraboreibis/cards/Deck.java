package com.auroraboreibis.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;
    private int currentCardIndex;

    public Deck(){
        cards = new ArrayList<>();

        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();

        for (Card.Suit s : suits){
            for (Card.Rank r : ranks){
                this.cards.add(new Card(r,s,false));
            }
        }
        currentCardIndex = cards.size()-1;
    }

    /**
     *
     * @return Returns the deck ArrayList.
     */
    public ArrayList<Card> getDeck(){
        return cards;
    }

    public void shuffle(){
        long seed = System.nanoTime();
        Collections.shuffle(cards,new Random(seed));
    }

    /**
     *
     * @return Returns the amount of Cards left in the deck.
     */
    public int cardsLeft(){
        return cards.size();
    }

    /**
     *
     * @return Returns the current Card's index.
     */
    public int getCurrentCardIndex(){
        return currentCardIndex;
    }

    /**
     *
     * @param value Set the current Card's index to value.
     */
    public void setCurrentCardIndex(int value){
        this.currentCardIndex = value;
    }

    public void printDeck(){
        for (int i = 0; i < cards.size(); i++){
            System.out.println(cards.get(i).toString());
        }
    }
}