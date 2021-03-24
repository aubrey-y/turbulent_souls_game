package org.example.services;

import javafx.scene.image.ImageView;

public class MonsterService {

    private ImageView imageView;


    public void moveX(double value) {
        this.imageView.setTranslateX(value);
    }

    public void moveY(double value) {
        this.imageView.setTranslateY(value);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public MonsterService setImageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }
}
