package org.example.services;

import org.example.dto.Monster;

import java.util.HashMap;
import java.util.Map;

public class MonsterService {

    private Map<Integer, Monster> monsterMapping = new HashMap<>();

    public MonsterService() {

    }

    public void addMonster(Integer key, Monster value) {
        this.monsterMapping.put(key, value);
    }


//    public void moveX(double value) {
//        this.imageView.setTranslateX(value);
//    }
//
//    public void moveY(double value) {
//        this.imageView.setTranslateY(value);
//    }
//
//    public ImageView getImageView() {
//        return imageView;
//    }
//
//    public MonsterService setImageView(ImageView imageView) {
//        this.imageView = imageView;
//        return this;
//    }
}
