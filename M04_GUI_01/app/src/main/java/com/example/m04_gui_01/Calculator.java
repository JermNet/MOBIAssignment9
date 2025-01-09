package com.example.m04_gui_01;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

// RULE 3: Having this + the music player follows the rule of let things only do what they're expected to do. It was described as having no "God classes"

/**
 * Author: Jeremy Whitenect
 * Year: 2024
 * This code is the intellectual property of Jeremy Whitenect. If this code is stolen, used for monetary gain, or reproduced without permission, you will be prosecuted to the fullest extent of the law.
 */
public class Calculator {
    private EditText textN1, textN2, textANS;
    private Double double1, double2, answer;

    public Calculator(View view) {
        textN1 = view.findViewById(R.id.editTextN1);
        textN2 = view.findViewById(R.id.editTextN2);
        textANS = view.findViewById(R.id.editTextNumAns);
        double1 = 0.0;
        double2 = 0.0;
        answer = 0.0;
    }

    // RULE 4: This follows the rule of not having a bunch of repeated code, in the original these were four separate methods, but this works to alleviate that somewhat.

    // RULE 5: This throws an exception instead of catching, and then avoiding it, which was explained in the AOSP guide.
    public void calculate(View view, String operand) throws ArithmeticException {
        Log.d(operand + " BUTTON", "User tapped the " + operand + " button.");
        double1 = Double.parseDouble(textN1.getText().toString());
        double2 = Double.parseDouble(textN2.getText().toString());

        switch (operand) {
            case "+":
                answer = double1 + double2;
                break;
            case "-":
                answer = double1 - double2;
                break;
            case "*":
                answer = double1 * double2;
                break;
            case "/":
                answer = double1 / double2;
                break;
            default:
                Log.d("BUTTON", "Not a valid operand");
        }
        Log.w(operand + " BUTTON", "Selected with => " + double1 + operand + double2 + "=" + answer);
        textANS.setText(answer.toString());
    }
}
