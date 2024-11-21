package com.turn_based_game.skills;

import com.turn_based_game.characters.Character;
import com.turn_based_game.monsters.Monster;

// public class ShieldSkill implements Skill {
//     private int defenseBoost;

//     public ShieldSkill() {
//         this.defenseBoost = 10;  // 預設提升的防禦力
//     }

//     @Override
//     public String getName() {
//         return "護盾術";
//     }

//     @Override
//     public void use(Character player, Monster monster) {
//         int newDef = player.getDef() + defenseBoost;
//         player.setDef(newDef);
//         System.out.println(player.getName() + " 使用了 " + getName() + "，防禦力提升了 " + defenseBoost + " 點，當前防禦力: " + player.getDef());
//     }
// }
