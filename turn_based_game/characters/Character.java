package com.turn_based_game.characters;

import java.util.List;

import com.turn_based_game.skills.Skill;

public interface Character {
    String getName();
    int getHp();
    int getMaxHp();
    int getMp();
    int getMaxMp();
    int getAtk();
    int getDef();
    int getSan(); // 理智值，不影響行為

    void setHp(int hp);
    void setMp(int mp);
    void setAtk(int atk);
    void setDef(int def);
    void setSan(int san);

    void addSkill(Skill skill);  // 添加技能
    List<Skill> getSkills();     // 獲取技能列表
    void decreaseSan(int value);  // 減少SAN值的方法

    // 判斷是否還存活，返回是否還活著
    boolean isAlive();

    default void printStatus() {
        System.out.println("角色狀態:");
        System.out.println("名稱: " + getName());
        System.out.println("生命值 (HP): " + getHp());
        System.out.println("魔力值 (MP): " + getMp());
        System.out.println("攻擊力 (ATK): " + getAtk());
        System.out.println("防禦力 (DEF): " + getDef());
        System.out.println("理智值 (SAN): " + getSan());
        System.out.println("技能列表: ");
        if (getSkills().isEmpty()) {
            System.out.println("你目前沒有任何技能。");
        } else {
            for (Skill skill : getSkills()) {
                System.out.println("- " + skill.getName());
            }
        }
    }
}
