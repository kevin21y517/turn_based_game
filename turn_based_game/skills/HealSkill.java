package com.turn_based_game.skills;

import com.turn_based_game.characters.Character;
import com.turn_based_game.monsters.Monster;

public class HealSkill implements Skill {
    private int healAmount;
    private int mpCost;

    public HealSkill() {
        this.healAmount = 20;  // 技能恢復量
        this.mpCost = 15;  // 技能消耗的 MP
    }

    @Override
    public String getName() {
        return "治癒術";
    }

    @Override
    public int getMpCost() {
        return mpCost;
    }

    @Override
    public void use(Character player, Monster monster) {
        // 治癒術不需要目標，只恢復自身
        if (player.getMp() >= mpCost) {
            player.setMp(player.getMp() - mpCost);
            player.setHp(Math.min(player.getHp() + healAmount, player.getMaxHp()));  // 恢復 HP，但不超過最大 HP
            System.out.println(player.getName() + " 使用了 " + getName() + "，恢復了 " + healAmount + " 點 HP，消耗了 " + mpCost + " 點 MP，當前 MP: " + player.getMp());
        } else {
            System.out.println("MP 不足，無法使用 " + getName() + "！");
        }
    }
}
