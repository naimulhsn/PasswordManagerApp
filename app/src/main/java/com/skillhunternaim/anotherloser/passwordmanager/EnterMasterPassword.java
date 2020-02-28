package com.skillhunternaim.anotherloser.passwordmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class EnterMasterPassword extends AppCompatActivity {
    SharedPreferences sharedPref;
    TextInputLayout lay;
    TextInputEditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_master_password);
        lay=findViewById(R.id.enter_pass_layout);
        txt=findViewById(R.id.enter_pass);

        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lay.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void enter(View view){
        lay.setError(null);
        String pass=txt.getText().toString().trim();
        sharedPref=getSharedPreferences("user",MODE_PRIVATE);
        String spass=sharedPref.getString("password","");
        if(pass.equals(spass)){
            Intent intent=new Intent(this,AllPasswords.class);
            startActivity(intent);
            finish();
        }else {
            lay.setError("Incorrect Password!!!");
        }
    }
}
