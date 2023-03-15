package com.genspark;

public class Treasure {
    String bowAndArrow = "bow and arrow";
    String sword = "sword";
    String mace = "mace";
    String axe = "axe";
    String armor = "armor";
    String healthSerum = "health serum";
    String poison = "poison";
    String[] itemsInTreasureChest = new String[30];
    int row;
    int col;


    Treasure() {}


    Treasure(String[] itemsInTreasureChest, int row, int col) {
        this.itemsInTreasureChest = itemsInTreasureChest;
        this.row = row;
        this.col = col;


    }
    public String[] treasureChestArray() {
        for(int i = 0; i < 10; i++) {
            itemsInTreasureChest[i] = bowAndArrow;
        }
        for(int i = 10; i < 20; i++) {
            itemsInTreasureChest[i] = sword;
        }
        for(int i = 20; i < 23; i++) {
            itemsInTreasureChest[i] = mace;
        }
        for(int i = 23; i < 26; i++){
            itemsInTreasureChest[i] = axe;


        }
        for(int i = 26; i < 28; i++) {
            itemsInTreasureChest[i] = healthSerum;
        }
        for(int i = 28; i< 29; i++) {
            itemsInTreasureChest[i] = poison;
        }
        for(int i = 29; i<30;i++) {
            itemsInTreasureChest[i] = armor;
        }
        return itemsInTreasureChest;


    }


}
