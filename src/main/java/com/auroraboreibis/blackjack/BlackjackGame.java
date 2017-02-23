package com.auroraboreibis.blackjack;

import com.auroraboreibis.cards.Card;
import com.auroraboreibis.cards.Deck;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame {
    // todo - add JUnit tests
    // todo - prevent the game from quitting if the user enters wrong type of input

    private Deck deck;
    private Dealer dealer;

    public BlackjackGame(Deck deck, Dealer dealer){
        this.deck = deck;
        this.dealer = dealer;
        this.deck.shuffle();
    }

    /**
     * Plays a game of blackjack.
     * @return Returns true if the user wins the round, and false if the dealer wins the round.
     */
    public boolean play(){
        Scanner s = new Scanner(System.in);
        char hitOrStay;

        // Players start off with 2 cards each
        BlackjackHand dealerHand = new BlackjackHand(dealer.dealCards(2));
        BlackjackHand playerHand = new BlackjackHand(dealer.dealCards(2));

        // Check if the players have got blackjack
        if (dealerHand.hasBlackjack()){ // Dealer has blackjack
            System.out.println("\nDealer's hand:");
            dealerHand.printHand();
            System.out.println("\nYour hand:");
            playerHand.printHand();
            System.out.println("Dealer has blackjack, dealer wins.");
            return false;
        }
        if (playerHand.hasBlackjack()){ // Player has blackjack
            System.out.println("\nDealer's hand:");
            dealerHand.printHand();
            System.out.println("\nYour hand:");
            playerHand.printHand();
            System.out.println("You have blackjack, you win.");
            return true;
        }

        // If nobody got blackjack with their first two cards
        System.out.println("\nYour hand:");
        playerHand.printHand();
        System.out.println("Your hand's value: " + playerHand.calcHandValue());

        while (true){
            System.out.print("\nDo you want to (H)it or (S)tay? ");
            do {
                hitOrStay = s.next().toLowerCase().charAt(0);
                if (hitOrStay != 'h' && hitOrStay != 's'){
                    System.out.print("Please enter 'h' or 's' ");
                }
            } while (hitOrStay != 'h' && hitOrStay != 's');

            if (hitOrStay == 's'){
                break;
            } else {
                // User hits.

                if (deck.cardsLeft() <= 1){
                    deck = null;
                    deck = new Deck();
                    deck.shuffle();

                    dealer = new Dealer(deck);
                }

                playerHand.addCardsToHand(dealer.dealCards(1));

                System.out.println("Your hand:");
                playerHand.printHand();
                System.out.println("You hand's value: " + playerHand.calcHandValue());

                if (playerHand.hasBusted()){ // Player busts
                    System.out.println("You busted!");
                    return false;
                }
            }
        }

        // User stands.
        System.out.println("You've chosen to stand.");
        System.out.println("\nDealer's hand:");
        dealerHand.printHand(2); // Show the dealer's first two cards

        boolean showDealersHandAgain = false;

        while (dealerHand.calcHandValue() <= 16){
            if (deck.cardsLeft() <= 1){
                deck = null;
                deck = new Deck();
                deck.shuffle();
                dealer = new Dealer(deck);
            }

            Card card = dealer.dealCards(1).get(0);
            ArrayList<Card> newCard = new ArrayList<>();
            newCard.add(0,card);

            dealerHand.addCardsToHand(newCard); // Add another card to the dealers hand
            System.out.println("Dealer chose to hit.");
            System.out.println("Dealer's new card is the " + card.toString() + ".");

            showDealersHandAgain = true;
        }

        if (dealerHand.hasBusted()){ // Dealer busts
            System.out.println("Dealer busted, you win.");
            return true;
        }

        if (showDealersHandAgain) {
            System.out.println("\nDealer's hand:");
            dealerHand.printHand();
        }

        System.out.println("\nYour hand: ");
        playerHand.printHand();

        // At this point both the dealer and player have < 21
        if (dealerHand.calcHandValue() == playerHand.calcHandValue()){ // tie
            System.out.println("\nIt's a tie, the dealer wins."); // todo - make this take no $ from player
            return false;
        }
        if (dealerHand.calcHandValue() > playerHand.calcHandValue()){ // Dealer wins
            System.out.println("\nDealer wins with " + dealerHand.calcHandValue() + ".");
            return false;
        }
        // By this point, the player has won
        System.out.println("\nYou win with " + playerHand.calcHandValue() + ".");
        return true;
    }
}