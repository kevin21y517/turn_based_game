package com.turn_based_game;

import com.turn_based_game.characters.CharacterFactory;
import com.turn_based_game.monsters.MonsterFactory;
import com.turn_based_game.player_settings.UI;

public class Main {
    public static void main(String[] args) {
        // 創建必須的實例
        UI ui = new UI();
        CharacterFactory characterFactory = new CharacterFactory();
        MonsterFactory monsterFactory = new MonsterFactory();

        // 傳遞實例到 Game 的構造函數
        Game game = new Game(ui, characterFactory, monsterFactory);
        game.start();
    }
}



// import java.util.Scanner;

// import com.turn_based_game.characters.Mage;
// import com.turn_based_game.characters.Warrior;


// public class Main {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("請選擇角色: 1. 戰士 2. 法師");
//         int action = getPlayerAction(scanner);

//         Character character;

//         if (action == 1) {
//             character = new Warrior("戰士", 1000, 10, 100, 0, 100);  // 初始化為戰士，SAN=100
//         } else {
//             character = new Mage("法師", 80, 50, 10, 10, 50);  // 初始化為法師，SAN=50
//         }

//         System.out.println("你選擇的角色是: " + character.getName());

//         Level level = new Level();  // 生成新的關卡
//         MonsterFactory.setLevel(level);
//         Event event = new Event();  // 創建事件對象
//         boolean reachedEnd = false;
//         boolean gameOver = false;  // 標記遊戲是否結束

//         while (!reachedEnd && !gameOver) {  // 遊戲主循環，直到到達終點或遊戲結束
//             System.out.println("你正在第 " + level.getLevelNumber() + " 層迷宮");
//             Floor floor = level.generateFloor(4, 4);  // 生成當前層的迷宮
//             int[] startPosition = floor.getStartPosition();
//             Player player = new Player(startPosition[0], startPosition[1]);  // 初始化玩家位置

//             Monster boss = MonsterFactory.createBoss();
//             System.out.println("Boss 的屬性: HP: " + boss.getHp() + ", ATK: " + boss.getAtk() + ", DEF: " + boss.getDef());

//             // 玩家移動與事件觸發循環
//             while (!gameOver) {
//                 floor.displayMap(player.getPosition());  // 顯示當前地圖

//                 System.out.println("請輸入移動方向 (w:上, s:下, a:左, d:右), 'inventory' 查看物品欄, 或輸入 'status' 來查看角色狀態: ");
//                 String input = scanner.nextLine();

//                 if (input.equals("status")) {
//                     character.printStatus();  // 查看角色狀態
//                 } else if (input.equals("inventory")) {
//                     player.getInventory().showInventory();  // 顯示物品欄
//                     System.out.println("你想使用哪個道具？輸入道具的編號，或輸入 '0' 取消:");
//                     int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;
//                     if (itemIndex >= 0) {
//                         player.getInventory().useItem(itemIndex, character);  // 使用指定的道具
//                     } else {
//                         System.out.println("取消使用道具。");
//                     }
//                 } else if (player.move(input, floor, character, event)) {  // 玩家移動並觸發事件
//                     reachedEnd = true;  // 如果玩家到達終點，結束當前循環
//                     break;
//                 }

//                 // 檢查遊戲狀態，如果玩家死亡，結束遊戲
//                 if (!character.isAlive()) {
//                     gameOver = true;  // 遊戲結束
//                     System.out.println("遊戲結束，角色死亡。");
//                     break;
//                 }
//             }

//             if (reachedEnd && character.isAlive()) {
//                 if (level.getLevelNumber() == 10) {
//                     System.out.println("恭喜你，成功通過第10層，通關遊戲！");
//                     break;  // 遊戲通關，結束
//                 }

//                 System.out.println("進入下一層...");
//                 level.nextLevel();  // 進入下一層

//                 // 增加 SAN，並確保不超過最大值
//                 character.setSan(character.getSan() + 20);
//                 System.out.println("你的 SAN 值增加了 20 點，當前 SAN 值: " + character.getSan());

//                 reachedEnd = false;  // 重置狀態，繼續下一層迷宮的探索
//             }
//         }
//     }

//     // 獲取玩家的角色選擇
//     public static int getPlayerAction(Scanner scanner) {
//         while (true) {
//             try {
//                 int action = Integer.parseInt(scanner.nextLine());
//                 if (action == 1 || action == 2) {
//                     return action;
//                 } else {
//                     System.out.println("請輸入 1 或 2 選擇角色。");
//                 }
//             } catch (NumberFormatException e) {
//                 System.out.println("無效輸入，請輸入數字 1 或 2。");
//             }
//         }
//     }
// }
