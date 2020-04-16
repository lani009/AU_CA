package app.lani;

public class Box {
    private int width;
    private int length;
    private int height;

    public Box(int width, int length, int height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public boolean isSameBox(Box box) {
        if(this.width == box.width && this.length == box.length && this.height == box.height) {
            return true;
        }
        else {
            return false;
        }
    }
}