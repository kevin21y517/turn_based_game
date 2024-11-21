package com.turn_based_game.items;

import java.util.Random;

public class ItemFactory {
    public static Item createPotion(String type) {
        switch (type) {
            case "heal":
                return new HealPotion("回復藥水", 20);
            case "atk_boost":
                return new AttackBoostPotion("攻擊藥水", 10);
            case "def_boost":
                return new DefenseBoostPotion("防禦藥水", 10);
            default:
                throw new IllegalArgumentException("未知的道具類型: " + type);
        }
    }

    // 隨機生成一個道具
    public static Item createRandomPotion() {
        String[] potionTypes = { "heal", "atk_boost", "def_boost" };
        int randomIndex = new Random().nextInt(potionTypes.length);
        return createPotion(potionTypes[randomIndex]);
    }
}
