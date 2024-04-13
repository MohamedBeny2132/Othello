package org.example.nothello.Graphique.Plateau;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.example.nothello.Graphique.Plateau.Disquee.Disque;
import org.example.nothello.Graphique.Plateau.Disquee.DisqueBlanc;
import org.example.nothello.Graphique.Plateau.Disquee.DisqueNoir;


import org.example.nothello.Player;
import org.example.nothello.Position;

import java.util.ArrayList;
import java.util.List;

public class Plateau extends GridPane
{
    public final int TAILLE = 8;
    private Case[][] plateau;
    private Player joueurActuelle;
    private Player joueurAdverse;


    public Plateau(Player joueur1,Player joueur2)
    {
        this.joueurActuelle = joueur1;
        this.joueurAdverse = joueur2;

        joueur1.setPartiEnCourt(this);
        joueur2.setPartiEnCourt(this);
        this.plateau = new Case[TAILLE][TAILLE];
        initPlateau();
    }

    public void jouer(int x,int y)
    {
        poserDisque(x,y);
        retourneDisque(x,y);
        changeDeTour();
    }
    private void poserDisque(int x,int y)
    {
        this.plateau[y][x].setDisque(new Disque(this.joueurActuelle.getCouleur()));
    }


    private void retourneDisque(int x,int y)
    {
        List<Position> positionCaseARetourner = new ArrayList<>();

        Color couleurAdverse = this.joueurAdverse.getCouleur();
        int nX,nY;

        for (int dY = -1;dY <= 1;dY++)
        {
            for (int dX = -1;dX <= 1;dX++)
            {
                nY = y+dY;
                nX = x+dX;

                while (coordonneCorrecte(nX,nY) && !this.plateau[nY][nX].estVide() && this.plateau[nY][nX].getDisque().getCouleur() == couleurAdverse)
                {
                    positionCaseARetourner.add(new Position(nX,nY));
                    nY += dY;
                    nX += dX;
                }

                if (coordonneCorrecte(nX,nY) && !this.plateau[nY][nX].estVide() && this.plateau[nY][nX].getDisque().getCouleur() == this.joueurActuelle.getCouleur() && caseNonColler(new Position(x,y),new Position(nX,nY)))
                {
                    for (Position pos : positionCaseARetourner)
                        this.plateau[pos.getY()][pos.getX()].getDisque().retourne();

                }


                positionCaseARetourner.clear();

            }
        }

    }


    private boolean peuRetournerDisque(int x,int y)
    {
        boolean peuRetorunerAuMoinsUnDisque = false;
        Color couleurAdverse = this.joueurAdverse.getCouleur();
        int nX,nY;

        for (int dY = -1;dY <= 1 && !peuRetorunerAuMoinsUnDisque;dY++)
        {
            for (int dX = -1;dX <= 1 && !peuRetorunerAuMoinsUnDisque;dX++)
            {
                nY = y+dY;
                nX = x+dX;

                while (coordonneCorrecte(nX,nY) && !this.plateau[nY][nX].estVide() && this.plateau[nY][nX].getDisque().getCouleur() == couleurAdverse)
                {
                    nY += dY;
                    nX += dX;
                }

                if (coordonneCorrecte(nX,nY) && !this.plateau[nY][nX].estVide() && this.plateau[nY][nX].getDisque().getCouleur() == this.joueurActuelle.getCouleur() && caseNonColler(new Position(x,y),new Position(nX,nY)))
                    peuRetorunerAuMoinsUnDisque = true;

            }
        }

        return peuRetorunerAuMoinsUnDisque;
    }


    public boolean peuPoserDisque(int x,int y)
    {
        return  coordonneCorrecte(x,y) && estUneCaseVide(x,y) && peuRetournerDisque(x,y);
    }

    public boolean peuJouer()
    {
        boolean peuJouer = false;
        List<Position> posDisque = positionDisque(this.joueurAdverse);

        Position pos;
        int x,y;

        for (int i = 0;i<posDisque.size() && !peuJouer;i++)
        {
            pos = posDisque.get(i);

            x = pos.getX();
            y = pos.getY();

            for (int dY = -1;dY <= 1 && !peuJouer;dY++)
                for (int dX = -1; dX <= 1 && !peuJouer; dX++)
                    if (peuRetournerDisque(x+dX,y+dY))
                        peuJouer = true;

        }
        return peuJouer;
    }

    private boolean estUneCaseVide(int x,int y)
    {
        return this.plateau[y][x].estVide();
    }


    private boolean coordonneCorrecte(int x,int y)
    {
        return x < TAILLE && x >= 0 && y < TAILLE && y >= 0;
    }
    private boolean caseNonColler(Position c1, Position c2)
    {
        return Math.abs(c1.getX()-c2.getX()) > 1 || Math.abs(c1.getY()- c2.getY()) > 1;
    }

    private List<Position> positionDisque(Player joueurViser)
    {
        List<Position> position = new ArrayList<>();

        for (int y = 0;y<TAILLE;y++)
        {
            for (int x = 0; x < TAILLE; x++)
            {
                Case c = this.plateau[y][x];

                if (!c.estVide() && c.getDisque().getCouleur() == joueurViser.getCouleur())
                    position.add(new Position(x, y));

            }
        }

        return position;
    }

    public boolean estFini()
    {
        return plateauEstPlein() || !personneNePeuxJouer() ;
    }

    private boolean plateauEstPlein()
    {
        boolean estPlein = true;

        for (int i = 0;i < TAILLE && estPlein;i++)
        {
            for (int j = 0;j < TAILLE && estPlein;j++)
            {
                if (this.plateau[i][j].estVide())
                    estPlein = false;
            }
        }

        return estPlein;
    }

    private boolean personneNePeuxJouer()
    {
        boolean peuJouer;

        peuJouer = peuJouer();


        changeDeTour();
        peuJouer = peuJouer();

        changeDeTour();

        return peuJouer;
    }

    private void changeDeTour()
    {
        Player tmp = this.joueurActuelle;
        this.joueurActuelle = this.joueurAdverse;
        this.joueurAdverse = tmp;
    }

    private void initPlateau()
    {
        for (int y = 0;y<TAILLE;y++)
        {
            for (int x = 0; x < TAILLE; x++)
            {
                this.plateau[y][x] = new Case(x, y);
                add(plateau[y][x],x,y);
            }
        }

        this.plateau[3][3].setDisque(new DisqueBlanc());
        this.plateau[4][4].setDisque(new DisqueBlanc());

        this.plateau[3][4].setDisque(new DisqueNoir());
        this.plateau[4][3].setDisque(new DisqueNoir());
    }

    public Player getJoueurActuelle()
    {
        return this.joueurActuelle;
    }

    public Player getJoueurAdverse()
    {
        return this.joueurAdverse;
    }
}
