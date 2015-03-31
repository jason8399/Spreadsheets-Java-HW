package jp;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JasonPan on 3/31/15.
 */
public class Block {
    private int number;
    private String formula;
    private boolean isFormula;

    public Block(){

    }

    public Block(int input) {
        this.number = input;
        this.isFormula = false;
    }

    public Block(String input) {
        this.formula = input;
        this.isFormula = true;
    }

    public ArrayList<String> processFormula(){
        ArrayList<String> processed = new ArrayList<>();
        Pattern pattern = Pattern.compile("[+-/*]|[A-F]\\d+");
        Matcher matcher = pattern.matcher(this.formula);

        while(matcher.find()){
            processed.add(matcher.group());
        }
        return processed;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public boolean isFormula() {
        return isFormula;
    }

    public void setIsFormula(boolean isFormula) {
        this.isFormula = isFormula;
    }
}
