package com.example.memory;

import javafx.scene.Node;
import javafx.scene.image.ImageView;


public class Card {
    private String imagePath;
    private boolean isFlipped;
    private boolean isMatched;

    private ImageView imageView;

    public Card(String imagePath) {
        this.imagePath = imagePath;
        this.isFlipped = false;
        this.isMatched = false;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void flip() {
        isFlipped = !isFlipped;
    }

    public boolean isFlipped() {

        return isFlipped;
    }

    public boolean isMatched() {

        return isMatched;
    }

    public void setMatched(boolean matched) {

        isMatched = matched;
    }

    public Node getNode() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}

