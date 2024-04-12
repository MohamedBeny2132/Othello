package org.example.nothello.Graphique;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.nothello.Position;

public class Case extends StackPane
{
    public static final int LARGEUR = 100;
    public static final int HAUTEUR = 100;
    private Disque disque;
    private Color color;
    private Position position;

    public Case(int x,int y)
    {
        this.color = Color.rgb(20,80,50);
        this.position = new Position(x,y);
        this.disque = null;

        Rectangle rectangle = new Rectangle(LARGEUR,HAUTEUR,color);
        rectangle.setStroke(Color.WHITE);
        getChildren().add(rectangle);

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

    public void setDisque(Disque disque)
    {
        this.disque = disque;
        getChildren().add(disque);
    }

    public boolean estVide()
    {
        return this.disque == null;
    }
}
