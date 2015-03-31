package jp;

/**
 * Created by JasonPan on 3/31/15.
 */
public class Spreadsheets {
    private Block[][] sheet;
    private int row;
    private int column;

    public Spreadsheets() {
    }

    public Spreadsheets(int row, int column) {
        this.row = row;
        this.column = column;
        this.sheet = new Block[row][column];
    }
}
