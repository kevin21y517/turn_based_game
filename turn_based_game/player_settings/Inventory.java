package com.turn_based_game.player_settings;

import java.util.ArrayList;
import java.util.List;

import com.turn_based_game.characters.Character;
import com.turn_based_game.items.Item;

public class Inventory {
    private List<StackedItem> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    // 內部類，表示堆疊道具
    private class StackedItem {
        Item item;
        int quantity;

        public StackedItem(Item item) {
            this.item = item;
            this.quantity = 1;
        }

        public void increaseQuantity() {
            quantity++;
        }

        public void decreaseQuantity() {
            quantity--;
        }

        public int getQuantity() {
            return quantity;
        }

        public Item getItem() {
            return item;
        }
    }

    // 添加道具到物品欄，如果已存在相同道具，則堆疊
    public void addItem(Item newItem) {
        for (StackedItem stackedItem : items) {
            if (stackedItem.getItem().isSameItem(newItem)) {
                stackedItem.increaseQuantity();  // 如果是相同道具，增加數量
                System.out.println("你獲得了 " + newItem.getName() + "，數量增加為: " + stackedItem.getQuantity());
                return;
            }
        }
        // 如果是新的道具，直接添加
        items.add(new StackedItem(newItem));
        System.out.println("你獲得了道具: " + newItem.getName());
    }

    // 顯示物品欄，顯示每種道具的數量
    public void showInventory() {
        if (items.isEmpty()) {
            System.out.println("你的物品欄是空的。");
        } else {
            System.out.println("你當前的物品欄包含以下道具:");
            for (int i = 0; i < items.size(); i++) {
                StackedItem stackedItem = items.get(i);
                Item item = stackedItem.getItem();
                System.out.println((i + 1) + ". " + item.getName() + " (效果: " + item.getEffectType() +
                                   ") - 數量: " + stackedItem.getQuantity());
            }
        }
    }

    // 使用物品欄中的道具
    public void useItem(int index, Character character) {
        if (index >= 0 && index < items.size()) {
            StackedItem stackedItem = items.get(index);
            Item item = stackedItem.getItem();
            item.use(character);  // 使用道具效果
            stackedItem.decreaseQuantity();  // 使用後數量減少

            // 如果數量為 0，移除該道具
            if (stackedItem.getQuantity() == 0) {
                items.remove(index);
            }
        } else {
            System.out.println("無效的道具索引。");
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
