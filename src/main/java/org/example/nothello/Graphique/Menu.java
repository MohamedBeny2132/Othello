package org.example.nothello.Graphique;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.nothello.Graphique.Plateau.Jeu;
import org.example.nothello.Graphique.Plateau.Plateau;
import org.example.nothello.JoueurBlanc;
import org.example.nothello.JoueurNoir;

public class Menu extends Pane
{
    private TextField pseudoNoirField;
    private TextField pseudoBlancField;

    public Menu()
    {
        Label pseudoNoirLabel = new Label("Pseudonyme du joueur noir:");
        pseudoNoirField = new TextField();
        Label pseudoBlancLabel = new Label("Pseudonyme du joueur blanc:");
        pseudoBlancField = new TextField();

        Button validerButton = new Button("Valider");
        validerButton.setOnAction(e -> afficherPlateau());

        getChildren().addAll(pseudoNoirLabel, pseudoNoirField, pseudoBlancLabel, pseudoBlancField, validerButton);
    }

    private void afficherPlateau()
    {
        String pseudoNoir = pseudoNoirField.getText();
        String pseudoBlanc = pseudoBlancField.getText();

        Plateau plateau = new Plateau(new JoueurNoir(pseudoNoir), new JoueurBlanc(pseudoBlanc));

        Stage stage = (Stage) getScene().getWindow();

        stage.setScene(new Jeu(plateau));
    }
}
