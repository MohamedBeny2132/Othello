package org.example.nothello.Graphique;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public  class Disque extends Circle
{
    private static final double RAYON = 15;
    private Color couleur;

    public Disque(Color couleur)
    {
        super(RAYON,couleur);
        this.couleur = couleur;
    }

    private Color getCouleur()
    {
        return this.couleur;
    }



}
