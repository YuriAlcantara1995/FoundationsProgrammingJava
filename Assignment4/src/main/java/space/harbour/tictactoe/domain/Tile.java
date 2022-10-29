package space.harbour.tictactoe.domain;

public class Tile {
    private String value;
    public Tile() {
        this.value = "";
    }

    public Tile(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }
}