package org.example.nothello.Joueur;

import javafx.scene.paint.Color;
import org.example.nothello.Graphique.Plateau.Case;
import org.example.nothello.Graphique.Plateau.Disquee.Disque;
import org.example.nothello.Graphique.Plateau.Plateau;
import org.example.nothello.Joueur.Player;
import org.example.nothello.Utilitaire.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Robot extends Player
{
    private final int PROFONDEUR = 5;
    public Robot() {
        super("ROBOT", Color.WHITE);
    }


    @Override
    public void jouer(int x, int y)
    {
        Case[][] tableauOriginel = partiEnCourt.getPlateau();
        Case[][] tableau = copie(tableauOriginel);

        List<Player> joueurs = new ArrayList<>();
        joueurs.add(this);
        joueurs.add(getAdversaire());

        Position meilleurPosition = null;
        double maxPoint = 0;

        LinkedList<Case[][]> tableaux = new LinkedList<>();



        HashMap<Case[][],List<Position>> positionAtester = new HashMap<>();

        for (Position position : listPositionPossible(tableauOriginel,this))
        {
            tableau = copie(tableauOriginel);
            tableaux.add(tableau);

            for (int i = 0;i<PROFONDEUR;i++)
            {
                Case[][] tab;
                for (Player joueur : joueurs)
                {
                    tab = tableaux.poll();

                    Position pos;

                    if (!positionAtester.containsKey(tab))
                    {
                        List<Position> positionList = listPositionPossible(tab,joueur);
                        pos = positionList.removeFirst();
                        
                    }




                    tab[pos.getY()][pos.getX()].setDisque(new Disque(joueur.getCouleur()));
                    tableaux.add(copie(tab));
                }
            }

            double pointMaximalPossible = maxPoint(tableaux);
            tableaux.clear();

            if (pointMaximalPossible > maxPoint)
            {
                maxPoint = pointMaximalPossible;
                meilleurPosition = position;
            }


        }


        if (meilleurPosition != null)
            this.partiEnCourt.jouer(meilleurPosition.getX(),meilleurPosition.getY());


    }


    private double maxPoint(LinkedList<Case[][]> tabs)
    {
        double maxPoint = 0;

        if (!tabs.isEmpty())
        {
            maxPoint = comptePoint(tabs.poll());

            for (Case[][] tab : tabs)
            {
                double point = comptePoint(tab);

                if (point > maxPoint)
                    maxPoint = point;
            }
        }
        return maxPoint;
    }

    private Case[][] copie(Case[][] tableau)
    {
        Case[][] tableauCopie = new Case[tableau.length][tableau[0].length];

        for (int i = 0;i<tableauCopie.length;i++)
        {
            for (int j = 0;j<tableauCopie[0].length;j++)
            {
                tableauCopie[i][j] = tableau[i][j].getCopie();
            }
        }

        return tableauCopie;
    }


    private boolean peuRetournerDisque(Case[][] plateau,Player joueur,int x,int y)
    {
        boolean peuRetorunerAuMoinsUnDisque = false;
        Color couleurAdverse = joueur.getAdversaire().getCouleur();
        int nX,nY;

        for (int dY = -1;dY <= 1 && !peuRetorunerAuMoinsUnDisque;dY++)
        {
            for (int dX = -1;dX <= 1 && !peuRetorunerAuMoinsUnDisque;dX++)
            {
                if (!(dX == 0 && dY == 0))
                {
                    nY = y+dY;
                    nX = x+dX;

                    if (coordonneCorrecte(nX, nY) && !plateau[nY][nX].estVide() && plateau[nY][nX].getDisque().getCouleur() == couleurAdverse)
                    {
                        do
                        {
                            nY += dY;
                            nX += dX;
                        } while (coordonneCorrecte(nX, nY) && plateau[nY][nX].estVide() && plateau[nY][nX].getDisque().getCouleur() == couleurAdverse);

                        if (coordonneCorrecte(nX,nY) && !plateau[nY][nX].estVide() && plateau[nY][nX].getDisque().getCouleur() == joueur.getCouleur())
                            peuRetorunerAuMoinsUnDisque = true;
                    }
                }
            }
        }

        return peuRetorunerAuMoinsUnDisque;
    }

    public boolean coordonneCorrecte(int x,int y)
    {
        return x < Plateau.TAILLE && x >= 0 && y < Plateau.TAILLE && y >= 0;
    }




    private List<Position> listPositionPossible(Case[][] plateau,Player joueur)
    {
        List<Position> listPosition = new ArrayList<>();

        return listPosition;
    }


    private List<Position> listPosition(Case[][] plateau,Player joueur)
    {
        List<Position> listPosition = new ArrayList<>();

        for (int y = 0;y<plateau.length;y++)
        {
            for (int x = 0;x<plateau[0].length;x++)
            {
                if (!plateau[y][x].estVide() && plateau[y][x].getDisque().getCouleur() == joueur.getCouleur())
                    listPosition.add(new Position(x,y));
            }
        }
        return listPosition;
    }





    private double comptePoint(Case[][] plateau)
    {
        double point = 0;

        for (int i = 0;i<plateau.length;i++)
        {
            for (int j = 0;j<plateau[0].length;j++)
            {
                if (!plateau[i][j].estVide() && plateau[i][j].getDisque().getCouleur() == getCouleur())
                    point++;
            }
        }

        return point;
    }
}