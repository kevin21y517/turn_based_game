package com.turn_based_game.system;

import java.util.Scanner;

import com.turn_based_game.characters.Character;
import com.turn_based_game.monsters.Monster;
import com.turn_based_game.player_settings.Player;
import com.turn_based_game.skills.Skill;

public class Battle {
    private Player player;
    private Monster monster;
    private Character character;  // 玩家控制的角色
    private Scanner scanner = new Scanner(System.in);

    public Battle(Player player, Character character, Monster monster) {
        this.player = player;
        this.monster = monster;
        this.character = character;
    }

    public boolean start() {

        System.out.println("戰鬥開始！你遇到了 " + monster.getClass().getSimpleName());

        while (character.isAlive() && monster.isAlive()) {  // 使用 isAlive 判斷存活狀態
            System.out.println("\n請選擇你的行動:");
            System.out.println("1. 普通攻擊");
            System.out.println("2. 防禦");
            System.out.println("3. 使用技能");
            System.out.println("4. 使用道具");
            System.out.println("5. 查看當前狀態");
            System.out.println("6. 逃跑");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    attack();
                    break;
                case "2":
                    defend();
                    break;
                case "3":
                    if (!useSkill()) {  // 如果使用技能失敗或取消技能，繼續選擇其他操作
                        continue;
                    }
                    break;
                case "4":
                    useItem();
                    continue;
                case "5":
                    character.printStatus();  // 輸出當前角色狀態
                    continue;
                case "6":
                    if (escape()) {
                        return true;  // 成功逃跑，繼續遊戲
                    }
                    break;
                default:
                    System.out.println("無效輸入，請選擇 1 - 6。");
                    continue;
            }

            if (!character.isAlive()) {
                System.out.println(character.getName() + " 被打敗了！");
                return false;  // 玩家死亡，結束遊戲
            }

            // 如果怪物還活著，怪物攻擊玩家
            if (monster.isAlive()) {
                monsterAttack();
            }

            // 檢查戰鬥結果
            if (!character.isAlive()) {
                System.out.println(character.getName() + " 被打敗了！");
                return false;  // 玩家死亡，結束遊戲
            } else if (!monster.isAlive()) {
                System.out.println("你打敗了怪物！");
                return true;  // 戰鬥勝利，繼續遊戲
            }
        }

        return true;  // 戰鬥結束，但玩家還活著
    }

    private void attack() {
        int playerAttack = character.getAtk();
        monster.takeDamage(playerAttack);
        System.out.println(character.getName() + " 對怪物造成了 " + playerAttack + " 點傷害！");
    }

    private void defend() {
        System.out.println(character.getName() + " 選擇防禦，減少下回合受到的傷害。");
    }

    private boolean useSkill() {
        if (character.getSkills().isEmpty()) {
            System.out.println("你沒有任何技能可以使用！");
            return false;  // 技能未使用
        } else {
            while (true) {
                System.out.println("請選擇要使用的技能 (輸入編號，或輸入 '0' 取消):");

                // 顯示玩家擁有的技能列表
                for (int i = 0; i < character.getSkills().size(); i++) {
                    System.out.println((i + 1) + ". " + character.getSkills().get(i).getName());
                }

                String input = scanner.nextLine();
                try {
                    int skillIndex = Integer.parseInt(input) - 1;

                    // 輸入 '0' 可取消
                    if (skillIndex == -1) {
                        System.out.println("取消使用技能。");
                        return false;  // 技能未使用，繼續選擇其他決策
                    }

                    if (skillIndex >= 0 && skillIndex < character.getSkills().size()) {
                        Skill selectedSkill = character.getSkills().get(skillIndex);

                        // 檢查 MP 是否足夠
                        if (character.getMp() >= selectedSkill.getMpCost()) {
                            // 使用技能並消耗 MP
                            selectedSkill.use(character, monster);
                            return true;  // 技能使用成功
                        } else {
                            System.out.println("MP 不足，無法使用 " + selectedSkill.getName() + "！");
                            return false;  // 技能未使用，繼續選擇其他決策
                        }
                    } else {
                        System.out.println("無效的技能編號，請重新輸入:");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("輸入無效，請輸入有效的技能編號或 '0' 取消:");
                }
            }
        }
    }

    private void useItem() {
        if (player.getInventory().isEmpty()) {
            System.out.println("你的物品欄是空的！");
        } else {
            player.getInventory().showInventory();
            System.out.println("請選擇要使用的道具 (輸入編號，或輸入 '0' 取消):");

            while (true) {
                String input = scanner.nextLine();
                try {
                    int itemIndex = Integer.parseInt(input) - 1;  // 輸入 '0' 可取消
                    if (itemIndex == -1) {
                        System.out.println("取消使用道具。");
                        break;  // 取消使用道具，退出
                    }

                    if (itemIndex >= 0) {
                        player.getInventory().useItem(itemIndex, character);  // 使用道具
                        break;  // 道具使用成功，退出
                    } else {
                        System.out.println("無效的道具編號，請重新輸入:");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("輸入無效，請輸入有效的道具編號或 '0' 取消:");
                }
            }
        }
    }

    private boolean escape() {
        System.out.println("你試圖逃跑...");
        character.decreaseSan(10);  // 無論逃跑成功與否，減少 10 點 SAN
        if (!character.isAlive()) {
            return false;  // 玩家死亡，結束遊戲
        } else if (Math.random() < 0.5) {  // 50% 機率逃跑成功
            System.out.println("你成功逃跑了！");
            return true;
        } else {
            System.out.println("逃跑失敗！");
            return false;
        }
    }

    private void monsterAttack() {
        int monsterAttack = monster.getAtk();
        int damage = monsterAttack - character.getDef();
        if (damage > 0) {
            character.setHp(character.getHp() - damage);
        } else {
            damage = 0;
        }
        System.out.println(monster.getClass().getSimpleName() + " 對你造成了 " + damage + " 點傷害，剩餘 HP: " + character.getHp());
    }
}
