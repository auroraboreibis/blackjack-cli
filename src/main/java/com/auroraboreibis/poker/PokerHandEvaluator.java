package com.auroraboreibis.poker;

import com.auroraboreibis.cards.Card;
import com.auroraboreibis.cards.Hand;
import java.util.ArrayList;
import java.util.Collections;

public class PokerHandEvaluator {
    private ArrayList<Card> cards;
    private ArrayList<Integer> sortedRanks;
    private String pattern = "Nothing.";
    private int faceTally[] = new int[5];
    private int suitsTally[] = new int[4];

    // Will be cleaning this evaluator up sometime, I'm not too happy
    // with the state its currently, I need this the be available on git rn.

    public PokerHandEvaluator(Hand hand){
        this.cards = new ArrayList<>();
        this.cards = hand.getHand();
        this.evaluateHand();
    }

    /**
     * Evaluates the given hand and sets the String pattern to the
     * String representation of the poker hand. E.g. "Full House"
     */
    public void evaluateHand(){
        // Tally the face cards
        // Example hand: QD, 10S, 5H, QH, JC
        // Example result: 2,1,1,2,1
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (this.cards.get(i).getRank().getValue() ==
                        this.cards.get(j).getRank().getValue()){
                    faceTally[i]++;
                }
            }
            // Tally the suits
            // Example result (from the same example hand commented above)
            // 1 1 2 1
            suitsTally[this.cards.get(i).getSuit().getValue()]++;
        }

        faceTally = sortIntArrayDesc(faceTally, false);

        // sort the hand into a new arraylist
        // todo - change sortedRanks to primitive int array, update sort function
        // todo - to account for both orders of sorting
        sortedRanks = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            sortedRanks.add(this.cards.get(i).getValue(true));
        }
        Collections.sort(sortedRanks);

        // check for straight & straight flush
        if (isStraight()){
            this.pattern = "Straight";
            // check for straight flush
            if (isFlush()){
                if (cards.get(4).getRank().equals(Card.Rank.ACE)){
                    this.pattern = "Royal Flush";
                    return;
                }
                this.pattern += " Flush";
            }
            return;
        }

        // check for 4 of a kind
        if (faceTally[0] == 4){
            pattern = "Four of a Kind";
            return;
        }

        // check for full house
        if (faceTally[2] == 3 && faceTally[3] == 2){
            pattern = "Full House";
            return;
        }

        // check for flush
        if (isFlush()){
            this.pattern = "Flush";
            return;
        }

        // check for 3 of a kind
        if (faceTally[2] == 3 && faceTally[3] == 1){
            this.pattern = "Three of a Kind";
            return;
        }

        // check for 2 pair
        if (faceTally[0] == 2 && faceTally[3] == 2){
            this.pattern = "Two Pair";
            return;
        }

        // check for pair
        // todo - saw this give a false value once, should probably test this.
        if (faceTally[0] == 2 && faceTally[2] == 1 && faceTally[4] == 1){
            this.pattern = "Pair";
        }
    }

    // todo - should this definitely be a function?
    private boolean isStraight(){
        if (sortedRanks.get(0) + 4 == sortedRanks.get(4)){
            // accounts for a hand like [10,10,10,10,14]
            // which makes the hand a RF, but it shouldnt be one
            for (int i = 0; i < faceTally.length; i++){
                if (faceTally[i] > 1){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // todo - should this definitely be a function?
    private boolean isFlush(){
        for (int i = 0; i < suitsTally.length; i++){
            if (suitsTally[i] == 5){
                return true;
            }
        }
        return false;
    }

    public String getPattern(){
        return pattern;
    }

    // todo - find an inbuilt way of reverse sorting primitive ints
    private int[] sortIntArrayDesc(int[] a, boolean sortAsc){
        int[] array = a;
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (sortAsc){
                    if (array[j - 1] > array[j]) {
                        temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;
                    }
                } else {
                    if (array[j - 1] < array[j]) {
                        temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
        return array;
    }
}