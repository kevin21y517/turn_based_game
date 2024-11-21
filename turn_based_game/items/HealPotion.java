package com.turn_based_game.items;

import com.turn_based_game.characters.Character;

public class HealPotion implements Item {
    private String name;
    private int healAmount;

    public HealPotion(String name, int healAmount) {
        this.name = name;
        this.healAmount = healAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEffectType() {
        return ("恢復" + healAmount + "點生命值");
    }

    @Override
    public void use(Character character) {
        character.setHp(character.getHp() + healAmount);
        System.out.println(character.getName() + " 使用了 " + getName() + "，恢復了 " + healAmount + " HP！");
    }

    // 判斷是否為同一類型的道具（例如同樣的名稱和效果）
    @Override
    public boolean isSameItem(Item other) {
        if (other instanceof HealPotion) {
            HealPotion otherPotion = (HealPotion) other;
            return this.name.equals(otherPotion.getName()) && this.healAmount == otherPotion.healAmount;
        }
        return false;
    }
}
