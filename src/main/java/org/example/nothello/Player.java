package org.example.nothello;

import org.example.nothello.Plateau;

public class Player
{
    protected String pseudo;
    protected int couleur;
    protected Plateau partiEnCourt;

    public Player(String pseudo, int couleur)
    {
        this.pseudo = pseudo;
        this.couleur = couleur;
    }

    public String getPseudo()
    {
        return this.pseudo;
    }

    public int getCouleur()
    {
        return this.couleur;
    }

    public void setPartiEnCourt(Plateau jeu)
    {
        this.partiEnCourt = jeu;
    }

    public void poserDisque(int x,int y)
    {
        this.partiEnCourt.poserDisque(x,y);
    }

    public boolean peuJouer()
    {
        return this.partiEnCourt.peuJouer();
    }

    public boolean peuPoserDisque(int x,int y)
    {
        return this.partiEnCourt.peuPoserDisque(x,y);
    }

}
