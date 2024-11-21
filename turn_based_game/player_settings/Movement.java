package com.turn_based_game.player_settings;

import com.turn_based_game.system.Floor;

public class Movement {
    private int[] position;

    public Movement(int startRow, int startCol) {
        this.position = new int[] {startRow, startCol};
    }

    public int[] getPosition() {
        return position;
    }

    public boolean move(String direction, Floor floor) {
        int newRow = position[0];
        int newCol = position[1];

        switch (direction.toLowerCase()) {
            case "w":
                newRow -= 1;
                break;
            case "s":
                newRow += 1;
                break;
            case "a":
                newCol -= 1;
                break;
            case "d":
                newCol += 1;
                break;
            default:
                System.out.println("無效指令！");
                return false;
        }

        if (newRow < 0 || newRow >= floor.getRows() || newCol < 0 || newCol >= floor.getCols()) {
            System.out.println("無法移動到該位置！");
            return false;
        }

        position[0] = newRow;
        position[1] = newCol;
        return true;
    }
}
