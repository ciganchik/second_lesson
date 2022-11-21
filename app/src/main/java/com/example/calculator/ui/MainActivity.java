package com.example.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.calculator.R;
import com.example.calculator.model.CalculatorImpl;
import com.example.calculator.model.Operator;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CalculatorView{

    private TextView resultTxt;
    private CalculatorPresenter presenter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTxt = findViewById(R.id.display);
//        resultHistory = findViewById(R.id.history);
        presenter = new CalculatorPresenter(this, new CalculatorImpl());

/**
 * ПОИСК И ПРИСВОЕНИЯ ФУНКЦИИ ДЛЯ ЧИСЕЛ
 */
        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.button_0, 0);
        digits.put(R.id.button_1, 1);
        digits.put(R.id.button_2, 2);
        digits.put(R.id.button_3, 3);
        digits.put(R.id.button_4, 4);
        digits.put(R.id.button_5, 5);
        digits.put(R.id.button_6, 6);
        digits.put(R.id.button_7, 7);
        digits.put(R.id.button_8, 8);
        digits.put(R.id.button_9, 9);


        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitsPressed(digits.get(view.getId()));
            }
        };

        findViewById(R.id.button_0).setOnClickListener(digitClickListener);
        findViewById(R.id.button_1).setOnClickListener(digitClickListener);
        findViewById(R.id.button_2).setOnClickListener(digitClickListener);
        findViewById(R.id.button_3).setOnClickListener(digitClickListener);
        findViewById(R.id.button_4).setOnClickListener(digitClickListener);
        findViewById(R.id.button_5).setOnClickListener(digitClickListener);
        findViewById(R.id.button_6).setOnClickListener(digitClickListener);
        findViewById(R.id.button_7).setOnClickListener(digitClickListener);
        findViewById(R.id.button_8).setOnClickListener(digitClickListener);
        findViewById(R.id.button_9).setOnClickListener(digitClickListener);

/**
 *         ПОИСК И ПРИСВОЕНИЯ ФУНКЦИИ ДЛЯ ОПЕРАТОРОВ
  */

        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.button_plus, Operator.ADD);
        operators.put(R.id.button_minus, Operator.SUB);
        operators.put(R.id.button_mult, Operator.MULT);
        operators.put(R.id.button_dive, Operator.DIV);

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };

        findViewById(R.id.button_plus).setOnClickListener(operatorClickListener);
        findViewById(R.id.button_minus).setOnClickListener(operatorClickListener);
        findViewById(R.id.button_mult).setOnClickListener(operatorClickListener);
        findViewById(R.id.button_dive).setOnClickListener(operatorClickListener);

/**
 * ПОИСК И ПРИСВОЕНИЯ ФУНКЦИИ ДЛЯ ТОЧКИ
 */

        findViewById(R.id.button_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 presenter.onDotPressed();
            }
        });

/**
 * ПОИСК И ПРИСВОЕНИЯ ФУНКЦИИ ДЛЯ РАВНО
 */

        findViewById(R.id.button_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDefPressed(operators.get(view.getId()));
            }
        });

/**
 * ПОИСК И ПРИСВОЕНИЯ ФУНКЦИИ ДЛЯ УДАЛИТЬ
 */

        findViewById(R.id.button_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClearPressed();
            }
        });



    }



    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }

}