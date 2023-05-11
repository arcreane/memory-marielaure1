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

public class CardGrid {

    private int numCards;
    private List<Card> cards;

    public CardGrid(String difficulty, String theme) {
        // Determine the number of cards based on difficulty
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

        // Create a list of cards
        cards = new ArrayList<>();
        for (int i = 1; i <= numCards; i++) {
            String imagePath = "/com/example/memory/images/" + theme + "/" + i + ".png";
            Image frontImage = new Image(getClass().getResourceAsStream(imagePath));
            ImageView frontImageView = new ImageView(frontImage);
            frontImageView.setFitWidth(100); // Adapt the size as needed
            frontImageView.setFitHeight(100);

            // Create the image to represent the back of the card
            Image backImage = new Image(getClass().getResourceAsStream("/com/example/memory/images/base.png"));
            ImageView backImageView = new ImageView(backImage);
            backImageView.setFitWidth(100); // Adapt the size as needed
            backImageView.setFitHeight(100);

            Card card = new Card("Card" + i, backImageView, frontImageView);
            cards.add(card);
        }
    }

    public GridPane getGridPane(String nomJoueur1, String nomJoueur2) {
        // Create a grid of cards from the shuffled list
        GridPane gridPane = new GridPane();

        int numCols = (int) Math.sqrt(numCards);
        int numRows = numCards / numCols;

        // Add score labels for each player
        Label scoreLabel1 = new Label(nomJoueur1 + ": 0 points");
        Label scoreLabel2 = new Label(nomJoueur2 + ": 0 points");
        HBox scoreBox = new HBox(scoreLabel1, scoreLabel2);
        scoreBox.setSpacing(20);
        scoreBox.setAlignment(Pos.CENTER);
        gridPane.add(scoreBox, 0, numRows + 1, numCols, 1);

        // Shuffle the list of cards and create a card node for each card
        Collections.shuffle(cards);
        int cardIndex = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Card card = cards.get(cardIndex);
                Node cardNode = card.getBackImageView();
                gridPane.add(cardNode, col, row);
                cardIndex++;
            }
        }

        return gridPane;
    }

}

