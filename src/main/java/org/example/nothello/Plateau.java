/*
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
 */
