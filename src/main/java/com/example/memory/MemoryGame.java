package com.example.memory;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;


public class MemoryGame extends Application {
        private Stage stage;
        private Scene scene;
        private String theme;
        private int difficulty;
        private String nomJoueur1;
        private String nomJoueur2;

        @Override
        public void start(Stage stage) {

            this.stage = stage;

            // Titre du jeux
            Label titre = new Label("Memory");
            titre.setFont(new Font("Arial", 36));

            // Input Select pour choisir le theme
            Label themeLabel = new Label("Choisissez un thème:");
            ChoiceBox<String> themeChoice = new ChoiceBox<>();
            themeChoice.getItems().addAll("Star Wars", "Harry Potter", "Hunter x Hunter");
            themeChoice.setValue("Star Wars");

            // Input Select pour choisir le niveau de difficulté du jeu
            Label difficultyLabel = new Label("Choisissez une difficulté:");
            ChoiceBox<String> difficultyChoice = new ChoiceBox<>();
            difficultyChoice.getItems().addAll("Easy", "Medium", "Hard");
            difficultyChoice.setValue("Easy");

            // Récupération du nom des joueurs
            Label nomLabel1 = new Label("Nom du joueur n°1:");
            TextField nomField1 = new TextField();

            Label nomLabel2 = new Label("Nom du joueur n°2:");
            TextField nomField2 = new TextField();

            // Bouton start pour commencer à jouer et ses actions
            Button startButton = new Button("Jouer");
            startButton.setOnAction(e -> {
                theme = themeChoice.getValue();
                difficulty = difficultyChoice.getSelectionModel().getSelectedIndex();
                nomJoueur1 = nomField1.getText();
                nomJoueur2 = nomField2.getText();
                // Lancer le jeu
            });

            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(titre, themeLabel, themeChoice, difficultyLabel, difficultyChoice,
                    nomLabel1, nomField1, nomLabel2, nomField2, startButton);
            scene = new Scene(layout, 400, 500);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }

}
