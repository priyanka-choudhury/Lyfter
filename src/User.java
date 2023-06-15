public abstract class User {
    
    private int xCor; 
    private int yCor; 
    private String name; 

    public User(int x, int y, String name) {
        this.xCor = x; 
        this.yCor = y;
        this.name = name;
    }

    public User(String name) {
        this.name = name; 
    }

    public int getX() {
        return this.xCor;
    }

    public int getY() {
        return this.yCor;
    }

    public String getName() {
        return this.name;
    }

    public void changeX(int x) {
        this.xCor = x;
    }

    public void changeY(int y) {
        this.yCor = y;
    }

    public String toString() {
        return this.xCor + " " + this.yCor + " " + this.name;
    }
}
