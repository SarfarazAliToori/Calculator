package com.example.calculatordesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOne, btnTwo, btnThree, btnFour, btnFive,
            btnSix, btnSeven, btnEight, btnNine, btnZero, btnPlus,
            btnMinus,  btnMultiply, btnDivid, btnPoint, btnCancel,
            btnSpace, btnPi, btnSin , btnCos;
    EditText edInputNum;
    TextView history;

    String oldNumber, newNumber;
    String operator;
    private String helperStr = null;
    boolean isNewNum = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInit();
        mButtonsListener();
    }

    public void mInit() {

        edInputNum = findViewById(R.id.ed_input_text);
        history = findViewById(R.id.tv_history);
        btnSpace = findViewById(R.id.btn_backSpace);
        btnOne = findViewById(R.id.btn_one);
        btnTwo = findViewById(R.id.btn_two);
        btnThree = findViewById(R.id.btn_three);
        btnFour = findViewById(R.id.btn_four);
        btnFive = findViewById(R.id.btn_five);
        btnSix = findViewById(R.id.btn_six);
        btnSeven = findViewById(R.id.btn_seven);
        btnEight = findViewById(R.id.btn_eight);
        btnNine = findViewById(R.id.btn_nine);
        btnZero = findViewById(R.id.btn_zero);
        btnPoint = findViewById(R.id.btn_point);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivid = findViewById(R.id.btn_Divide);
        btnCancel = findViewById(R.id.btn_cancel);
        btnPi = findViewById(R.id.btn_pi);
        btnSin = findViewById(R.id.btn_sin);
        btnCos = findViewById(R.id.btn_cos);

    }

    public void mButtonsListener() {
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSpace.setOnClickListener(this);
        btnPi.setOnClickListener(this);
        btnSin.setOnClickListener(this);
        btnCos.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (isNewNum) {
            edInputNum.setText(" ");
            isNewNum = false;
        }
        String number = edInputNum.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_one: number = number + "1"; break;
            case R.id.btn_two: number += "2"; break;
            case R.id.btn_three: number += "3"; break;
            case R.id.btn_four: number += "4"; break;
            case R.id.btn_five: number += "5"; break;
            case R.id.btn_six: number += "6"; break;
            case R.id.btn_seven: number += "7"; break;
            case R.id.btn_eight: number += "8"; break;
            case R.id.btn_nine: number += "9"; break;
            case R.id.btn_zero: number += "0"; break;
            case R.id.btn_point: number += "."; btnPoint.setEnabled(false); break;
            case R.id.btn_cancel: number = ""; history.setText(""); btnPoint.setEnabled(true); break;
            case R.id.btn_backSpace:
                 String currentText = edInputNum.getText().toString();
                 number = currentText.substring(0, currentText.length()-1);
                 btnPoint.setEnabled(true);
                 break;
            case R.id.btn_pi: number = "3.14"; history.setText("3.1415926535"); break;
            case R.id.btn_sin: number = Math.sin(Math.toRadians(Double.parseDouble(number))) + "";
                 history.setText("sin(" + edInputNum.getText() + ")");
                 break;
            case R.id.btn_cos: number = Math.cos(Math.toRadians(Double.parseDouble(number))) + "";
                 history.setText("cos(" + edInputNum.getText() + ")");
                 break;
        }
        edInputNum.setText(number);
    }

    public void operatorEvents(View view) {
        isNewNum = true;
        btnPoint.setEnabled(true);
        oldNumber = edInputNum.getText().toString();

        switch(view.getId()) {
            case R.id.btn_plus: operator = "+"; break;
            case R.id.btn_multiply: operator = "*"; break;
            case R.id.btn_minus: operator = "-"; break;
            case R.id.btn_Divide: operator = "/"; break;
        }
        edInputNum.setText(oldNumber +" " + operator);
        helperStr = oldNumber + operator;
    }

    public void equalButtonEvent( View view) {
        try {
            newNumber = edInputNum.getText().toString();
            edInputNum.setText(oldNumber + operator + newNumber);
            Double result = 0.0;
            if (oldNumber.isEmpty() && newNumber.isEmpty()) {
                Toast.makeText(this, "Enter some digit", Toast.LENGTH_SHORT).show();
            } else {
                switch (operator) {
                    case "+": result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);break;
                    case "-": result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber); break;
                    case "/": result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber); break;
                    case "*": result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber); break;
                    default: Toast.makeText(getApplicationContext(), "Choose Operator", Toast.LENGTH_SHORT).show(); break;
                }
                edInputNum.setText("" + result);
                //history.setText("" + oldNumber +" "+ operator + newNumber);
                String mm = edInputNum.getText().toString();
                mm += " = "  + oldNumber + "  " + operator + " " + newNumber ;
                history.setText(mm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Type Some Digit", Toast.LENGTH_SHORT).show();
        }
    }

}