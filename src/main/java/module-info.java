module org.example.nothello {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.nothello to javafx.fxml;

    exports org.example.nothello.Graphique;
    opens org.example.nothello.Graphique to javafx.fxml;
    exports org.example.nothello.Graphique.Plateau.Disquee;
    opens org.example.nothello.Graphique.Plateau.Disquee to javafx.fxml;
    exports org.example.nothello.Graphique.Plateau;
    opens org.example.nothello.Graphique.Plateau to javafx.fxml;
    exports org.example.nothello.Joueur;
    opens org.example.nothello.Joueur to javafx.fxml;
    exports org.example.nothello.Utilitaire;
    opens org.example.nothello.Utilitaire to javafx.fxml;
}