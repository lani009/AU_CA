package app;

public interface Skinnable {
    int BLACK = 0;
    int RED = 1;
    int GREEN = 2;
    int BLUE = 3;
    int YELLOW = 4;

    public abstract void changeSkin(int skin);

    public abstract void printSkin();
}