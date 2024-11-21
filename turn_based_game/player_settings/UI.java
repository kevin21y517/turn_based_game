package com.turn_based_game.player_settings;

import java.util.Scanner;

import com.turn_based_game.characters.Character;
import com.turn_based_game.system.Event;
import com.turn_based_game.system.Floor;


public class UI {
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int getPlayerAction() {
        while (true) {
            try {
                int action = Integer.parseInt(scanner.nextLine());
                if (action == 1 || action == 2) {
                    return action;
                } else {
                    displayMessage("請輸入 1 或 2 選擇角色。");
                }
            } catch (NumberFormatException e) {
                displayMessage("無效輸入，請輸入數字 1 或 2。");
            }
        }
    }

    public String getPlayerInput() {
        System.out.println("請輸入移動方向 (w:上, s:下, a:左, d:右), 'inventory' 查看物品欄, 或輸入 'status' 來查看角色狀態: ");
        return scanner.nextLine();
    }

    public void showStatus(Character character) {
        character.printStatus();
    }

    public void showInventory(Inventory inventory, Character character) {
        inventory.showInventory();
        System.out.println("你想使用哪個道具？輸入道具的編號，或輸入 '0' 取消:");
        try {
            int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (itemIndex >= 0) {
                inventory.useItem(itemIndex, character);
            } else {
                displayMessage("取消使用道具。");
            }
        } catch (NumberFormatException e) {
            displayMessage("無效的輸入，取消使用道具。");
        }
    }

    public boolean handleInput(String input, Player player, Character character, Floor floor) {
        Event event = new Event();  // 創建一個有效的事件對象
        switch (input) {
            case "status":
                showStatus(character);
                return false;  // 狀態顯示不影響遊戲進程
            case "inventory":
                showInventory(player.getInventory(), character);
                return false;  // 使用物品後繼續遊戲
            default:
                return player.move(input, floor, character, event);  // 玩家移動
        }
    }
}