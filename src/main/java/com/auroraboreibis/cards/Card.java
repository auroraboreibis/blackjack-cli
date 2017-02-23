package com.auroraboreibis.cards;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank(){
        return rank;
    }

    public Suit getSuit(){
        return suit;
    }

    public enum Rank{
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private int value;

        private Rank(final int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public enum Suit{
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    /**
     *
     * @return String representation of the Card.
     */
    @Override
    public String toString(){
        return this.rank + " of " + this.suit;
    }
}
