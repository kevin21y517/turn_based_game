package com.turn_based_game.characters;

public class CharacterFactory {
    public static Character createCharacter(int action) {
        if (action == 1) {
            return new Warrior("戰士", 1000, 10, 100, 0, 100);
        } else {
            return new Mage("法師", 80, 50, 10, 10, 50);
        }
    }
}
