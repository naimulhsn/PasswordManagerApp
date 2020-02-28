package com.skillhunternaim.anotherloser.passwordmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    TextView wel;
    SharedPreferences sharedPref;
    boolean f=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref=getSharedPreferences("user",MODE_PRIVATE);

        wel=findViewById(R.id.welcome);
        Animation fadein= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        wel.setAnimation(fadein);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                    if(sharedPref.contains("password")){
                        Intent i=new Intent(getApplicationContext(),EnterMasterPassword.class);
                        startActivity(i);
                        finish();
                    }else {
                        Intent i=new Intent(getApplicationContext(),SetMasterPassword.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
        thread.start();
    }
}
