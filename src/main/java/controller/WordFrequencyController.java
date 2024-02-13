package src.main.java.controller;


import src.main.java.service.WordFrequencyService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class WordFrequencyController {

    WordFrequencyService wordFrequencyService= new WordFrequencyService();
    Scanner sc = new Scanner(System.in);
    public void runWordFrequencyApplication(){
        while(true){
            showMenu();
            String userAction = getUserAction("Enter the Action number you want to perform ");
            completeUserAction(userAction);
        }
    }

    private String getUserAction(String userAction) {
        System.out.print(userAction + " : ");
        return sc.nextLine();
    }

    private void completeUserAction(String userAction) {
        switch (userAction){
            case "1":
                generateWordFrequency();
                break;
            case "2":
                sortWordsByCount();
                break;
            case "3":
                System.exit(200);
                break;
            default:
                System.out.println("Enter the valid input!!");
        }
    }

    private void sortWordsByCount() {
        System.out.println("Sorted words based on frequency!!");
        printWordFrequencyHeader();
        Map<String, Integer> sortedWords = wordFrequencyService.sortWordFrequencies();
        wordFrequencyIterator(sortedWords);
    }

    private void wordFrequencyIterator(Map<String, Integer> sortedWords) {
        for(Map.Entry<String, Integer> entryKey : sortedWords.entrySet()){
            System.out.println(entryKey.getKey() + " : " + entryKey.getValue());
        }
    }

    private void generateWordFrequency() {
        System.out.println("Enter the type of input (text, paragraph, file):");
        String parsedText = getUserInput(sc.nextLine().trim().toLowerCase());
        Map<String, Integer> wordFreqMap= wordFrequencyService.countWordFrequency(parsedText);
        printWordFrequencyHeader();
        wordFrequencyIterator(wordFreqMap);
    }

    private String getUserInput(String inputType) {
        switch (inputType) {
            case "text":
                 return readTextFromConsole();
            case "paragraph":
                return readParagraphFromConsole();
            case "file":
                return readTextFromInputFile();
            default:
                System.out.println("Invalid input type!!");
        }
       return "";
    }

    private String readTextFromConsole() {
        System.out.print("Enter your text or sentence: ");
        String userInput = sc.nextLine();
        System.out.println("You entered: " + userInput);
        return userInput;
    }
    private String readParagraphFromConsole() {
        StringBuilder userInput = new StringBuilder();
        System.out.println("Enter your paragraph (press Enter twice to finish):");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                break;
            }
            userInput.append(line).append("\n");
        }
        System.out.println("You entered:\n" + userInput);
        return userInput.toString();
    }

    private String readTextFromInputFile() {
        System.out.println("Enter the path to the file:");
        String filePath = sc.nextLine().trim();
        StringBuilder fileContent = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            fileContent = new StringBuilder();
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            System.out.println("File content:\n" + fileContent.toString());
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return fileContent.toString();
    }

    private static void printWordFrequencyHeader() {
        System.out.println("---------------------------------");
        System.out.println("WORD" + " | " + "FREQUENCY(#)");
        System.out.println("---------------------------------");
    }


    private void showMenu() {
        System.out.println("Welcome to the Word frequency counter App!!");
        System.out.println("Pick the action items : ");
        System.out.println("1. Generate word frequency count");
        System.out.println("2. Sort the words based on frequency count");
        System.out.println("3. Exit!!");
    }
}
