import java.util.ArrayList;

class Vector {
    private int key;
    private int color;
    public ArrayList<Vector> childrenKeys;

    public Vector(int key) {
        this.key = key;
        this.color = 0;
        childrenKeys = new ArrayList<>();
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getKey() {
        return key;
    }

    public int getColor() {
        return color;
    }
}