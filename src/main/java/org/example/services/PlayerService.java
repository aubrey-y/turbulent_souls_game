package org.example.services;

import javafx.scene.image.ImageView;

public class PlayerService {

    private ImageView imageView;

    private static final double MOVE_SIZE = 6;

    public PlayerService(ImageView imageView) {
        this.imageView = imageView;
    }

    public void moveUp() {
        this.imageView.setTranslateY(this.imageView.getTranslateY() - MOVE_SIZE);
    }

    public void moveDown() {
        this.imageView.setTranslateY(this.imageView.getTranslateY() + MOVE_SIZE);
    }

    public void moveRight() {
        this.imageView.setTranslateX(this.imageView.getTranslateX() + MOVE_SIZE);
    }

    public void moveLeft() {
        this.imageView.setTranslateX(this.imageView.getTranslateX() - MOVE_SIZE);
    }

    public void moveX(double value) {
        this.imageView.setTranslateX(this.imageView.getTranslateX() + value);
    }

    public void moveY(double value) {
        this.imageView.setTranslateY(this.imageView.getTranslateY() + value);
    }

    public void setVisible(boolean value) {
        this.imageView.setVisible(value);
    }
}
