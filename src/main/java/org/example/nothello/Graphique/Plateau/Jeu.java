package org.example.nothello.Graphique.Plateau;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import org.example.nothello.Player;

public class Jeu extends Scene
{
    private Plateau plateau;
    private Player joueurActuelle;
    private Player joueurAdverse;
    public Jeu(Plateau plateau)
    {
        super(plateau);
        this.plateau = plateau;
        this.joueurActuelle = plateau.getJoueurActuelle();
        this.joueurAdverse = plateau.getJoueurAdverse();
        initListener();
    }

    private void initListener()
    {
        setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                int x = (int) (e.getX()/ Case.LARGEUR);
                int y = (int) (e.getY()/ Case.HAUTEUR);

                if (joueurActuelle.peuJouer())
                {
                    if (joueurActuelle.peuPoserDisque(x,y))
                    {
                        joueurActuelle.poserDisque(x,y);
                    }
                }

                changeDeTour();


            }
        });
    }


    private void changeDeTour()
    {
        Player tmp = this.joueurActuelle;
        this.joueurActuelle = this.joueurAdverse;
        this.joueurAdverse = tmp;
    }

}
