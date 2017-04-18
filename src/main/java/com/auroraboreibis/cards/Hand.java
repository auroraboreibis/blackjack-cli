package com.auroraboreibis.cards;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand(ArrayList<Card> cards){
        hand = new ArrayList<>();
        this.hand = cards;
    }

    /**
     *
     * @param n Print Cards in deck from index 0 to n
     */
    public void printHand(int n){
        if (n > hand.size()){
            return;
        }
        System.out.print("\t");
        for (int i = 0; i < n; i++){
            System.out.print(hand.get(i).toString() + "   ");
        }
        System.out.println();
    }

    public void printHand(){
        System.out.print("\t");
        for (int i = 0; i < hand.size(); i++){
            System.out.print(hand.get(i).toString() + "   ");
        }
        System.out.println();
    }

    /**
     *
     * @param cards Add an ArrayList of Cards to the hand
     */
    public void addCardsToHand(ArrayList<Card> cards){
        for (int i = 0; i < cards.size(); i++){
            hand.add(cards.get(i));
        }
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }
}
