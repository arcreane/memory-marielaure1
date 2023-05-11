package com.example.memory;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.example.memory.Card;
import com.example.memory.CardGrid;
import com.example.memory.Logique;

public class CardGrid {

    private int numCards;
    private List<Card> cards;
    private Logique logique;

    public CardGrid(String difficulty, String theme) {
        // détermine le nombre de cartes selon le niveau de difficulté
        switch (difficulty) {
            case "Easy":
                numCards = 24;
                break;
            case "Medium":
                numCards = 32;
                break;
            case "Hard":
                numCards = 48;
                break;
            default:
                break;
        }

        // création d'une liste de cartes
        cards = new ArrayList<>();
        for (int i = 1; i <= numCards; i++) {
            String imagePath = "/com/example/memory/images/" + theme + "/" + i + ".png";
            Image frontImage = new Image(getClass().getResourceAsStream(imagePath));
            ImageView frontImageView = new ImageView(frontImage);
            frontImageView.setFitWidth(100);
            frontImageView.setFitHeight(100);

            // création d'une image pour le dos des cartes
            Image backImage = new Image(getClass().getResourceAsStream("/com/example/memory/images/base.png"));
            ImageView backImageView = new ImageView(backImage);
            backImageView.setFitWidth(100);
            backImageView.setFitHeight(100);

            Card card = new Card("Card" + i, backImageView, frontImageView);
            cards.add(card);
        }

        // création de l'objet Logique
        logique = new Logique();
    }

    public GridPane getGridPane(String nomJoueur1, String nomJoueur2) {
        // création de la grille
        GridPane gridPane = new GridPane();

        int numCols = (int) Math.sqrt(numCards);
        int numRows = numCards / numCols;

        // ajout du nom et du score des joueurs
        Label scoreLabel1 = new Label(nomJoueur1 + ": 0 points");
        Label scoreLabel2 = new Label(nomJoueur2 + ": 0 points");
        HBox scoreBox = new HBox(scoreLabel1, scoreLabel2);
        scoreBox.setSpacing(20);
        scoreBox.setAlignment(Pos.CENTER);
        gridPane.add(scoreBox, 0, numRows + 1, numCols, 1);

        // mélange des cartes et placement dans la grille
        Collections.shuffle(cards);
        int cardIndex = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Card card = cards.get(cardIndex);
                Node cardNode = card.getBackImageView();

                // ajoute l'événement de clic à la carte
                card.setOnCardClicked(event -> {
                    logique.selectCard(card);
                });

                gridPane.add(cardNode, col, row);
                cardIndex++;
            }
        }

        return gridPane;
    }

    // Récupération du nombre de card
    public Integer getNumCards() {
        return numCards;
    }

    // Récupérer une card
    public Card getCard(int index) {
        return cards.get(index);
    }

}


