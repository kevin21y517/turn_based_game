package com.turn_based_game.system;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int levelNumber;

    // 註冊觀察者（怪物）
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // 通知所有觀察者
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(levelNumber);  // 向觀察者傳遞樓層編號
        }
    }

    // 設置樓層編號，並通知觀察者
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
        notifyObservers();  // 樓層變化時通知所有觀察者
    }

    // 獲取當前樓層編號
    public int getLevelNumber() {
        return levelNumber;
    }
}
