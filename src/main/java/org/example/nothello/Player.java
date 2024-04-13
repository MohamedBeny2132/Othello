package org.example.nothello;

import javafx.scene.paint.Color;
import org.example.nothello.Graphique.Plateau.Plateau;

public class Player
{
    protected String pseudo;
    protected Color couleur;
    protected Plateau partiEnCourt;

    public Player(String pseudo, Color couleur)
    {
        this.pseudo = pseudo;
        this.couleur = couleur;
    }

    public String getPseudo()
    {
        return this.pseudo;
    }

    public Color getCouleur()
    {
        return this.couleur;
    }

    public void setPartiEnCourt(Plateau jeu)
    {
        this.partiEnCourt = jeu;
    }

    public void poserDisque(int x,int y)
    {
        this.partiEnCourt.jouer(x,y);
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
