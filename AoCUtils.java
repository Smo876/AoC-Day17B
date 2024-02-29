import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class AoCUtils { 
    /**
    * loadInput loads the puzzle input in an ArrayList<String>
    * @param fileName: filename of the puzzle input
    * @return inputData: Array with Strings of the puzzel input
    * @since 1.0
    */
    public static ArrayList<String> loadInput(String fileName)  throws Exception {
        ArrayList<String> inputData = new ArrayList<>();
	    File doc = new File(fileName);
        Scanner obj = new Scanner(doc);
        while (obj.hasNextLine()) inputData.add(obj.nextLine());
        return inputData;
    }
    
    /**
    * showInput displayed an ArrayList<String>
    * @param inputData: ArrayList that is displayed
    * @since 1.0
    */
    public static void showInput(ArrayList<String> inputData) {
        for (String line : inputData) {
            System.out.println(line);
        }
    }
    
    /**
    * showWorkData displayed an ArrayList that contains an ArrayList<Integer>
    * @param inputData: ArrayList that contains the ArrayList that is displayed
    * @since 1.0
    */
    public static void showIntegerWorkData(ArrayList<ArrayList<Integer>> inputData) {
        for (ArrayList<Integer> line : inputData) {
            String listString = "";
            for (int s : line) {
                listString += Integer.toString(s) + " ";
            }
            System.out.println(listString);
        }
    }
    
    /**
    * showWorkData displayed an ArrayList that contains an ArrayList<String>
    * @param inputData: ArrayList that contains the ArrayList that is displayed
    * @since 1.0
    */
    public static void showStringWorkData(ArrayList<ArrayList<String>> inputData) {
        for (ArrayList<String> cube : inputData) {
            for(String line : cube) {
                System.out.println(line);
            }
            System.out.println("");
        }
    }
    
    /**
    * splitInput splittet Strings in an ArrayList in two parts and save them in two new ArrayLists
    * @param inputData: ArrayList that contains the Strings
    * @param splitter: String at which the String is to be split
    * @param workData1: ArrayList in which part one would be saved
    * @param workData2: ArrayList in which part one would be saved
    * @since 1.0
    */
    public static void splitInput(ArrayList<String> inputData, String splitter, ArrayList<String> workData1, ArrayList<String> workData2) {
        for (String line : inputData) {
            String[] parts = line.split(splitter);
            workData1.add(parts[0]);
            workData2.add(parts[1]);
        }
    }
    
    /**
    * splitInput splittet Strings in an ArrayList in different integers and save them in an new ArrayList
    * @param inputData: ArrayList that contains the Strings
    * @param splitter: String at which the String is to be split
    * @param workData: ArrayList which contains the ArrayList<Integer> in which part one would be saved
    * @since 1.0
    */
    public static void splitInput(ArrayList<String> inputData, String splitter, ArrayList<ArrayList<Integer>> workData) {
        for (int j = 0; j < inputData.size(); j++) {
            String[] parts = inputData.get(j).split(splitter);
            ArrayList<Integer> data = new ArrayList<Integer>();
            for (int i = 0; i < parts.length; i++) {
                data.add(Integer.parseInt(parts[i]));
            }
            workData.add(data);
        }
    }
    
    /**
    * changeCharInPosition changes one char in an explicit position
    * @param position: position at which the character is to be changed
    * @param ch: should be changed to this character
    * @param str: string that should be changed
    * @return charArray: the new string
    * @since 1.0
    */
    public static String changeCharInPosition(int position, char ch, String str) {
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }
    
    /**
    * removeString deletes a specific part of a string (opposite of substring)
    * @param str: string that should be changed
    * @param indexStart: position from which to delete
    * @param indexEnd: position until it is to be deleted
    * @return newString: the new string
    * @since 1.0
    */
    public static String removeString(String string, int indexStart, int indexEnd) {
	     String newString = "";
	     for (int i = 0; i < string.length(); i++) {
	         if(i < indexStart || i >= indexEnd) {
	             newString += string.charAt(i);
	         }
	     }
	    return newString;
    }
    
    /**
    * splitBlocks splittet Strings in an Array in different Blocks
    * @param inputData: ArrayList that contains the Strings
    * @param workData: ArrayList in which the block parts would be saved
    * @since 1.0
    */
    public static void splitBlocks(ArrayList<String> inputData, ArrayList<ArrayList<String>> workData) {
        int i = 0;
        ArrayList<String> data = new ArrayList<String>();
        
        for (String line : inputData) {
            if (line.length() > 0) {
                data.add(line);
            } else {
                workData.add(data);
                data = new ArrayList<String>();
            }
        }
        workData.add(data);
    }
    
    /**
    * mirrorCube rows and colums in an array are swapped
    * @param cubeData: ArrayList that contains the block
    * @return cube: new ArrayList with mirrored Data
    * @since 1.0
    */
    public static ArrayList<String> mirrorCube(ArrayList<String> cubeData){
        ArrayList<String> cube = new ArrayList<String>();
        for (int i = 0; i < cubeData.get(0).length(); i++){
            String newLine = "";
            for (String line : cubeData) { 
                newLine += line.charAt(i);
            }
            cube.add(newLine);
        }
        return cube;
    }
    
    /**
    * rotateCubeLeft rows and colums in an array are rotated 90° left
    * @param cubeData: ArrayList that contains the block
    * @return cube: new ArrayList with rotated Data
    * @since 1.0
    */
    public static ArrayList<String> rotateCubeLeft(ArrayList<String> cubeData){
        ArrayList<String> cube = new ArrayList<String>();
        for (int i = cubeData.get(0).length(); i > 0; i--){
            String newLine = "";
            for (String line : cubeData) { 
                newLine += line.charAt(i-1);
            }
            cube.add(newLine);
        }
        return cube;
    }
    
    /**
    * rotateCubeRight rows and colums in an array are rotated 90° left
    * @param cubeData: ArrayList that contains the block
    * @return cube: new ArrayList with rotated Data
    * @since 1.0
    */
    public static ArrayList<String> rotateCubeRight(ArrayList<String> cubeData){
        ArrayList<String> cube = new ArrayList<String>();
        for (int i = 0; i < cubeData.get(0).length(); i++){
            String newLine = "";
            for (int j = cubeData.get(0).length(); j > 0; j--) { 
                newLine += cubeData.get(j-1).charAt(i);
            }
            cube.add(newLine);
        }
        return cube;
    }
    
    public static void copyData(ArrayList<String> oldData, ArrayList<String> newData) {
        newData.clear();
        for (String line : oldData) {
            newData.add(line);
        }
    }
    
}