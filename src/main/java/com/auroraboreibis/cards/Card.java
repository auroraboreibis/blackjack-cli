package com.auroraboreibis.cards;

public class Card {
    private Rank rank;
    private Suit suit;
    private boolean highAce;

    public Card(Rank rank, Suit suit, boolean highAce){
        this.rank = rank;
        this.suit = suit;
        this.highAce = highAce;
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
        JACK(11),
        QUEEN(12),
        KING(13);

        private int value;

        Rank(final int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public enum Suit{
        SPADES(0),
        CLUBS(1),
        HEARTS(2),
        DIAMONDS(3);

        private int value;
        Suit(final int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }

    // if highAce is false, the value of an ace is 1, otherwise ace value is set to 10
    public int getValue(boolean ignoreFaceValueAssignment){
        if (this.rank == Rank.JACK || this.rank == Rank.QUEEN ||this.rank == Rank.KING && !ignoreFaceValueAssignment){
            return 10;
        }
        if (this.highAce && this.rank == Rank.ACE){
            return rank.getValue() + 13; // makes the ace value highest ranking card
        }

        return rank.getValue();
    }

    public boolean isFaceCard(){
        return this.rank == Rank.ACE || this.rank == Rank.JACK ||
                this.rank == Rank.QUEEN || this.rank == Rank.KING;
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