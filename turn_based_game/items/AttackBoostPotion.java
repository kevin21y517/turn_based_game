package com.turn_based_game.items;

import com.turn_based_game.characters.Character;

public class AttackBoostPotion implements Item {
    private String name;
    private int boostAmount;

    public AttackBoostPotion(String name, int boostAmount) {
        this.name = name;
        this.boostAmount = boostAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEffectType() {
        return ("增加" + boostAmount + "點攻擊力");
    }

    @Override
    public void use(Character character) {
        character.setAtk(character.getAtk() + boostAmount);
        System.out.println(character.getName() + " 使用了 " + getName() + "，增加了 " + boostAmount + " ATK！");
    }

    // 判斷是否為同一類型的道具（例如同樣的名稱和效果）
    @Override
    public boolean isSameItem(Item other) {
        if (other instanceof AttackBoostPotion) {
            AttackBoostPotion otherPotion = (AttackBoostPotion) other;
            return this.name.equals(otherPotion.getName()) && this.boostAmount == otherPotion.boostAmount;
        }
        return false;
    }
}
