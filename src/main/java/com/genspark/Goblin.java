package com.genspark;

public class Goblin {
    private  int health;
    private int strength;
    private int row;
    private int column;
    private String name;

    public Goblin(int health, int strength, int row, int column, String name) {
        this.health = health;
        this.strength = strength;
        this.row = row;
        this.column = column;
        this.name = name;
    }

        Goblin() {

    }

    public Goblin(int randomNum, int randomNum1, int i, int i1, String goblinNameGenerator, boolean goblinMovementMechanics) {
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   // Creating an attack method for the goblin.
    public String attackHuman(Goblin goblin, Human human) {
        double attac = 1 + Math.random() * goblin.strength;
        int strength = (int) attac;
        human.health = human.health - strength;
        return goblin.name + " attacked " + human.name + " for a hit of " + strength + " damage!";
    }


}
