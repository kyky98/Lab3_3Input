package com.example.taruc.lab3_3input;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale,
            radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAge = findViewById(R.id.spinnerAge);
        radioGroupGender = findViewById(R.id.rgGender);
        radioButtonMale = findViewById(R.id.rbMale);
        radioButtonFemale = findViewById(R.id.rbFemale);
        checkBoxSmoker = findViewById(R.id.chkSmoker);
        textViewPremium = findViewById(R.id.textViewPremium);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.age_group, android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapter);
        spinnerAge.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Item selected" + position, Toast.LENGTH_SHORT).show();
        checkBoxSmoker.setVisibility(View.INVISIBLE);
        if (position > 2)
            checkBoxSmoker.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium(View view) {
        int position;
        int premium = 0;
        int gender = radioGroupGender.getCheckedRadioButtonId();
        position = spinnerAge.getSelectedItemPosition();
        if (position == 0) {
            premium = 50;
        } else if (position == 1) {
            premium = 55;
        } else if (position == 2) {
            premium = 60;
        } else if (position == 3) {
            premium = 70;
        } else if (position == 4) {
            premium = 120;
        } else if (position == 5) {
            premium = 160;
        } else if (position == 6) {
            premium = 200;
        } else if (position == 7) {
            premium = 250;
        }

        if (gender == R.id.rbMale) {
            if (position == 2 || position == 5)
                premium += 50;
            else if (position == 3 || position == 4)
                premium += 100;
        }

        if (checkBoxSmoker.isChecked()) {
            if (position == 3)
                premium += 100;
            else if (position == 4 || position == 5)
                premium += 150;
            else if (position == 6 || position == 7)
                premium += 250;
        }

        textViewPremium.setText(premium + "");
    }

    public void reset(View view) {
        spinnerAge.setSelection(0);
        radioGroupGender.clearCheck();
        checkBoxSmoker.setChecked(false);
        textViewPremium.setText("");
    }
}
