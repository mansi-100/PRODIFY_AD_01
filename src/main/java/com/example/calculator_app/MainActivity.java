package com.example.calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    String[] num = {"%", "CE", "+", "-", "*", "/", "7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "="};
    TextView textView, textView2;
    String expression = ""; // Initialize an empty string to store the expression

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, num);
        gridView.setAdapter(arrayAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clickedItem = adapterView.getItemAtPosition(i).toString();
                if (clickedItem.equals("=")) {
                    // If the clicked item is "=", perform calculation
                    if (!expression.isEmpty()) {
                        double result = evaluateExpression(expression);
                        textView.setText(String.valueOf(result));
                        expression = ""; // Reset expression for next input
                    }
                }
                else if (clickedItem.equals("CE"))
                {
                    expression="";
                    textView.setText("");
                }
                else {
                    // Otherwise, append the clicked item to the existing expression
                    expression += clickedItem;
                    textView.setText(expression);
                }
            }
        });
    }

    // Method to evaluate the expression and return the result
// Method to evaluate the expression and return the result
// Method to evaluate the expression and return the result
    private double evaluateExpression(String expression) {
        double result = 0;
        String[] tokens = expression.split("(?<=[*/+%-])|(?=[*/+%-])");
        String[] operators = {"+", "-", "*", "/", "%"};
        String currentOperator = "+";
        boolean isFirstToken = true;
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (isOperator(token, operators)) {
                currentOperator = token;
            } else {
                double value = Double.parseDouble(token);
                if (isFirstToken) {
                    result = value; // Set result to the first operand
                    isFirstToken = false;
                } else {
                    switch (currentOperator) {
                        case "+":
                            result += value;
                            break;
                        case "-":
                            result -= value;
                            break;
                        case "*":
                            result *= value;
                            break;
                        case "/":
                            if (value != 0) {
                                result /= value;
                            } else {
                               Log.e("DivisionError", "Division by zero error!");
                            }
                            break;
                        case "%":
                            result %= value;
                            break;
                    }
                }
            }
        }
        return result;
    }

    // Helper method to check if a token is an operator
    private boolean isOperator(String token, String[] operators) {
        for (String operator : operators) {
            if (token.equals(operator)) {
                return true;
            }
        }
        return false;
    }
}
