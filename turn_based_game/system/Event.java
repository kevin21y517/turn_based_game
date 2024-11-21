package com.turn_based_game.system;

import java.util.Random;

import com.turn_based_game.characters.Character;
import com.turn_based_game.items.Item;
import com.turn_based_game.items.ItemFactory;
import com.turn_based_game.monsters.Monster;
import com.turn_based_game.monsters.MonsterFactory;
import com.turn_based_game.player_settings.Player;
import com.turn_based_game.skills.Skill;
import com.turn_based_game.skills.SkillFactory;

public class Event {
    private Random random = new Random();

    public void triggerEvent(Player player, Character character, boolean isAtEndPoint) {
        if (isAtEndPoint) {
            // 玩家到達終點，觸發 Boss 戰鬥
            System.out.println("你到達了迷宮的終點，準備迎戰最終 Boss！");
            Monster boss = MonsterFactory.createBoss();  // 創建大 Boss
            System.out.println("你遇到了 Boss: " + boss.getClass().getSimpleName() + "！");
            Battle battle = new Battle(player, character, boss);
            battle.start();  // 開始戰鬥
        } else {
            // 普通事件的觸發邏輯
            int eventChance = random.nextInt(100);  // 隨機事件發生機率

            if (eventChance < 20) {  // 20% 機率遇到道具
                Item potion = ItemFactory.createRandomPotion();
                player.addItem(potion);
            } else if (eventChance < 40) {  // 20% 機率遇到怪物
                System.out.println("你遇到了一個怪物！準備戰鬥！");
                Monster monster = MonsterFactory.createRandomMonster();
                System.out.println("你遇到了 " + monster.getClass().getSimpleName() + "！");
                Battle battle = new Battle(player, character, monster);
                battle.start();  // 開始戰鬥
            } else if (eventChance < 100) {  // 10% 機率獲得技能
                Skill newSkill = SkillFactory.getRandomSkill(character);
                if (newSkill != null) {
                    character.addSkill(newSkill);  // 添加新技能
                } else {
                    System.out.println("你已經掌握了所有技能！");
                }
            } else {
                System.out.println("這次沒有遇到任何事件。");
            }
        }
    }
}
