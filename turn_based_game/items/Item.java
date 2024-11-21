package com.turn_based_game.items;

import com.turn_based_game.characters.Character;

public interface Item {
    String getName();
    String getEffectType();  // 確保定義了這個方法
    void use(Character character);  // 每種道具的使用方式不同
    boolean isSameItem(Item other);  // 判斷是否為同一類型道具
}
