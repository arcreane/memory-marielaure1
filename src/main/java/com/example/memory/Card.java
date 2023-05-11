package com.example.memory;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Card extends StackPane {
    private String name;
    private ImageView backImageView; // Image pour représenter le dos de la carte
    private ImageView frontImageView; // Image pour représenter le recto de la carte
    private boolean isFaceUp; // Indique si la carte est retournée face visible ou pas
    private ImageView imageView; // Image de la carte à afficher
    private VBox vbox; // Boîte pour stocker le nom et l'image

    public Card(String name, ImageView backImageView, ImageView frontImageView) {
        this.name = name;
        this.backImageView = backImageView;
        this.frontImageView = frontImageView;
        imageView = new ImageView(backImageView.getImage()); // Initialiser l'imageView avec l'image du dos de la carte
        vbox = new VBox(); // Créer une nouvelle boîte
        vbox.getChildren().addAll(new Label(name), imageView); // Ajouter le nom et l'image à la boîte
        getChildren().add(vbox); // Ajouter la boîte  à la carte
        isFaceUp = false; // Initialiser toutes les cartes à l'envers

        this.setOnCardClicked(event -> {
            System.out.println("Carte cliquée");
        });
    }

    // Retounrer la face selon la face précedente
    public void flip() {
        if (isFaceUp) {
            showBack();
        } else {
            showFront();
        }
    }

    // Vérifier si les 2 card choisi ^par le joueur match
    public boolean matches(Card other) {
        return this.getName().equals(other.getName());
    }

    public String getName() {
        return name;
    }

    public ImageView getFrontImageView() {
        return frontImageView;
    }


    public ImageView getBackImageView() {
        return backImageView;
    }
    
    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void showFront() {
        imageView.setImage(frontImageView.getImage());
        isFaceUp = true;
    }

    public void showBack() {
        imageView.setImage(backImageView.getImage());
        isFaceUp = false;
    }

    public void setOnCardClicked(EventHandler<MouseEvent> eventHandler) {
        vbox.setOnMouseClicked(eventHandler); // Utiliser la boîte pour gérer les clics sur la carte
    }

    public ImageView getImageView() {
        return imageView;
    }
}

