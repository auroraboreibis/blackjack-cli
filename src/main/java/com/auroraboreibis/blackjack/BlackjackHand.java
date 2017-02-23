package com.auroraboreibis.blackjack;

import com.auroraboreibis.cards.Card;
import com.auroraboreibis.cards.Hand;
import java.util.ArrayList;

public class BlackjackHand extends Hand{
    private ArrayList<Card> hand;

    public BlackjackHand(ArrayList<Card> cards){
        super(cards);
        hand = new ArrayList<>();
        this.hand = cards;
    }

    /**
     *
     * @return Returns the hand's value as an int.
     */
    public int calcHandValue(){
        int handValue = 0;

        for (int i = 0; i < hand.size(); i++){
            handValue += hand.get(i).getRank().getValue();
            if (hand.get(i).getRank().equals(Card.Rank.ACE)){
                if (handValue <= 11){
                    handValue += 10;
                }
            }
        }

        return handValue;
    }

    /**
     *
     * @return Returns true if the hand is a bust, otherwise false.
     */
    public boolean hasBusted(){
        if (calcHandValue() > 21){
            return true;
        }
        return false;
    }

    /**
     *
     * @return Returns true if the hand's value is 21, otherwise false.
     */
    public boolean hasBlackjack(){
        if (calcHandValue() == 21){
            return true;
        }
        return false;
    }
}
