package com.turn_based_game.skills;

import com.turn_based_game.characters.Character;
import com.turn_based_game.monsters.Monster;

public interface Skill {
    String getName(); // 獲取技能名稱
    int getMpCost();  // 返回技能的 MP 消耗
    void use(Character player, Monster monster); // 使用技能
}
