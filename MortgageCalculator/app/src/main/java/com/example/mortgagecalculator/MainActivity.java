package com.example.mortgagecalculator;

// IMPORTS
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // UPDATE FUNCTIONS - Used to update the EMI when text is changed
    public void update(){
        // PRT, need to get String values to test for empty text box
        String p = principal.getText().toString();
        String r = rate.getText().toString();
        String t = term.getText().toString();

        // If any box is empty, we do nothing, otherwise, we create double values (pp, rr, tt (lol))
        // by parsing the string and then do the EMI calculation
        if (!p.matches("") && !r.matches("") && !t.matches("")) {
            double pp = Double.parseDouble(principal.getText().toString());
            double rr = (Double.parseDouble(rate.getText().toString()))/100;
            double tt = Double.parseDouble(term.getText().toString());

            double powt = Math.pow((1 + (rr / 12)), (12 * tt));
            pay = ( ((pp * (rr / 12)) * powt) / (powt - 1) );
            pay = Math.round(pay * 100.00) / 100.00;

            payment.setText("$"+pay);
        }
    }

    // Declarations
    EditText principal;
    EditText rate;
    EditText term;
    double pay;
    TextView payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assigning the views the each variable by ID Lookup
        principal = findViewById(R.id.principal_amount);
        rate = findViewById(R.id.interest_rate);
        term = findViewById(R.id.term);
        payment = findViewById(R.id.payment);

        // TextWatcher for the principal box
        principal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            // Call update() after text has been changed
            @Override
            public void afterTextChanged(Editable s) {
                update();
            }
        });

        // TextWatcher for the rate box
        rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            // Call update() after text has been changed
            @Override
            public void afterTextChanged(Editable s) {
                update();
            }
        });

        // TextWatcher for the term box
        term.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            // Call update() after text has been changed
            @Override
            public void afterTextChanged(Editable s) {
                update();
            }
        });
    }
}