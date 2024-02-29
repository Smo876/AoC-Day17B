public class Node implements Comparable<Node>{
    private final int posX, posY;
    private int pathValue;
    private final int direction;
    private final int distance;
    private final int id;
    private int preId;

    public Node(int posX, int posY, int pathValue, int direction, int distance, int id, int preId) {
        this.posX = posX;
        this.posY = posY;
        this.pathValue = pathValue;
        this.direction = direction;
        this.distance = distance;
        this.id = id;
        this.preId = preId;
    }
        
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPathValue() {
        return pathValue;
    }
    
    public void setPathValue(int pathValue) {
        this.pathValue = pathValue;
    }

    public int getDirection() {
        return direction;
    }
    
    public int getDistance() {
        return distance;
    }
    
    public int getId() {
        return id;
    }
    
    public int getPreId() {
        return preId;
    }
    
    @Override 
    public String toString() {
        return this.id +" X: "+ this.posX +" Y: "+ this.posY +" Val: "+ this.pathValue;
    }
    
    @Override 
    public int compareTo(Node o) {
        if (this.id == o.id && this.pathValue >= o.pathValue) {
            return 0;
        }
        else if (this.id == o.id && this.pathValue < o.pathValue) {
            o.pathValue = this.pathValue;
            o.preId = this.preId;
            return 0;
        }    
        if (this.pathValue < o.pathValue)
            return -1;
        else 
            return 1;
    }

}