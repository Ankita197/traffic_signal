package com.ankita.trafficsignal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ConfigurationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerDuration,spinnerRotation,spinnerAmbTimeDuration;
    private String timeduration,ambDuration,rotation;
    private Button btnSet;
    private String[] timeDuration={"5","25","50","100","120"},
            ambTimeDuration={"clockWise","antiClockWise","left-right-top-bottom"},
            signalRotation={"10","50","100","200","300"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        init();
        setArrayAdapter(timeDuration,spinnerDuration);
        setArrayAdapter(signalRotation,spinnerRotation);
        setArrayAdapter(ambTimeDuration,spinnerAmbTimeDuration);
        setSpinnerListener();
        btnSet.setOnClickListener(new View.OnClickListener() {

            //set all the configured value and give it to the main activity
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ConfigurationActivity.this,MainActivity.class);
                i.putExtra("duration",timeduration);
                i.putExtra("ambduration",ambDuration);
                i.putExtra("rotation",rotation);
                setResult(1,i);

            }
        });

    }

    private void setSpinnerListener() {
        spinnerDuration.setOnItemSelectedListener(this);
        spinnerRotation.setOnItemSelectedListener(this);
        spinnerAmbTimeDuration.setOnItemSelectedListener(this);
    }

    //initialize views.
    private void init() {
        spinnerDuration=findViewById(R.id.spinnerDuration);
        spinnerRotation=findViewById(R.id.spinnerRotation);
        spinnerAmbTimeDuration=findViewById(R.id.spinnerAmbTimeDuration);
        btnSet=findViewById(R.id.btnSet);
    }

    //set adapter for different spinner.
    private void setArrayAdapter(String[] timeDuration, Spinner spinnerDuration) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, timeDuration);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerDuration.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinnerDuration:
                switch (position){
                    case 0:
                        timeduration="5";
                        break;
                    case 1:
                        timeduration="25";
                        break;
                    case 2:
                        timeduration="50";
                        break;
                }
                break;
            case R.id.spinnerRotation:
                switch (position){
                    case 0:
                        rotation="clkWise";
                        break;
                    case 1:
                        rotation="antiClkWise";
                        break;
                    case 2:
                        rotation="U-D-T-B";
                        break;
                }
                break;
            case R.id.spinnerAmbTimeDuration:
                switch (position){
                    case 0:
                        ambDuration="10";
                        break;
                    case 1:
                        ambDuration="50";
                        break;
                    case 2:
                        ambDuration="100";
                        break;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
