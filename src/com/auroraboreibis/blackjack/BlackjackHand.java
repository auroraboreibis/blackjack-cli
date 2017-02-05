package com.auroraboreibis.blackjack;

import com.auroraboreibis.cards.Card;
import com.auroraboreibis.cards.Hand;

import java.util.ArrayList;

public class BlackjackHand extends Hand{
    private ArrayList<Card> hand;
    private boolean hasBusted = false, hasBlackjack = false;

    public BlackjackHand(ArrayList<Card> cards){
        super(cards);
        hand = new ArrayList<>();
        this.hand = cards;
    }

    public int calcHandValue(){
        int handValue = 0, aceCount = 0;

        for (int i = 0; i < hand.size(); i++){
            handValue += hand.get(i).getRank().getValue();
            if (hand.get(i).getRank().equals(Card.Rank.ACE)){
                aceCount++;
            }
        }

        if (aceCount > 0){
            for (int i = 0; i < aceCount; i++){
                if(handValue <= 11){
                    handValue += 10; // 10 bc already added 1 before if an ace was encountered
                }
            }
        }

        if (handValue > 21){
            this.hasBusted = true;
        }
        if (handValue == 21){
            this.hasBlackjack = true;
        }

        return handValue;
    }

    public boolean hasBusted(){
        if (hasBusted){
            return true;
        }
        return false;
    }

    public boolean hasBlackjack(){
        if (hasBlackjack){
            return true;
        }
        return false;
    }

    //todo give the cards back to deck
    public void clearHand(){
        this.hand.clear();
    }
}
