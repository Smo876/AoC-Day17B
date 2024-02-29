import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;

public class Main {

    static ArrayList<String> inputData = new ArrayList<String>();
    static ArrayList<Node> closeNodes = new ArrayList<Node>();
    static TreeSet unvisited = new TreeSet();
    static int[] visited = new int[20022500];

    static final int NORTH = 1;
    static final int EAST = 2;
    static final int SOUTH = 3;
    static final int WEST = 4;

    static boolean goalReached = false;
    static String filename = "input.txt";

    public static void main(String[] args) throws InterruptedException {
        try {
            inputData = AoCUtils.loadInput(filename);
        } catch (Exception e) {
            System.out.println("File not found!");
        }

        double oldTime = System.currentTimeMillis();

        Node node1 = new Node(0, 0, 0, 2, 0, getId(0,0,2,0), getId(0,0,2,0));
        unvisited.add(node1);
        Node node2 = new Node(0, 0, 0, 3, 0, getId(0,0,3,0), getId(0,0,2,0));
        unvisited.add(node2);
        checkNeighbors();
        double newTime = System.currentTimeMillis();
        System.out.println(newTime - oldTime);
    }

    private static void checkNeighbors ()  {
        while (!goalReached) {
            Iterator it = unvisited.iterator();
            if (!it.hasNext()) {
                System.out.println("list is empty");
                break;
            }
            Node node = (Node) it.next();
            it.remove();
            
            
            if (node.getPosX() == inputData.get(0).length() - 1 && node.getPosY() == inputData.size() - 1 && node.getDistance() > 3) {
                goalReached = true;
                reconstructMap(node);
                System.out.println(node);
                break;
            }
            int posX = node.getPosX();
            int posY = node.getPosY();
            int pathValue = node.getPathValue();
            int direction = node.getDirection();
            int distance = node.getDistance();
            int id = node.getId();
            
            if (visited[id] == 0) {
                visited[id] = 1;
                
                closeNodes.add(node);

                // EAST
                if (stepIsPossible(posX+1, posY, distance, sameDirection(direction, EAST)) && direction != WEST) {
                        if (sameDirection(direction, EAST)) {
                            sortInNode(posX+1, posY, pathValue, EAST, ++distance, id);
                        } else {
                            sortInNode(posX+1, posY, pathValue, EAST, 1, id);
                        }
                } // SOUTH
                if (stepIsPossible(posX, posY+1, distance, sameDirection(direction, SOUTH)) && direction != NORTH) {
                        if (sameDirection(direction, SOUTH)) {
                            sortInNode(posX, posY+1, pathValue, SOUTH, ++distance, id);
                        } else {
                            sortInNode(posX, posY+1, pathValue, SOUTH, 1, id);
                        }
                } // WEST
                if (stepIsPossible(posX-1, posY, distance, sameDirection(direction, WEST)) && direction != EAST) {
                        if (sameDirection(direction, WEST)) {
                            sortInNode(posX-1, posY, pathValue, WEST, ++distance, id);
                        } else {
                            sortInNode(posX-1, posY, pathValue, WEST, 1, id);
                        }
                } // NORTH
                if (stepIsPossible(posX, posY-1, distance, sameDirection(direction, NORTH)) && direction != SOUTH) {
                        if (sameDirection(direction, NORTH)) {
                            sortInNode(posX, posY-1, pathValue, NORTH, ++distance, id);
                        } else {
                            sortInNode(posX, posY-1, pathValue, NORTH, 1, id);
                        }
                }
            }

        }

    }

    public static boolean sameDirection(int oldDir, int newDir) {
        return (oldDir == newDir);
    }

    public static boolean stepIsPossible(int posX, int posY, int distance, boolean sameDirection) {

        if ((posX < 0) || (posX > inputData.get(0).length() - 1) || (posY < 0) || (posY > inputData.size() - 1)) {
            return false;
        }
        if (sameDirection) {
            return distance <= 10;
        } else {
            return (distance >= 4 && distance <= 10);
        }
    }

    public static void sortInNode(int posX, int posY, int pathValue, int direction, int distance, int preId) {
        int id = getId(posX, posY, direction, distance);
        pathValue += Character.getNumericValue(inputData.get(posY).charAt(posX));
        Node node = new Node(posX, posY, pathValue, direction, distance, id, preId);
        unvisited.add(node);
    }

    
    public static int getId(int x, int y, int direction, int distance) {
        String newId;
        newId = Integer.toString(y * inputData.get(0).length() + x);
        switch (direction) {
            case 1:
                newId += "1";
                break;
            case 2:
                newId += "2";
                break;
            case 3:
                newId += "3";
                break;
            case 4:
                newId += "4";
                break;
            default:
                newId += "0";
                break;
        }
        newId += Integer.toString(distance);
        return Integer.parseInt(newId);
    }
    
    public static void reconstructMap(Node firstNode) {
        int id = firstNode.getPreId();
        inputData.set(firstNode.getPosY(), AoCUtils.changeCharInPosition(firstNode.getPosX(), '-', inputData.get(firstNode.getPosY())));
        boolean end = false;
        do {
            for (Node node : closeNodes) {
                if (node.getId() == id) {
                    inputData.set(node.getPosY(), AoCUtils.changeCharInPosition(node.getPosX(), '-', inputData.get(node.getPosY())));
                    id = node.getPreId();
                    if (node.getPosX() == 0 && node.getPosY() == 0) {
                        end = true;
                    }
                    break;
                }
            }
        } while (!end);
        
        AoCUtils.showInput(inputData);
    }

}
