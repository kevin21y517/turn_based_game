package com.turn_based_game.characters;

import java.util.ArrayList;
import java.util.List;

import com.turn_based_game.skills.Skill;

public class Warrior implements Character {
    private String name;
    private int hp, maxHp;
    private int mp, maxMp;
    private int atk;
    private int def;
    private int san, maxSan;
    private List<Skill> skills;  // 使用 List 來儲存多個技能

    public Warrior(String name, int maxHp, int maxMp, int atk, int def, int maxSan) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.mp = maxMp;
        this.maxMp = maxMp;
        this.atk = atk;
        this.def = def;
        this.san = maxSan;
        this.skills = new ArrayList<>();  // 初始化技能列表
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getHp() { return hp; }

    @Override
    public int getMaxHp() { return maxHp; }

    @Override
    public int getMp() { return mp; }

    @Override
    public int getMaxMp() { return maxMp; }

    @Override
    public int getAtk() { return atk; }

    @Override
    public int getDef() { return def; }

    @Override
    public int getSan() { return san; }

    @Override
    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;  // 確保 HP 不會低於 0
        } else {
            this.hp = Math.min(hp, maxHp);  // 確保 HP 不超過最大 HP
        }
    }

    @Override
    public void setMp(int mp) {
        this.mp = Math.min(mp, maxMp);  // 確保 MP 不超過最大 MP
    }

    @Override
    public void setAtk(int atk) { this.atk = atk; }

    @Override
    public void setDef(int def) { this.def = def; }

    @Override
    public void setSan(int san) {
        this.san = san;
    }

    @Override
    public void addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
            System.out.println("你學會了新技能: " + skill.getName());
        } else {
            System.out.println("你已經擁有該技能。");
        }
    }

    @Override
    public List<Skill> getSkills() {
        return skills;
    }

    @Override
    public void decreaseSan(int value) {
        int newSan = getSan() - value;
        if (newSan < 0) {
            newSan = 0;  // 確保 SAN 不會低於 0
        }
        setSan(newSan);
        System.out.println(getName() + " 的 SAN 值減少了 " + value + "，現在的 SAN 值為: " + getSan());

        // 如果 SAN 歸零，則角色死亡，HP 也歸零
        if (getSan() == 0) {
            setHp(0);  // 當 SAN 歸零時，HP 設置為 0
            System.out.println(getName() + " 的 SAN 值已歸零，生命值 (HP) 歸零！");
        }
    }

    @Override
    public boolean isAlive() {
        return hp > 0;  // HP > 0 表示角色還存活
    }

    @Override
    public void printStatus() {
        Character.super.printStatus();  // 調用接口中的默認方法打印狀態
    }
}
