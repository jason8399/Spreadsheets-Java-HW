package jp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JasonPan on 3/31/15.
 */
public class Spreadsheets {
    private final String TABLE = "ABCDEF";
    private final String OPEARTOR = "+-*/";
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
                if(input.hasNextInt()) {        //If next is int
                    this.sheet[row][column] = new Cell(input.nextInt());
                }
                else if(input.hasNextLine()){   //If next is formula
                    this.sheet[row][column] = new Cell(input.nextLine());
                }
                else{
                    System.out.println("Err input Type.\nDefault set cell 0");
                    this.sheet[row][column] = new Cell(0);
                }
            }
        }
    }

    private void checkAndTranslateSheet(){
        for(int row = 0; row < this.row; row++){
            for(int column = 0; column < this.column; column++){
                if(this.sheet[row][column].isFormula()){
                    calculateFormula(this.sheet[row][column]);
                }
            }
        }
    }

    private int getNumberInCellbyName(String name){
        //Separate Alphabet and Number
        Pattern pattern = Pattern.compile("([A-F])(\\d+)");
        Matcher matcher = pattern.matcher(name);

        //Check whether name is match or not
        if(matcher.matches()){
            int row = TABLE.indexOf(matcher.group(1));
            int column = Integer.parseInt(matcher.group(2));
            //Check whether out of range or not;
            if((row < this.row) && (column < this.column))
                return this.sheet[row][column].getNumber();
            else
                System.out.println("OutOfRange return 0");
        }
        else {
            System.out.println("String doesn't match return 0");
        }
        return 0;
    }

    private void calculateFormula(Cell formula){
        ArrayList<String> input = formula.getFormulaArrayList();
        Stack<String> operand = new Stack<>();
        Stack<String> operator = new Stack<>();
        int right, left, optor, ans;

        //Infix calculate
        for(String now: input){
            switch (now){
                case "+":
                case "-":
                case "*":
                case "/":
                    if(!operator.isEmpty()) {
                        if (OPEARTOR.indexOf(operator.peek()) > OPEARTOR.indexOf(now)) {
                            right = Integer.parseInt(operand.pop());
                            left = Integer.parseInt(operand.pop());
                            optor = OPEARTOR.indexOf(operator.pop());
                            ans = 0;
                            switch (optor) {
                                case 0:
                                    ans = right + left;
                                    break;
                                case 1:
                                    ans = right - left;
                                    break;
                                case 2:
                                    ans = right * left;
                                    break;
                                case 3:
                                    ans = right / left;
                                    break;
                            }
                            operand.push(Integer.toString(ans));
                        }
                    }
                    operator.push(now);
                    break;
                default:
                    now = Integer.toString(getNumberInCellbyName(now));
                    operand.push(now);
                    break;
            }
        }
        for(;!operator.isEmpty();){
            right = Integer.parseInt(operand.pop());
            left = Integer.parseInt(operand.pop());
            optor = OPEARTOR.indexOf(operator.pop());
            ans = 0;
            switch (optor) {
                case 0:
                    ans = right + left;
                    break;
                case 1:
                    ans = right - left;
                    break;
                case 2:
                    ans = right * left;
                    break;
                case 3:
                    ans = right / left;
                    break;
            }
            operand.push(Integer.toString(ans));
        }

        //Set Ans to Cell
        ans = Integer.parseInt(operand.pop());
        formula.setNumber(ans);
    }
}
