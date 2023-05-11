package com.example.memory;

import com.example.memory.CardGrid;
import com.example.memory.Logique;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MemoryGame extends Application {
    private Stage stage;
    private Scene scene;
    private String theme;
    private String difficulty;
    private String nomJoueur1;
    private String nomJoueur2;

    private ImageView imageView;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Titre du jeux
        Label titre = new Label("Memory");
        titre.setFont(new Font("Arial", 36));

        // Input Select pour choisir le theme
        Label themeLabel = new Label("Choisissez un thème:");
        ChoiceBox<String> themeChoice = new ChoiceBox<>();
        themeChoice.getItems().addAll("Star Wars", "One Piece", "Hunter x Hunter");
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
            difficulty = difficultyChoice.getValue();
            nomJoueur1 = nomField1.getText();
            nomJoueur2 = nomField2.getText();
            System.out.println(theme);
            System.out.println(difficulty);
            System.out.println(nomJoueur1);
            System.out.println(nomJoueur2);

            System.out.println("Jouer cliqué!");
            // Lancer le jeu

            // Créer une instance de la classe Logique
            Logique logique = new Logique();

            // Obtenir la grille de jeu à partir de la classe CardGrid
            CardGrid cardGrid = new CardGrid(difficulty, theme);
            GridPane gameGrid = cardGrid.getGridPane(nomJoueur1, nomJoueur2);



            // Ajouter la grille de jeu à une nouvelle scène
            scene = new Scene(gameGrid);

            // Ajouter les événements de clic aux cartes
            int numCards = cardGrid.getNumCards();
            for (int i = 0; i < numCards; i++) {
                Card card = cardGrid.getCard(i);


                // System.out.println(card );
                card.setOnCardClicked(event -> {
                    System.out.println("Carte cliquée");
                    logique.selectCard(card);
                });
            }


            // Afficher la scène
            stage.setScene(scene);
            stage.show();
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titre, themeLabel, themeChoice, difficultyLabel, difficultyChoice,
                nomLabel1, nomField1, nomLabel2, nomField2, startButton);
        scene = new Scene
(layout, 400, 500);
        stage.setScene(scene);
        stage.show();
    }

    public Node getNode() {
        return imageView;
    }

    public static void main(String[] args) {
        launch();
    }
}

