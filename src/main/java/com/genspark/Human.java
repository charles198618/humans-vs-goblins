package com.genspark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Human{
    int health;
    int strength;
    int row;
    int col;
    String name;
    ArrayList<String> inventorySystem = new ArrayList<>();
    Human(ArrayList<String> inventorySystem) {
        this.inventorySystem = inventorySystem;
    }
    Human() {


    }
    Human(int health, int strength, int row, int col, String name) {
        this.health = health;
        this.strength = strength;
        this.row = row;
        this.col = col;
        this.name = name;
    }


    public boolean checkForHealthSerum(String str, Treasure treasure, Human human, Land land, Scanner scanner) {
        if(str.equals(treasure.healthSerum)) {
            System.out.println("You have found the health serum, press any button to increase your health by 10.");
            scanner.next();
            human.health = human.health + 10;
            land.grid.get(human.row)[human.col] = " H ";
        }else if(str.equals(treasure.poison)) {
            System.out.println("You unfortunately have found poison, your health will decrease by 5. Press any button to continue.");
            scanner.next();
            human.health = human.health - 5;
            land.grid.get(human.row)[human.col] = " H ";
            if (human.health < 1) {
                System.out.println("Your health was too low when you found the poison. The poison killed you. You have lost.");
                return false;
            }
        } else {
            human.inventorySystem.add(str.toLowerCase());
            System.out.println("The treasure chest you opened had : " + str + " . The will be added to your inventory.");
            land.grid.get(human.row)[human.col] = " H ";
        }
        return true;
    }
    public String useTheItem(String str, Human human, Goblin goblin, String[] arr) {
        for(String item : human.inventorySystem) {
            if (item.contains(str)) {
                switch (str) {
                    case "bow and arrow" -> {
                        human.strength = human.strength + 2;
                        human.inventorySystem.remove("bow and arrow");
                        arr[0] = "bow and arrow";
                        return "The bow and arrow will increase your strength by 2.";


                    }
                    case "sword" -> {
                        human.strength = human.strength + 3;
                        human.inventorySystem.remove("sword");
                        arr[0] = "sword";
                        return "The sword will increase your strength by 3";
                    }
                    case "mace" -> {
                        human.strength = human.strength + 5;
                        human.inventorySystem.remove("mace");
                        arr[0] = "mace";
                        return "The mace will increase your strength by 5.";
                    }
                    case "axe" -> {
                        human.strength = human.strength + 7;
                        human.inventorySystem.remove("axe");
                        arr[0] = "axe";
                        return "The axe will increase your strength by 7.";
                    }
                    case "armor" -> {
                        goblin.setStrength(1);
                        human.inventorySystem.remove("armor");
                        arr[0] = "armor";
                        return "The armor you have decreased the goblin's strength to 1.";
                    }
                }
            }
        }
        return "Your inventory does not contain this item.";
    }
    public void removeInventoryItem(Human human, String[] arr) {
        if(arr[0].equals("bow and arrow")) human.strength = human.strength - 2;
        if(arr[0].equals("sword")) human.strength = human.strength - 3;
        if(arr[0].equals("mace")) human.strength = human.strength - 5;
        if(arr[0].equals("axe")) human.strength = human.strength - 7;
        arr[0] = "no item";
    }
    public String printInventoryItem (ArrayList<String> inventorySystem) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : inventorySystem) {
            stringBuilder.append(": ");
            stringBuilder.append(s);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }


    public String attackGoblin(Human human, Goblin goblin) {
        double x = 1 + Math.random() * human.strength;
        int strengthPower = (int) x;
        goblin.setHealth(goblin.getHealth() - strengthPower);
        return human.name + "has attacked the " + goblin.getName() + " for a damage of " + strengthPower + " .";
    }


    public String humanMovement(HashMap<Integer, String[]> grid, HashMap<Integer, int[]> hash, String str){
        switch (str.toLowerCase()) {
            //To move up:
            case "n":
                if (this.row - 1 >= 1) {
                    grid.get(this.row)[this.col] = " - ";
                    this.row = this.row - 1;
                    hash.get(0)[0] = this.row;
                    if(grid.get(this.row)[this.col].equals(" G ")){
                        grid.get(this.row)[this.col] = "G:H";
                    } else if(grid.get(this.row)[this.col].equals(" - ")){
                        grid.get(this.row)[this.col] = " H ";
                    }else {
                        grid.get(this.row)[this.col] = "[H]";
                    }
                }
                break;


            //To move down:
            case "s":
                if (this.row + 1 < 16) {
                    grid.get(this.row)[this.col] = " - ";
                    this.row = this.row + 1;
                    hash.get(0)[0] = this.row;
                    if(grid.get(this.row)[this.col].equals(" G ")){
                        grid.get(this.row)[this.col] = "G:H";
                    } else if(grid.get(this.row)[this.col].equals(" - ")){
                        grid.get(this.row)[this.col] = " H ";
                    }else {
                        grid.get(this.row)[this.col] = "[H]";
                    }
                }
                break;


            //To move to the right:
            case "e" :
                if (this.col + 1 < 15) {
                    grid.get(this.row)[this.col] = " - ";
                    this.col = this.col + 1;
                    hash.get(0)[1] = this.col;
                    if(grid.get(this.row)[this.col].equals(" G ")){
                        grid.get(this.row)[this.col] = "G:H";
                    } else if(grid.get(this.row)[this.col].equals(" - ")){
                        grid.get(this.row)[this.col] = " H ";
                    }else {
                        grid.get(this.row)[this.col] = "[H]";
                    }
                }
                break;


            //to move to the left:
            case "w" :
                if (this.col - 1 >= 0) {
                    grid.get(this.row)[this.col] = " - ";
                    this.col = this.col - 1;
                    hash.get(0)[1] = this.col;
                    if(grid.get(this.row)[this.col].equals(" G ")){
                        grid.get(this.row)[this.col] = "G:H";
                    } else if(grid.get(this.row)[this.col].equals(" - ")){
                        grid.get(this.row)[this.col] = " H ";
                    }else {
                        grid.get(this.row)[this.col] = "[H]";
                    }
                }
                break;
            default:
                return toString(grid);




        }
        return toString(grid);
    }


    public String toString(HashMap<Integer, String[]> hash) {
        StringBuilder stringBuilder2 = new StringBuilder();
        for(int i = 0; i<hash.size(); i++) {
            for (String s : hash.get(i)) {
                stringBuilder2.append(s);
            }
        }
        return stringBuilder2.toString();
    }
}
