package com.turn_based_game.monsters;

import java.util.Random;

import com.turn_based_game.system.Level;

public class MonsterFactory {
    private static Random random = new Random();
    private static Level level;  // 引用 Level 實例

    public static void setLevel(Level levelInstance) {
        level = levelInstance;
    }

    // 隨機生成怪物
    public static Monster createRandomMonster() {
        int monsterChance = random.nextInt(100);  // 生成一個 0 到 99 之間的隨機數
        int levelNumber = (level != null) ? level.getLevelNumber() : 1;  // 獲取當前樓層，默認為 1

        Monster monster;

        if (monsterChance < 40) {  // 40% 機率生成 Goblin
            // 在創建時根據樓層調整屬性
            int hp = 50 + (levelNumber * 10);
            int atk = 15 + (levelNumber * 2);
            int def = 5 + (levelNumber * 1);
            monster = new Goblin(hp, atk, def);  // 創建 Goblin 並設置屬性
        } else if (monsterChance < 70) {  // 30% 機率生成 Orc
            int hp = 80 + (levelNumber * 15);
            int atk = 20 + (levelNumber * 3);
            int def = 10 + (levelNumber * 2);
            monster = new Orc(hp, atk, def);  // 創建 Orc 並設置屬性
        } else {  // 30% 機率生成 Skeleton
            int hp = 150 + (levelNumber * 20);
            int atk = 30 + (levelNumber * 5);
            int def = 20 + (levelNumber * 3);
            monster = new Skeleton(hp, atk, def);  // 創建 Skeleton 並設置屬性
        }

        return monster;
    }

    public static Monster createBoss() {
        int levelNumber = (level != null) ? level.getLevelNumber() : 1;  // 獲取當前樓層，默認為 1
        int bossHp = 300 + (levelNumber * 30);
        int bossAtk = 50 + (levelNumber * 5);
        int bossDef = 30 + (levelNumber * 4);
        Monster boss = new Minotaur(bossHp, bossAtk, bossDef);  // Boss 屬性隨樓層增加
        return boss;
    }
}
