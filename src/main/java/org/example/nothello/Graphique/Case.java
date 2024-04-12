package org.example.nothello.Graphique;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.nothello.Position;

public class Case extends Rectangle
{
    private Disque disque;
    private Color color;
    private Position position;

    public Case(Color color,int x,int y)
    {
        this.color = color;
        this.position = new Position(x,y);
        this.disque = null;
    }

    public Color getColor()
    {
        return this.color;
    }

    public Disque getDisque()
    {
        return this.getDisque();
    }

    public Position getPosition()
    {
        return this.position;
    }
}
