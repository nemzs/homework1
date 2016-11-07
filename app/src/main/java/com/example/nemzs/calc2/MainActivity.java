package com.example.nemzs.calc2;

import android.content.DialogInterface;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import ru.ifmo.android_2016.calc.R;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    double left = -1, last = 0;
    String lastOperation = "none";
    boolean leftBuffer = false, newDigit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Button d0 = (Button) findViewById(R.id.d0);
        Button d1 = (Button) findViewById(R.id.d1);
        Button d2 = (Button) findViewById(R.id.d2);
        Button d3 = (Button) findViewById(R.id.d3);
        Button d4 = (Button) findViewById(R.id.d4);
        Button d5 = (Button) findViewById(R.id.d5);
        Button d6 = (Button) findViewById(R.id.d6);
        Button d7 = (Button) findViewById(R.id.d7);
        Button d8 = (Button) findViewById(R.id.d8);
        Button d9 = (Button) findViewById(R.id.d9);
        d0.setOnClickListener(this);
        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d4.setOnClickListener(this);
        d5.setOnClickListener(this);
        d6.setOnClickListener(this);
        d7.setOnClickListener(this);
        d8.setOnClickListener(this);
        d9.setOnClickListener(this);
        Button add = (Button) findViewById(R.id.add);
        Button sub = (Button) findViewById(R.id.sub);
        Button mul = (Button) findViewById(R.id.mul);
        Button div = (Button) findViewById(R.id.div);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(this);
        Button equal = (Button) findViewById(R.id.eqv);
        equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView result = (TextView) findViewById(R.id.result);
        String resultText = result.getText().toString();
        Button digit = (Button) v;
        String digitText = digit.getText().toString();
        switch (v.getId()) {
            case R.id.add:
                if (newDigit) break;
                lastOperation = digitText;
                newDigit = true;
                if (leftBuffer) {
                    double current = Double.parseDouble(resultText) + left;
                    result.setText(Double.toString(current));
                    left = current;
                } else {
                    left = Double.parseDouble(resultText);
                }
                leftBuffer = true;
                break;
            case R.id.sub:
                if (newDigit) break;
                lastOperation = digitText;
                newDigit = true;
                if (leftBuffer) {
                    double current = left - Double.parseDouble(resultText);
                    result.setText(Double.toString(current));
                    left = current;
                } else {
                    left = Double.parseDouble(resultText);
                }
                leftBuffer = true;
                break;
            case R.id.mul:
                if (newDigit) break;
                lastOperation = digitText;
                newDigit = true;
                if (leftBuffer) {
                    double current = Double.parseDouble(resultText) * left;
                    result.setText(Double.toString(current));
                    left = current;
                } else {
                    left = Double.parseDouble(resultText);
                }
                leftBuffer = true;
                break;
            case R.id.div:
                if (newDigit) break;
                lastOperation = digitText;
                newDigit = true;
                if (leftBuffer) {
                    double current = left / Double.parseDouble(resultText);
                    result.setText(Double.toString(current));
                    left = current;
                } else {
                    left = Double.parseDouble(resultText);
                }
                leftBuffer = true;
                break;
            case R.id.clear:
                left = 0;
                lastOperation = "none";
                leftBuffer = false;
                newDigit = true;
                result.setText("0");
                break;
            case R.id.eqv:
                switch (lastOperation) {
                    case "+":
                        if (leftBuffer && !newDigit) {
                            double current = Double.parseDouble(resultText) + left;
                            left = current;
                            leftBuffer = true;
                            result.setText(Double.toString(current));
                            newDigit = true;
                        }
                        break;
                    case "-":
                        if (leftBuffer && !newDigit) {
                            double current = left - Double.parseDouble(resultText);
                            left = current;
                            leftBuffer = true;
                            result.setText(Double.toString(current));
                            newDigit = true;
                        }
                        break;
                    case "*":
                        if (leftBuffer && !newDigit) {
                            double current = Double.parseDouble(resultText) * left;
                            left = current;
                            leftBuffer = true;
                            result.setText(Double.toString(current));
                            newDigit = true;
                        }
                        break;
                    case "/":
                        if (leftBuffer && !newDigit) {
                            double current = left / Double.parseDouble(resultText);
                            left = current;
                            leftBuffer = true;
                            result.setText(Double.toString(current));
                            newDigit = true;
                        }
                        break;
                    case "=":

                        break;

                }
                lastOperation = digitText;
                break;
            default:
                if (newDigit) {
                    newDigit = false;
                    result.setText(digitText);
                } else
                    result.setText(Double.toString(Double.parseDouble(resultText) * 10 + Double.parseDouble(digitText)));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView res = (TextView) findViewById(R.id.result);
        outState.putDouble("left", left);
        outState.putString("result", res.getText().toString());
        outState.putDouble("last", last);
        outState.putString("lastOperation", lastOperation);
        outState.putBoolean("leftBuffer", leftBuffer);
        outState.putBoolean("newDigit", newDigit);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView result = (TextView) findViewById(R.id.result);
        left = savedInstanceState.getDouble("left");
        result.setText(savedInstanceState.getString("result"));
        last = savedInstanceState.getDouble("last");
        lastOperation = savedInstanceState.getString("lastOperation");
        leftBuffer = savedInstanceState.getBoolean("leftBuffer");
        newDigit = savedInstanceState.getBoolean("newDigit");
    }
}
