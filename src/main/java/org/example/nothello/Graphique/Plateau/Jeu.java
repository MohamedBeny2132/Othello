package org.example.nothello.Graphique.Plateau;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import org.example.nothello.Joueur.Player;

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
                if (!estFini())
                {
                    int x = (int) (e.getX()/ Case.LARGEUR);
                    int y = (int) (e.getY()/ Case.HAUTEUR);

                    if (plateau.coordonneCorrecte(x,y))
                    {

                        if (joueurActuelle.peuJouer())
                        {
                            if (joueurActuelle.peuPoserDisque(x,y))
                                joueurActuelle.jouer(x,y);
                        }
                        changeDeTour();
                    }
                }
                else
                {

                }


            }
        });
    }


    private void changeDeTour()
    {
        Player tmp = this.joueurActuelle;
        this.joueurActuelle = this.joueurAdverse;
        this.joueurAdverse = tmp;
    }

    private boolean estFini()
    {
        return plateau.estFini();
    }

}
