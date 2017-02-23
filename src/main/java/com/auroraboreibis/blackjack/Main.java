package com.auroraboreibis.blackjack;

import com.auroraboreibis.cards.Deck;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int input, money = 100, bet;
        boolean userWins;

        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck);

        BlackjackGame game = new BlackjackGame(deck, dealer);

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
                    System.out.print("You have $" + money + ".");
                    do {
                        System.out.print("\nHow much would you like to bet for this round? (or 0 to quit) $");
                        bet = s.nextInt();
                        if (bet < 0 || bet > money){
                            System.out.println("Your bet must be between $0 and $" + money + ".");
                        }
                    } while (bet < 0 || bet > money);
                    if (bet == 0){
                        break;
                    }
                    userWins = game.play();
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
                System.out.println("Blackjack game by @auroraboreibis");
                System.out.println("You will be started out with $100.");
                System.out.println("Ace values are automatically set as either 1 or 11 for you.");
                System.out.println("This is a side project for learning Java. More card");
                System.out.println("games will probably be added in the future.\n");
                System.out.println("How was you experience? Any bugs?");
                System.out.println("Let me know -> auroraboreibis@gmail.com");
                break;
            case 3:
                return;
        }

        System.out.println("You walk out with $" + money + ".");
    }
}
