package org.example.nothello.Graphique;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainView extends Application {
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setScene(new Menu());
        stage.show();
    }
}
