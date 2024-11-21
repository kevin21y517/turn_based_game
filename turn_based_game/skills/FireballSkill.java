package com.turn_based_game.skills;

import com.turn_based_game.characters.Character;
import com.turn_based_game.monsters.Monster;

public class FireballSkill implements Skill {
    private int damage;
    private int mpCost;

    public FireballSkill() {
        this.damage = 30;  // 技能傷害值
        this.mpCost = 20;  // 技能消耗的 MP
    }

    @Override
    public String getName() {
        return "火球術";
    }

    @Override
    public int getMpCost() {
        return mpCost;
    }

    @Override
    public void use(Character player, Monster monster) {
        // 檢查 MP 是否足夠
        if (player.getMp() >= mpCost) {
            // 消耗 MP
            player.setMp(player.getMp() - mpCost);

            // 自動對怪物使用技能
            if (monster != null) {
                int newHp = monster.getHp() - damage;
                if (newHp < 0) newHp = 0;  // 確保 HP 不會低於 0
                monster.takeDamage(damage);
                System.out.println(monster.getName() + " 受到 " + getName() + " 造成的 " + damage + " 點傷害，當前 HP: " + monster.getHp());
            } else {
                System.out.println("沒有目標怪物！");
            }

            System.out.println(player.getName() + " 使用了 " + getName() + "，消耗了 " + mpCost + " 點 MP，當前 MP: " + player.getMp());
        } else {
            // 提示 MP 不足
            System.out.println("MP 不足，無法使用 " + getName() + "！");
        }
    }
}
