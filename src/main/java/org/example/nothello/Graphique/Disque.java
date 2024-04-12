package org.example.nothello.Graphique;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disque extends Circle
{
    private Color couleur;

    public Disque(Color couleur)
    {
        this.couleur = couleur;
    }

    private Color getCouleur()
    {
        return this.couleur;
    }



}
