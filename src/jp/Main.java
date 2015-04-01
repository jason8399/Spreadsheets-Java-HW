package jp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);
        Spreadsheets sheet;
        while(input.hasNext()) {
            int column = input.nextInt();
            int row = input.nextInt();
            sheet = new Spreadsheets(row, column);
            while(sheet.hasNextCell()){
                sheet.nextCell(input.next());
            }
            sheet.makeSheet();
        }
    }
}
