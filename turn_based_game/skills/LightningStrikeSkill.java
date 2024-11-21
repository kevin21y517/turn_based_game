package com.turn_based_game.skills;

import com.turn_based_game.characters.Character;
import com.turn_based_game.monsters.Monster;

// public class LightningStrikeSkill implements Skill {
//     private int damage;

//     public LightningStrikeSkill() {
//         this.damage = 50;  // 預設傷害值
//     }

//     @Override
//     public String getName() {
//         return "雷擊術";
//     }

//     @Override
//     public void use(Character player, Monster monster) {
//         int newHp = monster.getHp() - damage;
//         if (newHp < 0) newHp = 0;  // 確保 HP 不會低於 0
//         monster.setHp(newHp);
//         System.out.println(monster.getName() + " 受到 " + getName() + " 造成的 " + damage + " 點傷害，當前 HP: " + monster.getHp());
//     }
// }
