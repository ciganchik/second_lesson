package com.example.calculator.ui;

import com.example.calculator.model.Calculator;
import com.example.calculator.model.Operator;
import java.text.DecimalFormat;
public class CalculatorPresenter {

    private DecimalFormat format = new DecimalFormat("#.######");

    private CalculatorView view;
    private Calculator calculator;

    private double argOne;
    private Double argTwo;
    private Double numberAfterPoint;
    private boolean isSecond;
    private boolean isEquals;
    private Operator selectedOperator;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
        argOne = 0.0;
        argTwo = 0.0;
        isSecond = false;
        isEquals = false;

    }

    public void onDigitsPressed(int digit) {
        if (!isSecond){
            if (numberAfterPoint==null){
                argOne=argOne*10 + digit;
                showFormatted(argOne);
            }
            else{
                numberAfterPoint++;
                argOne=argOne+digit/Math.pow(10,numberAfterPoint);
                showFormatted(argOne,numberAfterPoint);
            }
        } else if (isSecond) {
            if (numberAfterPoint==null){
                argTwo=argTwo*10 + digit;
                showFormatted(argTwo);
            }
            else {
                numberAfterPoint++;
                argTwo=argTwo+digit/Math.pow(10,numberAfterPoint);
                showFormatted(argTwo, numberAfterPoint);
            }
        }
    }

    public void onOperatorPressed(Operator operator) {
        if(selectedOperator != null){

            argOne = calculator.perform(argOne, argTwo, selectedOperator);

            showFormatted(argOne);
        }
        argTwo = 0.0;
        selectedOperator = operator;
        isSecond = true;
        numberAfterPoint = null;
        isEquals = false;
    }

    public void onDotPressed() {

        if (numberAfterPoint==null && !isEquals){
            numberAfterPoint=0.0;
            if (!isSecond)
                showFormatted(argOne ,0.0);
            else
                showFormatted(argTwo ,0.0);
        }

    }

    public void onDefPressed(Operator operator) {
        if (selectedOperator != null){
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
        }
        argTwo = 0.0;
        selectedOperator = operator;
        isSecond = false;
        numberAfterPoint=null;
        isEquals = true;

    }

    public void onClearPressed() {
        argOne = 0.0;
        argTwo = null;
        showFormatted(argOne);
    }

    private void showFormatted(double value){
        view.showResult(format.format(value));
    }

    private void showFormatted(double value, double numberAfterPoint){
        StringBuilder pattern = new StringBuilder("0.");
        for (int i = 0; i<numberAfterPoint; i++)
            pattern.append("0");
        view.showResult(format.format(value));
    }

    public void onChangeSing() {
        if (!isSecond){
            if (argOne >= 1 || argOne <=1){
                argOne = argOne * (-1);
                showFormatted(argOne);
            }
        } else if (isSecond) {
            if (argTwo >= 1 || argTwo <=1){
                argTwo=argTwo * (-1);
                showFormatted(argTwo);
            }
        }
}

    public void onPercentPressed() {
        if (!isSecond){
            if (argOne >= 1 || argOne <=1){
                argOne = argOne / 100;
                showFormatted(argOne);
            }
        } else if (isSecond) {
            if (argTwo >= 1 || argTwo <=1){
                argTwo=argTwo / 100;
                showFormatted(argTwo);
            }
        }
    }
}
