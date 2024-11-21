package com.turn_based_game.system;

public interface Observer {
    void update(int levelNumber);  // 接收樓層編號，並根據樓層調整怪物屬性
}
