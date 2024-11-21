package com.turn_based_game.system;

import java.util.Random;

public class Floor {
    private int levelNumber;
    private int rows, cols;
    private int[] startPosition;
    private int[] endPosition;
    private Random random = new Random();

    public Floor(int levelNumber, int rows, int cols) {
        this.levelNumber = levelNumber;
        this.rows = rows + levelNumber;  // 每層地圖會隨著樓層數增加而變大
        this.cols = cols + levelNumber;  // 每層地圖會隨著樓層數增加而變大
        generateMap();  // 隨機生成起點和終點
    }

    // 隨機生成起點和終點，確保它們不太接近
    private void generateMap() {
        startPosition = generateRandomPosition();  // 隨機生成起點
        do {
            endPosition = generateRandomPosition();  // 隨機生成終點
        } while (!isValidDistance(startPosition, endPosition));  // 檢查距離
    }

    // 隨機生成地圖上的位置
    private int[] generateRandomPosition() {
        int row = random.nextInt(rows);
        int col = random.nextInt(cols);
        return new int[] { row, col };
    }

    // 檢查起點與終點是否有足夠的距離
    private boolean isValidDistance(int[] start, int[] end) {
        int distance = Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
        return distance >= Math.min(rows, cols) / 2;  // 確保有足夠距離
    }

    // 獲取起點位置
    public int[] getStartPosition() {
        return startPosition;
    }

    // 檢查是否為終點位置
    public boolean isEndPoint(int row, int col) {
        return row == endPosition[0] && col == endPosition[1];
    }

    // 輸出地圖，並顯示玩家當前位置和終點
    public void displayMap(int[] playerPosition) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == playerPosition[0] && j == playerPosition[1]) {
                    System.out.print("P ");  // 用 P 表示玩家當前位置
                } else if (i == endPosition[0] && j == endPosition[1]) {
                    System.out.print("X ");  // 用 X 表示終點
                } else {
                    System.out.print(". ");  // 其他地圖部分
                }
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
