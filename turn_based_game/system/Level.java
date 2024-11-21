package com.turn_based_game.system;

import com.turn_based_game.monsters.Monster;
import com.turn_based_game.monsters.MonsterFactory;

public class Level extends Subject {
    private int levelNumber;  // 當前樓層編號
    private final int maxLevel = 10;  // 遊戲最大樓層
    private Monster monster;  // 每層生成的怪物

    public Level() {
        this.levelNumber = 1;  // 初始樓層
    }

    // 獲取當前樓層編號
    public int getLevelNumber() {
        return levelNumber;
    }

    // 提升樓層（進入下一層）
    public void nextLevel() {
        if (levelNumber < maxLevel) {
            levelNumber++;
            monster = MonsterFactory.createBoss();  // 根據樓層生成怪物
        } else {
            System.out.println("你已經通過了所有樓層，恭喜通關遊戲！");
        }
    }

    // 檢查是否已通關
    public boolean isMaxLevel() {
        return levelNumber >= maxLevel;
    }

    // 生成對應樓層的地圖
    public Floor generateFloor(int rows, int cols) {
        return new Floor(levelNumber, rows, cols);
    }

    public Monster getMonster() {
        return monster;
    }
}
