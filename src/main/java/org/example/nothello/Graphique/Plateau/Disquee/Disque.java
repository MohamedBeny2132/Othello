package org.example.nothello.Graphique.Plateau.Disquee;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disque extends Circle
{
    private static final double RAYON = 15;
    private Color couleur;

    public Disque(Color couleur)
    {
        super(RAYON,couleur);
        this.couleur = couleur;
    }

    public Color getCouleur()
    {
        return this.couleur;
    }

    public void retourne()
    {
        Color nvCouleur = couleur == Color.WHITE ? Color.BLACK : Color.WHITE;
        setFill(nvCouleur);
        this.couleur = nvCouleur;
    }



}
