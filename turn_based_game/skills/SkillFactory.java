package com.turn_based_game.skills;

import com.turn_based_game.characters.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkillFactory {
    private static List<Skill> allSkills = new ArrayList<>();
    private static Random random = new Random();

    static {
        // 初始化可用技能
        allSkills.add(new FireballSkill());
        allSkills.add(new HealSkill());
        // allSkills.add(new ShieldSkill());
        // allSkills.add(new LightningStrikeSkill());
    }

    // 隨機生成技能，如果玩家還沒有此技能則返回
    public static Skill getRandomSkill(Character character) {
        List<Skill> availableSkills = new ArrayList<>(allSkills);

        // 移除玩家已經擁有的技能
        availableSkills.removeAll(character.getSkills());

        if (availableSkills.isEmpty()) {
            return null;  // 所有技能已經獲得
        }

        // 從剩餘技能中隨機挑選
        return availableSkills.get(random.nextInt(availableSkills.size()));
    }
}
