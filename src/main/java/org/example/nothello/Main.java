package org.example.nothello;

import org.example.nothello.Jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Jeu game;

    public static void main(String[] args)
    {
        game = new Jeu(createPerso());
        game.startGame();


    }

    public static List<Player> createPerso()
    {
        List<Player> players = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choix1,choix2;
        String pseudo1;
        String pseudo2;

        do {
            System.out.println("""
                    1. Jouer contre un robot.
                    2. Jouer contre un amis !
                    """);

            choix1 = sc.nextInt();
        } while (choix1 != 1 && choix1 != 2);


        if (choix1 == 1)
        {
            do {
                System.out.println("""
                    1. Joueur blanc
                    2. Joueur noir
                    """);
                choix2 = sc.nextInt();
            } while (choix2 != 1 && choix2 != 2);

            do
            {
                System.out.println("Entrez votre pseudo");
                pseudo1 = sc.nextLine();
            } while (pseudo1.length() > 20 || pseudo1.length() < 2);


        }
        else
        {
            do
            {
                System.out.println("Joueur blanc : Entrez votre pseudo");
                pseudo1 = sc.next();
            } while (pseudo1.length() > 20 || pseudo1.length() < 2);

            do
            {
                System.out.println("Joueur noir : Entrez votre pseudo");
                pseudo2 = sc.next();
            } while (pseudo1.equals(pseudo2) || pseudo2.length() > 20 || pseudo2.length() < 2);

            players.add(new Player(pseudo1,1));
            players.add(new Player(pseudo2,2));
        }

        return players;
    }
}