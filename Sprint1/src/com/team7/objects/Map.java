package com.team7.objects;


import com.team7.objects.terrain.Desert;
import com.team7.objects.terrain.FlatLand;
import com.team7.objects.terrain.Hills;
import com.team7.objects.terrain.Mountains;

public class Map {
    private long id;
    private String name;
    private Tile[][] grid;

    public Map() {
        createOGMap(); //for purposes of later abstraction
    }

    //hardcoded Tiles to create our map grid
    private void createOGMap() {
        grid = new Tile[20][20];
        name = "OG Map";

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++){
                if (i >= 0 && i <= 4){
                    if (j >= 0 && j <= 4){
                        grid[i][j].setTerrain(new Mountains());
                        grid[19-i][19-j].setTerrain(new Mountains());
                    } else if (j > 4 && j <= 9){
                        grid[i][j].setTerrain(new Hills());
                        grid[19-i][19-j].setTerrain(new Hills());
                    } else if (j > 9 && j <= 14){
                        grid[i][j].setTerrain(new Desert());
                        grid[19-i][19-j].setTerrain(new Desert());
                    } else if (j > 14 && j <= 19){
                        grid[i][j].setTerrain(new FlatLand());
                        grid[19-i][19-j].setTerrain(new FlatLand());
                    }
                } else{
                    if (j >= 0 && j <= 4){
                        grid[i][j].setTerrain(new Hills());
                        grid[19-i][19-j].setTerrain(new Hills());
                    } else if (j > 4 && j <= 9){
                        grid[i][j].setTerrain(new FlatLand());
                        grid[19-i][19-j].setTerrain(new FlatLand());
                    } else if (j > 9 && j <= 14){
                        grid[i][j].setTerrain(new Desert());
                        grid[19-i][19-j].setTerrain(new Desert());
                    } else if (j > 14 && j <= 19){
                        grid[i][j].setTerrain(new FlatLand());
                        grid[19-i][19-j].setTerrain(new FlatLand());
                    }
                }
            }
        }
    }


}
