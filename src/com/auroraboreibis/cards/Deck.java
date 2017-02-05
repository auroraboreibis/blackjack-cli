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
                this.cards.add(new Card(r,s));
            }
        }
        currentCardIndex = cards.size()-1;
    }

    public ArrayList<Card> getDeck(){
        return cards;
    }

    public void shuffle(){
        long seed = System.nanoTime();
        Collections.shuffle(cards,new Random(seed));
    }

    public int cardsLeft(){
        return cards.size();
    }

    public int getCurrentCardIndex(){
        return currentCardIndex;
    }

    public void setCurrentCardIndex(int value){
        this.currentCardIndex = value;
    }

    public void printDeck(){
        for (int i = 0; i < cards.size(); i++){
            System.out.println(cards.get(i).toString());
        }
    }
}