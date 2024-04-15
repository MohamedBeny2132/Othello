package org.example.nothello.Joueur;

import javafx.scene.paint.Color;
import org.example.nothello.Graphique.Plateau.Plateau;

public class Player
{
    protected String pseudo;
    protected Color couleur;
    protected Plateau partiEnCourt;

    private Player adversaire;

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

    public void jouer(int x, int y)
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

    public void setAdversaire(Player adversaire)
    {
        this.adversaire = adversaire;
    }
    public Player getAdversaire()
    {
        return this.adversaire;
    }
}
