/******
 * MoloHunt 2016
 *
 * Levenshtein Distance Calculator
 * Create an object and pass two strings and the class will
 * calculate the Levenshtein Distance and also allow you to print the
 * matrix of working values to inspect
 *
 * Usage is Simple
 *
 * LevenshteinDistance ld = new LevenshteinDistance();
 * int diastance = ld.CalculateDistance(word1, word2);
 *
 * After calculating the distance you can print the matrix of working values to the console
 *
 * ld.PrintMatrix();
 *
 ******/

public class LevenshteinDistance{

    private char[] word1, word2;

    private int[][] workingMatrix;

    //Constructor
    public LevenshteinDistance(){
    }

    //Initialize Values
    //allows for resetting object without having to create a new object
    private void Initialize(String w1, String w2) {
        word1 = w1.toUpperCase().toCharArray();
        word2 = w2.toUpperCase().toCharArray();

        workingMatrix = new int[word1.length + 1][word2.length + 1];

        for (int x = 0; x < word1.length + 1; x++) {
            workingMatrix[x][0] = x;
        }
        for (int y = 0; y < word2.length + 1; y++) {
            workingMatrix[0][y] = y;
        }
    }

    //Runs through the Levenshtein algorithm
    public int CalculateDistance(String w1, String w2){
        Initialize(w1, w2);
        for (int x = 1; x < word1.length + 1; x++) {
            for (int y = 1; y < word2.length + 1; y++) {
                int subCost = 1;
                if(word1[x - 1] == word2[y - 1]){
                    subCost = 0;
                }
                int del = workingMatrix[x - 1][y] + 1;
                int ins = workingMatrix[x][y - 1] + 1;
                int sub = workingMatrix[x - 1][y - 1] + subCost;
                workingMatrix[x][y] = Math.min(del, Math.min(ins, sub));
            }
        }

        int result = workingMatrix[word1.length][word2.length];

        return result;
    }

    //Iterates throught the matrix and prints the matrix to the console
    public void PrintMatrix(){
        for (int y = 0; y < word2.length + 1; y++) {
            String line = "";
            for (int x = 0; x < word1.length + 1; x++) {
                line += "[" + Format(workingMatrix[x][y]) + "]";
            }
            System.out.println(line);
        }
    }

    //Returns the Matrix of Workign values
    public int[][] GetMatrix(){
        return workingMatrix;
    }
    
    //Formats numbers into 2 digit strings
    private String Format(int i) {
        if(i < 10){
            return "0" + i;
        }else{
            return "" + i;
        }
    }
}
