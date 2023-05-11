package com.example.memory;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Card extends StackPane {
    private String name;
    private ImageView backImageView; // Image pour représenter le dos de la carte
    private ImageView frontImageView; // Image pour représenter le recto de la carte
    private boolean isFaceUp; // Indique si la carte est retournée face visible ou pas

    public Card(String name, ImageView backImageView, ImageView frontImageView) {
        this.name = name;
        this.backImageView = backImageView;
        this.frontImageView = frontImageView;
        getChildren().addAll(backImageView, frontImageView);
        isFaceUp = false; // Initialiser toutes les cartes à l'envers
        showBack(); // Afficher le dos de la carte
    }

    public String getName() {
        return name;
    }

    public ImageView getFrontImageView() {
        return frontImageView;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void showFront() {
        frontImageView.setVisible(true);
        backImageView.setVisible(false);
        isFaceUp = true;
    }

    public void showBack() {
        frontImageView.setVisible(false);
        backImageView.setVisible(true);
        isFaceUp = false;
    }
}
