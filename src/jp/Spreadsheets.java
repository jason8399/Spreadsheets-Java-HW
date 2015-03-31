package jp;

import java.util.Scanner;

/**
 * Created by JasonPan on 3/31/15.
 */
public class Spreadsheets {
    private Cell[][] sheet;
    private int row;
    private int column;

    public Spreadsheets() {
    }

    public Spreadsheets(int row, int column) {
        this.row = row;
        this.column = column;
        this.sheet = new Cell[row][column];
    }

    public void makeSheet(){
        readCells();
    }

    private void readCells(){
        Scanner input = new Scanner(System.in);

        for(int row = 0; row < this.row; row++){
            for(int column = 0; column < this.column; column++){
                if(input.hasNextInt()) {
                    this.sheet[row][column] = new Cell(input.nextInt());
                }
                else if(input.hasNextLine()){
                    this.sheet[row][column] = new Cell(input.nextLine());
                }
                else{
                    System.out.println("Err input Type.\nDefault set cell 0");
                    this.sheet[row][column] = new Cell(0);
                }
            }
        }
    }
}
