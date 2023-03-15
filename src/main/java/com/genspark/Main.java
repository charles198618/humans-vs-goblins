package com.genspark;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean keepPlay = true;

        while (keepPlay) {
            Land land = new Land();
            land.GameBoardCreation();

            HashMap<Integer, int[]> humanInfo = new HashMap<>();
            land.addingElement(1,humanInfo," H ");
            Human human = new Human(10, 5, humanInfo.get(0)[0], humanInfo.get(0)[0], "Human");
            String[] weapon = {"no item"};

            Treasure treasures = new Treasure();
            HashMap<Integer, int[]> treasureLocation = new HashMap<>();
            land.addingElement(1, treasureLocation, "**e");
            for (int i = 0; i < treasureLocation.size(); i++) {
                land.treasureChestArray2.add(new Treasure(treasures.treasureChestArray(), treasureLocation.get(i)[0], treasureLocation.get(i)[1]));
            }

            HashMap<Integer, int[]> goblinLocation = new HashMap<>();
            land.addingElement(10, goblinLocation, " G ");
            for (int i = 0; i < goblinLocation.size(); i++) {
                land.goblinArray.add(new Goblin(land.randomNum(2, 8), land.randomNum(2, 6), goblinLocation.get(i)[0], goblinLocation.get(i)[1], land.goblinNameGenerator(), land.goblinMovementMechanics() ));
            }

            System.out.println("Welcome to the game!");
            System.out.println("Kill all of the goblins before you die.");
            System.out.println("Controls: type n to move up, s to move down, e to move to the right, and w to move to the left.");
            System.out.println("Press any key to continue playing.");
            sc.next();
            System.out.println(land.toString(land.grid));
            boolean playGame = true;

            while (playGame) {
                boolean alive = true;
                System.out.println("The " + human.name + " 's health is " + human.health + " and their strength is " + human.strength + " .");
                if (!human.inventorySystem.isEmpty()) {
                    System.out.println("Your inventory contains: " + human.printInventoryItem(human.inventorySystem));
                }

                System.out.println("Please make a move.");
                String input = sc.next();
                System.out.println(human.humanMovement(land.grid, humanInfo, input));

                if (land.testElement(treasureLocation, humanInfo)) {
                    Treasure treasure2 = land.getTreasureItems(humanInfo, land.treasureChestArray2);
                    String s = treasure2.itemsInTreasureChest[land.randomNum(0, 30)];
                    if(!human.checkForHealthSerum(s, treasure2, human, land, sc)) {
                        playGame = false;
                    }
                    land.elementRemoval(treasureLocation, humanInfo);
                }

                if (land.testElement(goblinLocation, humanInfo)) {
                    Goblin goblin = land.goblinFightMechanics(humanInfo, land.goblinArray);
                    System.out.println("human's health is " + human.health + " " + goblin.getName() + " has " + goblin.getHealth() + " health.");
                    System.out.println();


                    if (!human.inventorySystem.isEmpty()) {
                        System.out.println("Your inventory contains: " + human.printInventoryItem(human.inventorySystem));
                        System.out.println("Do you want to use any item? (y/n).");
                        String result = sc.next();


                        if(result.equalsIgnoreCase("y")) {
                            while(weapon[0].equals("no item")) {
                                System.out.println("What item you want to use?");
                                System.out.println("Your inventory contains: " + human.printInventoryItem(human.inventorySystem));
                                String item = sc.next();
                                System.out.println(human.useTheItem(item, human, goblin, weapon));
                            }
                            System.out.println("Press any button to continue.");
                            sc.next();
                        }
                    }
                    while (alive) {
                        System.out.println(human.attackGoblin(human, goblin));
                        if (goblin.getHealth() > 0) {
                            System.out.println("human's health is " + human.health + " . " + goblin.getName() + " 's health is " + goblin.getHealth() + " .");
                            System.out.println("Press any button to continue.");
                            sc.next();
                        }else {
                            System.out.println("You killed the goblin " + goblin.getName() + " .");
                            if(land.checkToSeeIfYouWon(goblinLocation)) {
                                System.out.println("You won! ");
                                playGame = false;
                                break;
                            }
                            if (!weapon[0].equals("no item")) {
                                human.removeInventoryItem(human, weapon);
                            }
                            System.out.println("Human's health is " + human.health + " and their strength is " + human.strength + " .");
                            land.elementRemoval(goblinLocation, humanInfo);
                            land.grid.get(human.row)[human.col] = " H ";


                            int t = treasureLocation.size();
                            land.addingElement(1, treasureLocation, "**");
                            land.treasureChestArray2.add(new Treasure(treasures.treasureChestArray(), treasureLocation.get(t)[0], treasureLocation.get(t)[1]));
                            System.out.println("Press any button to continue");
                            sc.next();
                            System.out.println(land.toString(land.grid));
                            break;
                        }
                        System.out.println(goblin.attackHuman(goblin,human));
                        if (human.health > 0) {
                            System.out.println("Human's health is " + human.health + " and the " + goblin.getName() + " 's health is " + goblin.getHealth() + " .");
                            System.out.println("Press any button to continue.");
                            sc.next();
                        }else {
                            System.out.println("Sorry, you lost the game.");
                            alive = false;
                            playGame = false;
                        }
                    }

                }
            }
            System.out.println("Do you want to again?");
            String finaleResult = sc.next();
            if(!finaleResult.equals("y")) {
                System.out.println("Alright, thank you!");
                keepPlay= false;
            }

        }

    }
}
