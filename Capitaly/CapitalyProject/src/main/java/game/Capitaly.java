/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;

public class Capitaly {
    static List<Field> fields = new ArrayList<>();
    static List<Player> players = new ArrayList<>();
    static List<Integer> diceRolls = new ArrayList<>();
    static String inputResultF;


    public static void main(String[] args) {
        try {
            read("input1.txt");
            play();
        } catch (InvalidInputException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void read(String filename) throws InvalidInputException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            int fieldCount = Integer.parseInt(validateNonNegative(br.readLine().trim(), "Field count"));

            for (int i = 0; i < fieldCount; i++) {
                String[] fieldData = br.readLine().split(" ");
                if (fieldData.length < 1) throw new InvalidInputException("Invalid field data at line " + (i + 2));

                String fieldType = fieldData[0];

                switch (fieldType) {
                    case "Property":
                        fields.add(new Property(FieldType.Property));
                        break;
                    case "Service":
                        if (fieldData.length < 2) throw new InvalidInputException("Service field requires cost.");
                        int serviceCost = Integer.parseInt(validateNonNegative(fieldData[1], "Service cost"));
                        fields.add(new ServiceField(FieldType.Service, serviceCost));
                        break;
                    case "Lucky":
                        if (fieldData.length < 2) throw new InvalidInputException("Lucky field requires reward.");
                        int luckyReward = Integer.parseInt(validateNonNegative(fieldData[1], "Lucky reward"));
                        fields.add(new LuckyField(FieldType.Lucky, luckyReward));
                        break;
                    default:
                        throw new InvalidInputException("Invalid field type: " + fieldType);
                }
            }

            int playerCount = Integer.parseInt(validateNonNegative(br.readLine().trim(), "Player count"));

            for (int i = 0; i < playerCount; i++) {
                String[] playerData = br.readLine().split(" ");
                if (playerData.length < 2) throw new InvalidInputException("Invalid player data at line " + (i + fieldCount + 3));

                String playerName = playerData[0];
                String strategy = playerData[1];

                switch (strategy) {
                    case "Greedy":
                        players.add(new GreedyPlayer(playerName));
                        break;
                    case "Careful":
                        players.add(new CarefulPlayer(playerName));
                        break;
                    case "Tactical":
                        players.add(new TacticalPlayer(playerName));
                        break;
                    default:
                        throw new InvalidInputException("Invalid strategy: " + strategy);
                }
            }
            int diceCount = Integer.parseInt(validateNonNegative(br.readLine().trim(), "Dice count"));

            String[] diceRollsData = br.readLine().split(" ");
            for (int i = 0; i < diceCount; i++) {
                try {   
                    if (Integer.parseInt(diceRollsData[i]) > 6 || Integer.parseInt(diceRollsData[i]) < 0) throw new InvalidInputException("Dice roll should be in range (1,6)");
                    int diceRoll = Integer.parseInt(validateNonNegative(diceRollsData[i], "Dice roll"));
                    diceRolls.add(diceRoll);
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid dice roll at index " + i);
                }
            }
            inputResultF = br.readLine();


            br.close();

        } catch (FileNotFoundException e) {
            throw new InvalidInputException("File not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Number format error: " + e.getMessage());
        } catch (IOException e) {
            throw new InvalidInputException("Error reading the file: " + e.getMessage());
        }
    }
    private static String validateNonNegative(String input, String fieldName) throws InvalidInputException {
        try {
            int value = Integer.parseInt(input);
            if (value < 0) {
                throw new InvalidInputException(fieldName + " must be a non-negative integer.");
            }
            return input;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " is not a valid number.");
        }
    }

    public static void play() {
        int i = 0;
        int rollIndex = 0;

        while (players.size() > 1 && rollIndex < diceRolls.size()) {
            if (i == players.size()) {
                i -= players.size();
            }

            players.get(i).position = (diceRolls.get(rollIndex) + players.get(i).position) % fields.size();

            rollIndex++;

            players.get(i).round(fields.get(players.get(i).position));

            if (players.get(i).isBankrupt) {
                players.get(i).loseGame();
                players.remove(i);
            } else {
                i += 1;
            }

        }
        
        String inputWeGot = "Name is: "+ players.get(0).name + " Last budget is: " + players.get(0).getBudget() + " Number of properties is:  " + players.get(0).properties.size();
        
        System.out.println("Name is: "+ players.get(0).name + " Last budget is: " + players.get(0).getBudget() + " Number of properties is:  " + players.get(0).properties.size() + " If it is same: " + inputWeGot.equals(inputResultF));



    }
}