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

import com.example.memory.Card;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
                difficulty = difficultyChoice.getValue();
                nomJoueur1 = nomField1.getText();
                nomJoueur2 = nomField2.getText();
                System.out.println(theme);
                System.out.println(difficulty);
                System.out.println(nomJoueur1);
                System.out.println(nomJoueur2);


                System.out.println("Jouer cliqué!");
                // Lancer le jeu
                // Créer la grille de jeu
                GridPane gameGrid = createCardsGrid(difficulty, theme, nomJoueur1, nomJoueur2);

                // Créer une nouvelle scène pour afficher la grille de jeu
                Scene gameScene = new Scene(gameGrid);
                // Afficher la nouvelle scène
                Stage gameStage = new Stage();
                gameStage.setScene(gameScene);
                gameStage.show();
            });

            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(titre, themeLabel, themeChoice, difficultyLabel, difficultyChoice,
                    nomLabel1, nomField1, nomLabel2, nomField2, startButton);
            scene = new Scene(layout, 400, 500);
            stage.setScene(scene);
            stage.show();
        }

    public Node getNode() {
        return imageView;
    }
    public GridPane createCardsGrid(String difficulty, String theme, String nomJoueur1, String nomJoueur2) {
        // Détermine le nombre de cartes en fonction de la difficulté
        int numCards = 0;

        switch (difficulty) {
            case "Easy":
                numCards = 16;
                break;
            case "Medium":
                numCards = 36;
                break;
            case "Hard":
                numCards = 64;
                break;
            default:
                break;
        }

        // Créer une liste de cartes
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i <= numCards; i++) {
            String imagePath = "/com/example/memory/images/" + theme + "/" + i + ".png";
            Image frontImage = new Image(getClass().getResourceAsStream(imagePath));
            ImageView frontImageView = new ImageView(frontImage);
            frontImageView.setFitWidth(100); // Adapter la taille selon vos besoins
            frontImageView.setFitHeight(100);

            // Créer l'image pour représenter le dos de la carte
            Image backImage = new Image(getClass().getResourceAsStream("/com/example/memory/images/base.png"));
            ImageView backImageView = new ImageView(backImage);
            backImageView.setFitWidth(100); // Adapter la taille selon vos besoins
            backImageView.setFitHeight(100);

            Card card = new Card("Card" + i, backImageView, frontImageView);
            cards.add(card);
        }

        // Créer une grille de cartes à partir de la liste mélangée
        GridPane gridPane = new GridPane();

        int numCols = (int) Math.sqrt(numCards);
        int numRows = numCards / numCols;

        // Ajouter les labels de score pour chaque joueur
        Label scoreLabel1 = new Label(nomJoueur1 + ": 0 points");
        Label scoreLabel2 = new Label(nomJoueur2 + ": 0 points");
        HBox scoreBox = new HBox(scoreLabel1, scoreLabel2);
        scoreBox.setSpacing(20);
        scoreBox.setAlignment(Pos.CENTER);
        gridPane.add(scoreBox, 0, numRows + 1, numCols, 1);

        // Ajouter les cartes à la grille
        for (int i = 0; i < numCards; i++) {
            Card card = cards.get(i);
            int row = i / numRows;
            int col = i % numRows;
            gridPane.add(card, col, row);
        }
        return gridPane;
    }

    public static void main(String[] args) {
            launch();
        }

}
