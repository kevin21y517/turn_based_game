package com.turn_based_game;

import com.turn_based_game.characters.Character;
import com.turn_based_game.characters.CharacterFactory;
import com.turn_based_game.monsters.Monster;
import com.turn_based_game.monsters.MonsterFactory;
import com.turn_based_game.player_settings.Player;
import com.turn_based_game.player_settings.UI;
import com.turn_based_game.system.Floor;
import com.turn_based_game.system.Level;

public class Game {
    private Character character;
    private Level level;
    private UI ui;
    private GameState gameState;
    private CharacterFactory characterFactory;
    private MonsterFactory monsterFactory;

    public Game(UI ui, CharacterFactory characterFactory, MonsterFactory monsterFactory) {
        this.ui = ui;
        this.characterFactory = characterFactory;
        this.monsterFactory = monsterFactory;
        this.gameState = GameState.getInstance();
    }

    public void start() {
        ui.displayMessage("請選擇角色: 1. 戰士 2. 法師");
        int action = ui.getPlayerAction();

        this.character = characterFactory.createCharacter(action);
        ui.displayMessage("你選擇的角色是: " + character.getName());

        this.level = new Level();
        monsterFactory.setLevel(level);

        gameLoop();
    }

    private void gameLoop() {
        while (!gameState.isGameOver() && !gameState.hasReachedEnd()) {
            ui.displayMessage("你正在第 " + level.getLevelNumber() + " 層迷宮");
            Floor floor = level.generateFloor(4, 4);
            int[] startPosition = floor.getStartPosition();
            Player player = new Player(startPosition[0], startPosition[1]);

            Monster boss = monsterFactory.createBoss();
            ui.displayMessage("Boss 的屬性: HP: " + boss.getHp() + ", ATK: " + boss.getAtk() + ", DEF: " + boss.getDef());

            while (!gameState.isGameOver()) {
                floor.displayMap(player.getPosition());
                String input = ui.getPlayerInput();

                if (ui.handleInput(input, player, character, floor)) {
                    gameState.setReachedEnd(true);
                    break;
                }

                if (!character.isAlive()) {
                    gameState.setGameOver(true);
                    ui.displayMessage("遊戲結束，角色死亡。");
                    break;
                }
            }

            if (gameState.hasReachedEnd() && character.isAlive()) {
                if (level.getLevelNumber() == 10) {
                    ui.displayMessage("恭喜你，成功通過第10層，通關遊戲！");
                    break;
                }

                ui.displayMessage("進入下一層...");
                level.nextLevel();
                character.setSan(character.getSan() + 20);
                ui.displayMessage("你的 SAN 值增加了 20 點，當前 SAN 值: " + character.getSan());
                gameState.setReachedEnd(false);
            }
        }
    }
}
