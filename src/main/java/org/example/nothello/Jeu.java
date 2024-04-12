package org.example.nothello;

import java.util.*;
import java.util.List;

public class Jeu
{
    private List<Player>  players;
    private Plateau plateau;


    public Jeu(List<Player> players)
    {
        this.plateau = new Plateau(players.get(0),players.get(1));
        this.players = players;

        for (Player j : this.players)
            j.setPartiEnCourt(this.plateau);


        System.out.println(plateau.getJoueurActuelle().getCouleur());
        System.out.println(plateau.getJoueurAdverse().getCouleur());

    }

    public void startGame()
    {
        Scanner sc = new Scanner(System.in);
        String x,y;
        int posX,posY;



        showPlateau();

        do
        {
            for (Player joueur : this.players)
            {
                showPlateau();
                if (joueur.peuJouer())
                {
                    System.out.println(joueur.getPseudo() + " à toi de joué !");

                    do
                    {
                        System.out.println("Entre le X et le Y de la piece que tu souhaite joué.");

                        do {
                            System.out.println("Entre le X ...");
                            x = sc.nextLine();
                        } while (!"01234567".contains(x));

                        do {
                            System.out.println("Entre le Y ...");
                            y = sc.nextLine();
                        } while (!"01234567".contains(x));

                        posX = Integer.parseInt(x);
                        posY = Integer.parseInt(y);

                    } while (!joueur.peuPoserDisque(posX,posY));

                    joueur.poserDisque(posX,posY);

                }
                plateau.changeDeTour();
            }

        } while (!plateau.estFini());
    }

    public void showPlateau()
    {
        int[][] plat = plateau.getPlateau();

        for (int i=0;i<20;i++)
            System.out.println(" ");

        for (int[] pa : plat) {
            for (int p : pa) {
                System.out.print("["+p+"]");
            }
            System.out.println();
        }
    }
}
