package com.turn_based_game.monsters;

public class Goblin implements Monster {
    private int hp;
    private int atk;
    private int def;

    public Goblin(int hp, int atk, int def) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }

    @Override
    public int getAtk() {
        return atk;  // 怪物攻擊力
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void takeDamage(int damage) {
        int actualDamage = damage - def;
        if (actualDamage > 0) {
            hp -= actualDamage;
        } else {
            actualDamage = 0;
        }
        if (hp < 0) {
            hp = 0;  // 血量不會低於0
        }
        System.out.println(getName() + " 受到 " + actualDamage + " 點傷害，剩餘 HP: " + hp);
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public int getDef() {
        return def;
    }

    @Override
    public String getName() {
        return "Goblin";
    }
}
