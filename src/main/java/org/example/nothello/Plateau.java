package org.example.nothello;

import java.util.*;
import java.util.List;

public class Plateau
{
    private final int TAILLE_TABLEAU = 8;
    private int[][] plateau;

    private Player joueurActuelle;
    private Player joueurAdverse;

    public Plateau(Player j1, Player j2)
    {
        this.plateau = new int[TAILLE_TABLEAU][TAILLE_TABLEAU];

        this.joueurActuelle = j1;
        this.joueurAdverse = j2;

        initPlateau();
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
                    if (peuPoserDisque(x+dX,y+dY))
                        peuJouer = true;

        }
        return peuJouer;
    }

    private boolean estUneCaseVide(int x,int y)
    {
        return this.plateau[y][x] == 0;
    }

    private boolean peuRetournerDisque(int x,int y)
    {
        boolean peuRetorunerAuMoinsUnDisque = false;
        int couleurAdverse = this.joueurAdverse.getCouleur();
        int nX,nY;

        for (int dY = -1;dY <= 1 && !peuRetorunerAuMoinsUnDisque;dY++)
        {
            for (int dX = -1;dX <= 1 && !peuRetorunerAuMoinsUnDisque;dX++)
            {
                nY = y+dY;
                nX = x+dX;

                while (coordonneCorrecte(nX,nY) && this.plateau[nY][nX] == couleurAdverse)
                {
                    nY += dY;
                    nX += dX;
                }

                if (coordonneCorrecte(nX,nY) && this.plateau[nY][nX] == this.joueurActuelle.getCouleur() && caseNonColler(new Position(x,y),new Position(nX,nY)))
                    peuRetorunerAuMoinsUnDisque = true;

            }
        }

        return peuRetorunerAuMoinsUnDisque;
    }

    private boolean caseNonColler(Position c1, Position c2)
    {
        return Math.abs(c1.getX()-c2.getX()) > 1 || Math.abs(c1.getY()- c2.getY()) > 1;
    }

    public void poserDisque(int x,int y)
    {
        this.plateau[y][x] = this.joueurActuelle.getCouleur();
        retourneDisque(x,y);
    }

    private void retourneDisque(int x,int y)
    {
        List<Position> positionCaseARetourner = new ArrayList<>();

        int couleurAdverse = this.joueurAdverse.getCouleur();
        int nX,nY;

        for (int dY = -1;dY <= 1;dY++)
        {
            for (int dX = -1;dX <= 1;dX++)
            {
                nY = y+dY;
                nX = x+dX;

                while (coordonneCorrecte(nX,nY) && this.plateau[nY][nX] == couleurAdverse)
                {
                    positionCaseARetourner.add(new Position(nX,nY));
                    nY += dY;
                    nX += dX;
                }

                if (coordonneCorrecte(nX,nY) && this.plateau[nY][nX] == this.joueurActuelle.getCouleur() && caseNonColler(new Position(x,y),new Position(nX,nY)))
                {
                    for(Position pos : positionCaseARetourner)
                        this.plateau[pos.getY()][pos.getX()] = this.joueurActuelle.getCouleur();
                }
                else
                {
                    positionCaseARetourner = new ArrayList<>();
                }
            }
        }

    }


    public boolean estFini()
    {
        return plateauEstPlein() || !personneNePeuxJouer() ;
    }

    private boolean plateauEstPlein()
    {
        boolean estPlein = true;

        for (int i = 0;i<TAILLE_TABLEAU && estPlein;i++)
        {
            for (int j = 0;j<TAILLE_TABLEAU && estPlein;j++)
            {
                if (this.plateau[i][j] == 0)
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




    public boolean coordonneCorrecte(int x,int y)
    {
        return x < TAILLE_TABLEAU && x >= 0 && y < TAILLE_TABLEAU && y >= 0;
    }

    public void changeDeTour()
    {
        Player tmp = this.joueurActuelle;
        this.joueurActuelle = this.joueurAdverse;
        this.joueurAdverse = tmp;
    }

    private void initPlateau()
    {
        for (int y = 0;y<TAILLE_TABLEAU;y++)
            for (int x = 0;x<TAILLE_TABLEAU;x++)
                this.plateau[y][x] = 0;

        this.plateau[3][3] = 1;
        this.plateau[4][4] = 1;

        this.plateau[3][4] = 2;
        this.plateau[4][3] = 2;
    }




    private List<Position> positionDisque(Player joueurViser)
    {
        List<Position> position = new ArrayList<>();

        for (int y = 0;y<TAILLE_TABLEAU;y++)
            for (int x = 0;x<TAILLE_TABLEAU;x++)
                if (this.plateau[y][x] == joueurViser.getCouleur())
                    position.add(new Position(x,y));

        return position;
    }

    public HashMap<Position,List<Position>> posCasesPrenable()
    {
        HashMap<Position,List<Position>> mapCasesPrenable = new HashMap<>();
        List<Position> posCase = positionDisque(joueurActuelle);
        List<Position> tmp;
        List<Position> posPositionPrenable;

        int x,y,nX,nY;

        for (Position pos : posCase)
        {
            x = pos.getX();
            y = pos.getY();
            posPositionPrenable = new ArrayList<>();

            for (int dY = -1;dY <= 1;dY++)
            {
                for (int dX = -1;dX <= 1;dX++)
                {
                    tmp = new ArrayList<>();

                    nY = y+dY;
                    nX = x+dX;

                    while (coordonneCorrecte(nX,nY) && this.plateau[nY][nX] == joueurAdverse.getCouleur())
                    {
                        tmp.add(new Position(nX,nY));
                        nY += dY;
                        nX += dX;
                    }

                    if (coordonneCorrecte(nX,nY) && this.plateau[nY][nX] == this.joueurActuelle.getCouleur() && caseNonColler(new Position(x,y),new Position(nX,nY)))
                        posPositionPrenable.addAll(tmp);
                }
            }

            if (!posPositionPrenable.isEmpty())
                mapCasesPrenable.put(pos, posPositionPrenable);
        }
        return mapCasesPrenable;
    }




    public int[][] getPlateau()
    {
        return this.plateau;
    }

    public void setPlateau(int[][] plat)
    {
        this.plateau = plat;
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
