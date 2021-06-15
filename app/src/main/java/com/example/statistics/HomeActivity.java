package com.example.statistics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private String operation_type;
    public int seconds = 20;

    public  int index = 0;
    public String[] file = {"MODE\nMode is the most repeated number in a given sequence of number! If most repeated number in a distribution is more than one number, then the mode is 0", "MAXIMUM\nIf you arrange the distribution in ascending order, The maximum is always the last number",
            "MINIMUM\nIf you arrange the distribution in ascending order, the minimum is always the first number", "MEAN\n Mean the mean of a distribution is the sum of all the numbers in the distribution divided by the count of the distribution",
            "MEDIAN\nMedian is the middle number in an odd distribution, and two divided by the sum of the two middle numbers in an even distribution", "RANGE\nRange is difference between the maximum number and the minimum number",
            "COUNT\nCount is the number of numbers in a given distribution", "SUM\nSum is the addition of all the characters in a distribution", "STATISTICS\nStatistics is the practice of analyzing numbers, to find a threshold of the numbers. The first step to simple statistic is arrange the numbers in ascending or descending orders."};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // to display an image from the app starts to run


        // populate the spinner
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // get the click item
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Button button =  findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        TextView message = findViewById(R.id.text5);

        operation_type = (String) parent.getItemAtPosition(position);
        button.setText(operation_type);

        // declare the statistic class
        Statistics stats = new Statistics();

        // set a timer for 20 seconds
        Timer time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {



            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                detailStatistics();
                            }
                        });

                        button.setOnClickListener(new View.OnClickListener() {
                            TextView string_numbers = (TextView) findViewById(R.id.list_num);
                            EditText result = (EditText)  findViewById(R.id.result);


                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onClick(View v) {
                                result.setText(string_numbers.getText());
                                stats.stringNumbers(String.valueOf(string_numbers.getText()));

                                result.setText(stats.getResult(operation_type));
                                index = stats.getMessage(operation_type);
                                message.setText(file[index]);
                                seconds = 20;
                            }
                        });

                        seconds -= 1;

                        if (seconds <= 0){


                            if (index < file.length){
                                index += 1;
                            }

                            if (index >= file.length){
                                index = 0;
                            }

                            message.setText(file[index]);
                            seconds = 20;
                        }
                    }
                });
            }
        }, 0, 1000);






    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void detailStatistics(){
        Intent intent = new Intent(HomeActivity.this, FinalActivity.class);
        startActivity(intent);

    }

}