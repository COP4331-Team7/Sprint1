package com.team7.objects;



import com.team7.ProbabilityGenerator;
import com.team7.objects.items.Obstacle;
import com.team7.objects.items.OneShotItem;
import com.team7.objects.resource.MoneyBag;
import com.team7.objects.resource.HieroglyphicBooks;
import com.team7.objects.resource.MoonRocks;
import com.team7.objects.terrain.Desert;
import com.team7.objects.terrain.FlatLand;
import com.team7.objects.terrain.Crater;
import com.team7.objects.terrain.Mountains;

public class Map {

    private String name;
    private Tile[][] grid;

    public Map() {
        createOGMap(); //for purposes of later abstraction
    }

    //hardcoded Tiles to create our map grid
    private void createOGMap() {
        grid = new Tile[20][20];
        name = "OG Map";
        for (int i = 0; i <20; i++)
        {
            for (int j = 0; j <20; j++)
            {
                grid[i][j]=new Tile(i,j);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++){
                if (i >= 0 && i <= 4){
                    if (j >= 0 && j <= 4){
                        grid[i][j].setTerrain(new Mountains());
                        grid[19-i][19-j].setTerrain(new Mountains());
                    } else if (j > 4 && j <= 9){
                        grid[i][j].setTerrain(new Crater());
                        grid[19-i][19-j].setTerrain(new Crater());
                    } else if (j > 9 && j <= 14){
                        grid[i][j].setTerrain(new Desert());
                        grid[19-i][19-j].setTerrain(new Desert());
                    } else if (j > 14 && j <= 19){
                        grid[i][j].setTerrain(new FlatLand());
                        grid[19-i][19-j].setTerrain(new FlatLand());
                    }
                }
                else{
                    if (j >= 0 && j <= 4){
                        grid[i][j].setTerrain(new Crater());
                        grid[19-i][19-j].setTerrain(new Crater());
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

    //called in Game
    public void setMapDetails(){
        populateMap();
    }

    //iterate through each Tile on our Map
    private void populateMap() {
        for (Tile[] tileArray: grid){
            for (Tile tile: tileArray) {
                populateTile(tile);
            }
        }

        Tile[] startingTiles = {grid[17][3], grid[17][4], grid[16][4],
                grid[3][18], grid[3][17], grid[4][18]}; //the 6 starting tiles of the units

        for(Tile t : startingTiles){
            t.clearTile();      //clear all starting Tiles of resources/items
        }
    }

    private void populateTile(Tile tile) {
        //check tile terrain
        //depending on terrain type:
        //Desert has 10% AreaEffect, 5% Item, 5% Resource
        //FlatLand has 20% AreaEffect, 15% Item, 30% Resource
        //Crater has 20% AreaEffect, 5% Item, 25% Resource
        //Mountain has 0% AreaEffect, 0% Item, 0% Resource


        if(tile.getTerrain() instanceof Desert){
//            System.out.print("Checked instance of Desert");
            populateAreaEffects(tile,0.1/2); // Populate Area Effects depending on terrain type
            populateItem(tile,0.05/2); // Populate Item depending on terrain type
            populateResource(tile,0.05/2);// Populate Item depending on terrain type

        }
        else if(tile.getTerrain() instanceof FlatLand){
            populateAreaEffects(tile,0.2/2);
            populateItem(tile,0.15/2);
            populateResource(tile,0.3/2);
        }
        else if(tile.getTerrain() instanceof Crater){
            populateAreaEffects(tile,0.2/2);
            populateItem(tile,0.05/2);
            populateResource(tile, 0.25/2);
        }
        else if(tile.getTerrain() instanceof Mountains){
            populateAreaEffects(tile,0);
            populateItem(tile,0);
            populateResource(tile,0);
        }

    }


    // Populate Area Effects for each tile.
    private void populateAreaEffects(Tile tile, double prob) {
        if (ProbabilityGenerator.willOccur(prob)) {

            int rand = ProbabilityGenerator.randomInteger(0, tile.getTerrain().getAreaEffects().size()-1);
            tile.setAreaEffect(tile.getTerrain().getAreaEffects().get(rand));
        }
    }

    // Populate Item for each tile.
    private void populateItem(Tile tile, double prob) {
        int rand =0;
        if(ProbabilityGenerator.willOccur(prob)){
            rand = ProbabilityGenerator.randomInteger(0,1);
            if(rand == 0)
                tile.setItem(new OneShotItem());

            else if(rand == 1)
                tile.setItem(new Obstacle());
        }
    }

    // Populate Resource for each tile.
    private void populateResource(Tile tile, double prob) {
        if(ProbabilityGenerator.willOccur(prob)){
            int rand = ProbabilityGenerator.randomInteger(0,2);
            if(rand == 0)
                tile.setResource(new MoneyBag());
            else if(rand == 1)
                tile.setResource(new HieroglyphicBooks());
            else if(rand == 2)
                tile.setResource(new MoonRocks());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    public Tile getTile(int i, int j) {
        return grid[i][j];
    }
}
