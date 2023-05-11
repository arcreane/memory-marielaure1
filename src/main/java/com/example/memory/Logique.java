package com.example.memory;
import com.example.memory.Card;

import java.util.ArrayList;
import java.util.List;

public class Logique {
    private Card firstCard;
    private List<Card> matchedCards;
    private boolean canFlip;
    private int score;

    public Logique() {
        this.firstCard = null;
        this.matchedCards = new ArrayList<>();
        this.canFlip = true;
        this.score = 0;
    }

    public void selectCard(Card card) {
        if (canFlip && !matchedCards.contains(card)) {
            card.flip();

            if (firstCard == null) {
                firstCard = card;
            } else {
                canFlip = false;

                if (firstCard.matches(card)) {
                    matchedCards.add(firstCard);
                    matchedCards.add(card);
                    score++;
                    firstCard = null;
                    canFlip = true;
                } else {
                    // wait for 5 seconds before flipping the cards back
                    new Thread(() -> {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        firstCard.flip();
                        card.flip();
                        firstCard = null;
                        canFlip = true;
                    }).start();
                }
            }
        }
    }

    public boolean isGameFinished(int numCards) {
        return matchedCards.size() == numCards;
    }

    public int getScore() {
        return score;
    }
}
