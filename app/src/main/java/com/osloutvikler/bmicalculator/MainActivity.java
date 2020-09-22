package com.osloutvikler.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private Button calculateButton;
    private RadioButton femaleRadioButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText weightEditText;
    private EditText inchesEditText;
    private RadioButton maleRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtononClickListener();

    }

private void findViews(){
            resultText = findViewById(R.id.text_view_result);
            femaleRadioButton = findViewById(R.id.radio_button_female);
            maleRadioButton = findViewById(R.id.radio_button_male);
            ageEditText = findViewById(R.id.edit_text_age);
            weightEditText =findViewById(R.id.edit_text_weight);
            feetEditText= findViewById(R.id.edit_text_feet);
            inchesEditText = findViewById(R.id.edit_text_inches);
            calculateButton = findViewById(R.id.button_calculate);
        }

    private void setupButtononClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    double bmiResult = calculateBmi();
                    displayResult(bmiResult);
                    //displayGuidance(bmiResult);

                    String ageText = ageEditText.getText().toString();
                    int age = Integer.parseInt(ageText);

                    if (age >= 18) {
                        displayResult(bmiResult);
                    }
                    else {
                        displayGuidance(bmiResult);
                    }


                    //In a listener you need to specify the activity/page to display your toast message
                    //Toast.makeText(MainActivity.this, "Calculating...... BMI", Toast.LENGTH_LONG).show();
                }
             });
    }


    private double calculateBmi(){

        String feetText=feetEditText.getText().toString();
        String weightText=weightEditText.getText().toString();
        String inchesText=inchesEditText.getText().toString();


        int feet =Integer.parseInt(feetText);
        int weight=Integer.parseInt(weightText);
        int inches=Integer.parseInt(inchesText);

        int totalInches = (feet*12)+inches;

        //Converting inches into meters
        double heightInMeters = totalInches*0.0254 ;

        //Formula for Calculating BMI
        return weight/Math.pow(heightInMeters, 2);
    }

    private void displayResult(double bmi){
        DecimalFormat bmidecimalformat = new DecimalFormat("0.00");

        String bmiTextResult = bmidecimalformat.format(bmi);
        resultText.setText(bmiTextResult);
        double underWeightBmi = 18.5;
        double overWeightBmi = 25;

        String fullResultString;

        if(bmi < underWeightBmi){
            fullResultString = bmiTextResult+" You are underweight";
        }

        else if(bmi > overWeightBmi){
            fullResultString = bmiTextResult+" You are overweight";
        }

        else{
            fullResultString=bmiTextResult+" You are healthy";
        }

        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi){

        DecimalFormat bmiDecimalFormat= new DecimalFormat("0.00");
        String bmiTextResult = bmiDecimalFormat.format(bmi);

        String fullResultString;

        if(maleRadioButton.isChecked()){

            fullResultString= bmiTextResult+" - As you are a boy under 18, kindly consult your Doctor for interpretation of your BMI range";
        }
        else if(femaleRadioButton.isChecked()){
            //Display girl guidance
            fullResultString=bmiTextResult+ " - As you are a girl under the age of 18, kindly consult your Doctor for interpretation of your BMI range";
        }

        else{
            //Display general guidance
            fullResultString=bmiTextResult+ " - As you are under the age of 18, kindly consult your Doctor for interpretation of your BMI range";
        }
        resultText.setText(fullResultString);


    }


        /**String alertText="Wow!!! Am getting this Android course";
        Toast.makeText(this, alertText, Toast.LENGTH_LONG).show();**/
    //Converting the int or double into a String
    //String bmiTextResult = String.valueOf(bmi);


    //resultText.setText("Age: "+ ageText+"|"+"weight: "+weightText+"|"+"Feet:"+feetText+"|"+"inches:"+inchesText);

}