package com.turn_based_game.monsters;

// 定義 Monster 介面
public interface Monster {
    // 怪物的攻擊行為，返回攻擊力數值
    int getAtk();

    // 怪物受到傷害後的處理，傳入受到的傷害值
    void takeDamage(int damage);

    // 判斷怪物是否還存活，返回是否還活著
    boolean isAlive();

    // 獲取怪物的防禦力，用於減免玩家攻擊傷害
    int getDef();

    // 獲取怪物當前的 HP，用於顯示狀態
    int getHp();

    // 獲取怪物的名字，用於顯示或輸出
    String getName();
}
