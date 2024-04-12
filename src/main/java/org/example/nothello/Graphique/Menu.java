package org.example.nothello.Graphique;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.nothello.JoueurBlanc;
import org.example.nothello.JoueurNoir;
import org.example.nothello.Player;

public class Menu extends Scene {
    private TextField pseudoNoirField;
    private TextField pseudoBlancField;

    public Menu() {
        super(new VBox(), 300, 200);
        VBox menuLayout = (VBox) getRoot();
        menuLayout.setSpacing(10);

        Label pseudoNoirLabel = new Label("Pseudonyme du joueur noir:");
        pseudoNoirField = new TextField();
        Label pseudoBlancLabel = new Label("Pseudonyme du joueur blanc:");
        pseudoBlancField = new TextField();

        Button validerButton = new Button("Valider");
        validerButton.setOnAction(e -> afficherPlateau());

        menuLayout.getChildren().addAll(
                pseudoNoirLabel, pseudoNoirField,
                pseudoBlancLabel, pseudoBlancField,
                validerButton
        );
    }

    private void afficherPlateau()
    {
        String pseudoNoir = pseudoNoirField.getText();
        String pseudoBlanc = pseudoBlancField.getText();

        Plateau plateau = new Plateau(new JoueurNoir(pseudoNoir), new JoueurBlanc(pseudoBlanc));

        Stage plateauStage = new Stage();
        plateauStage.setScene(new Scene(plateau, 400, 300));
        plateauStage.setTitle("Plateau de jeu");
        plateauStage.show();
    }
}
