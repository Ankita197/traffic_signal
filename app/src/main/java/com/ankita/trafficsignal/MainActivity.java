package com.ankita.trafficsignal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private ImageView ivSetting;
    private GridLayout gridTrafficSignal;
    private Button btnA,btnB,btnC,btnD;
    private String duration;
    private int time,i=0;
    private View view;
    private boolean[] status={true,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ConfigurationActivity.class);
                startActivityForResult(i,1);
            }
        });
        changeSignal();
        if(duration!=null){
            time=Integer.parseInt(duration);
            Log.d("@@@@",time+" ");
        }

    }

    private void changeSignal() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
               if ( i< gridTrafficSignal.getChildCount()){
                    view=gridTrafficSignal.getChildAt(i);
                   if(view.getId()==R.id.btnA){
                       view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                       i++;
                       changeSignal();
                   }
                   else if(view.getId()==R.id.btnB){
                       checkStatusAndSetBg();
                       status[1]=true;
                       view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                       i++;
                       changeSignal();
                   }
                   else if(view.getId()==R.id.btnC){
                       checkStatusAndSetBg();
                       status[2]=true;
                       view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                       i++;
                       changeSignal();
                   }
                   else if(view.getId()==R.id.btnD){
                       checkStatusAndSetBg();
                       status[3]=true;
                       view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                       i++;
                       changeSignal();
                   }
                   else if(view.getId()==R.id.btnAmbTop){

                       view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                       i++;
                       changeSignal();
                   }
                   else {
                       status[0]=true;
                       i++;
                       changeSignal();
                   }

               }
            }
        }, 5000);
    }

    private void checkStatusAndSetBg() {
        if(status[0]==true){
            status[0]=false;
            btnA.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if(status[1]==true){
            status[1]=false;
            btnB.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if(status[2]==true){
            status[2]=false;
            btnC.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if(status[3]==true){
            status[3]=false;
            btnD.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    private void init() {
        ivSetting=findViewById(R.id.ivSetting);
        gridTrafficSignal=findViewById(R.id.gridSignal);
        btnA=findViewById(R.id.btnA);
        btnB=findViewById(R.id.btnB);
        btnC=findViewById(R.id.btnC);
        btnD=findViewById(R.id.btnD);
        view=gridTrafficSignal.getChildAt(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            duration=data.getStringExtra("duration");
        }
    }
}
