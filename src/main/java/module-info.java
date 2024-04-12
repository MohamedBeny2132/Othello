module org.example.nothello {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.nothello to javafx.fxml;
    exports org.example.nothello;
    exports org.example.nothello.Graphique;
    opens org.example.nothello.Graphique to javafx.fxml;
}