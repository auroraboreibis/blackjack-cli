package com.auroraboreibis.blackjack;

import com.auroraboreibis.cards.Deck;
import java.util.Scanner;

public class Game {
    // todo - make sure everything works..and maybe add in more comments
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int input, money = 100, bet;
        boolean userWins;

        System.out.println("=Welcome to Blackjack=");
        System.out.println("1. Start a game");
        System.out.println("2. Info");
        System.out.println("3. Quit");
        System.out.print("> ");

        do {
            input = s.nextInt();
            if (input < 1 || input > 3){
                System.out.print("Please choose 1,2 or 3 ");
            }
        } while (input < 1 || input > 3);


        switch (input){
            case 1:
                while(true){
                    System.out.println("You have $" + money + ".");
                    do {
                        System.out.print("How much would you like to bet for this round? (or 0 to quit) $");
                        bet = s.nextInt();
                        if (bet < 0 || bet > money){
                            System.out.println("Your bet must be between $0 and $" + money + ".");
                        }
                    } while (bet < 0 || bet > money);
                    if (bet == 0){
                        break;
                    }
                    userWins = playBlackjackGame();
                    if (userWins){
                        money += bet;
                    } else {
                        money -= bet;
                    }
                    System.out.println();
                    if (money <= 0){
                        System.out.println("You have run out of money.");
                        break;
                    }
                }
                break;
            case 2:
                showInfo();
                break;
            case 3:
                return;
        }
        //todo - show how much money the player walks out with
    }

    public static void showInfo(){
        System.out.println("Blackjack game by @auroraboreibis");
        System.out.println("You will be started out with $100.");
        System.out.println("Ace values are automatically set as either 1 or 11 for you.");
        System.out.println("This is a side project for learning Java. More card");
        System.out.println("games will probably be added in the future.\n");
        System.out.println("How was you experience? Any bugs?");
        System.out.println("Let me know -> auroraboreibis@gmail.com");
    }

    public static boolean playBlackjackGame(){
        Scanner s = new Scanner(System.in);
        char hitOrStay;

        Deck deck = new Deck();
        deck.shuffle();

        Dealer dealer = new Dealer(deck);

        // Players start off with 2 cards each
        BlackjackHand dealerHand = new BlackjackHand(dealer.dealCards(2));
        BlackjackHand playerHand = new BlackjackHand(dealer.dealCards(2));

        // Check if the players have got blackjack
        if (dealerHand.calcHandValue() == 21){ // Dealer has blackjack
            System.out.println("\nDealer's hand:"); // todo - could make these prints into a method to save space
            dealerHand.printHand();
            System.out.println("\nYour hand:");
            playerHand.printHand();
            System.out.println("Dealer has blackjack, dealer wins.");
            return false;
        }
        if (playerHand.calcHandValue() == 21){ // Player has blackjack
            System.out.println("\nDealer's hand:"); // todo - could make these prints into a method to save space
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
                playerHand.addCardsToHand(dealer.dealCards(1));

                System.out.println("Your hand:");
                playerHand.printHand();
                System.out.println("You hand's value: " + playerHand.calcHandValue());

                if (playerHand.calcHandValue() > 21){ // Player busts
                    System.out.println("You busted!");
                    return false;
                }
            }
        }
        // User stands.

        System.out.println("You've chosen to stand.");
        System.out.println("\nDealer's hand:");
        dealerHand.printHand(2); // Show the dealer's first two cards

        while (dealerHand.calcHandValue() <= 16){
            dealerHand.addCardsToHand(dealer.dealCards(1)); // Add another card to the dealers hand
            System.out.println("Dealer chose to hit.");
            // todo - show what card the dealer picked up (only the new picked up card)

            if (dealerHand.calcHandValue() > 21){ // Dealer busts
                System.out.println("Dealer busted, you win.");
                return true;
            }
        }

        System.out.println("Dealer's hand:");
        dealerHand.printHand();
        System.out.println("\nYour hand: ");
        playerHand.printHand();

        // At this point both the dealer and player have < 21
        if (dealerHand.calcHandValue() == playerHand.calcHandValue()){ // tie
            System.out.println("It's a tie, the dealer wins."); // todo - make this take no $ from player
            return false;
        }
        if (dealerHand.calcHandValue() > playerHand.calcHandValue()){ // Dealer wins
            System.out.println("Dealer wins with " + dealerHand.calcHandValue() + ".");
            return false;
        }
        // By this point, the player has won
        System.out.println("You win with " + playerHand.calcHandValue() + ".");
        return true;
    }
}