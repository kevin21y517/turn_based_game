package com.turn_based_game.player_settings;

import com.turn_based_game.characters.Character;
import com.turn_based_game.items.Item;
import com.turn_based_game.system.Event;
import com.turn_based_game.system.Floor;

public class Player {
    private Movement movement;
    private Inventory inventory;

    public Player(int startRow, int startCol) {
        this.movement = new Movement(startRow, startCol);
        this.inventory = new Inventory();
    }

    public int[] getPosition() {
        return movement.getPosition();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.addItem(item);  // 將道具添加到物品欄
    }

    public boolean move(String direction, Floor floor, Character character, Event event) {
        if (movement.move(direction, floor)) {
            character.decreaseSan(1);  // 移動後減少 SAN 值，若 SAN 歸零則 HP 歸零

            // 檢查角色是否已經死亡，如果死亡則提前結束，不觸發事件
            if (!character.isAlive()) {
                System.out.println(character.getName() + " 已經死亡，遊戲結束！");
                return false;  // 角色死亡，不再觸發事件
            }

            // 檢查是否到達終點
            boolean isAtEndPoint = floor.isEndPoint(movement.getPosition()[0], movement.getPosition()[1]);

            // 觸發事件，傳入是否在終點
            event.triggerEvent(this, character, isAtEndPoint);

            return isAtEndPoint;  // 返回是否到達終點
        }
        return false;
    }

}
