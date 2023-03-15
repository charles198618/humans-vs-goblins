package com.genspark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Land {

    HashMap<Integer, String[]> grid = new HashMap<>();
    ArrayList<Goblin> goblinArray = new ArrayList<>();
    ArrayList<Treasure> treasureChestArray2 = new ArrayList<>();

    public void GameBoardCreation () {
        grid.put(0, new String[]{"\n        Human vs. Goblins game!\n"});
        for(int i = 1; i < 16; i++) {
            grid.put(i, new String[]{" - ", " - ", " - ", " - ", " - ", " - ", " - ",
                    " - ", " - ", " - ", " - ", " - ", " - ", " - ", " - ","\n"});
        }
    }
    public boolean testElement(HashMap<Integer, int[]> hashToEdit, HashMap<Integer, int[]> hashToCompare) {
        int[] temp = { 111 };
        for(int i =0; i < hashToEdit.size(); i++) {
            for(int j = 0; j < hashToCompare.size(); j++) {
                if (Arrays.equals(hashToEdit.get(i), hashToCompare.get(j))) {
                    hashToEdit.put(i, temp);
                    return true;
                }
            }
        }
        return false;
    }
    public void elementRemoval (HashMap<Integer, int[]> hashToRemove, HashMap<Integer, int[]> hashToCompare) {
        for (int i = 0; i < hashToRemove.size(); i ++) {
            for(int j = 0; j < hashToCompare.size(); j ++) {
                if (Arrays.equals(hashToRemove.get(i), hashToCompare.get(j))) {
                    hashToRemove.remove(i);
                    break;
                }
            }
        }
    }
    public void addingElement (int l, HashMap<Integer, int[]> coordinates, String s) {
        int i = 0;
        while (l > 0) {
            boolean same = true;
            while(same) {
                int x = randomNum(1, 15);
                int y = randomNum(0, 15);
                if (grid.get(x)[y].equals(" - ")) {
                    grid.get(x)[y] = s;
                    int[] arr = {x, y};
                    coordinates.put(coordinates.size(), arr);
                    i++;
                    same = false;
                }
            }
            l--;
        }
    }


    public String goblinNameGenerator() {
        String[] goblinNames = {"Charles", "Mike", "Bernard", "Jacob", "Wolf", "Dog", "Cat", "Obama", "Girl", "Zaire", "German", "USA", "Haiti", "Nike", "Clark"};
        double x = Math.random() * 12;
        int y = (int) x;
        return goblinNames[y];
    }


    public Goblin goblinFightMechanics(HashMap<Integer, int[]> humanCoordinates, ArrayList<Goblin> arrList) {
        Goblin fighter = null;
        for (Goblin g : arrList) {
            if (g.getRow() == humanCoordinates.get(0)[0] && g.getColumn() == humanCoordinates.get(0)[1]) {
                fighter = g;
            }
        }
        return fighter;
    }


    public Treasure getTreasureItems(HashMap<Integer, int[]> humanCoordinates, ArrayList<Treasure> arrList) {
        Treasure treasure = null;
        for(Treasure i : arrList) {
            if(i.row == humanCoordinates.get(0)[0] && i.col == humanCoordinates.get(0)[1]) {
                treasure = i;
            }
        }
        return treasure;
    }


    public String toString(HashMap<Integer, String[]> hash) {
        StringBuilder strB2 = new StringBuilder();
        for(int i = 0; i <hash.size(); i++) {
            for(String str: hash.get(i)) {
                strB2.append(str);
            }
        }
        return strB2.toString();
    }


    public int randomNum(int i, int j) {
        double x = i + Math.random() * (j - i);
        return (int) x;
    }


    public boolean checkToSeeIfYouWon(HashMap<Integer, int[]> coordinates) {
        int[] temp = { 111 };
        for(int i = 0; i < coordinates.size(); i++) {
            if(!Arrays.equals(coordinates.get(i), temp)) {
                return false;
            }
        }
        return true;
    }
    public boolean goblinMovementMechanics() {
        double x = 1 + Math.random() * 2;
        return ((int) x == 1);
    }

}

