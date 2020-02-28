package com.skillhunternaim.anotherloser.passwordmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

public class SetMasterPassword extends AppCompatActivity {
    SharedPreferences sharedPref;
    TextInputEditText pass_e, confirm_pass_e;
    TextInputLayout set_pass_layout,conf_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_master_password);
        pass_e=findViewById(R.id.set_pass);
        confirm_pass_e=findViewById(R.id.set_pass_confirm);

        set_pass_layout=findViewById(R.id.set_pass_layout);
        conf_layout=findViewById(R.id.set_pass_confirm_layout);

        sharedPref=getSharedPreferences("user",MODE_PRIVATE);


        pass_e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    set_pass_layout.setError(null);
                    conf_layout.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    public void save_password(View view){
        String pass=pass_e.getText().toString().trim();
        String conf_pass=confirm_pass_e.getText().toString().trim();
        if(pass.length()<=4){
            set_pass_layout.setError("Password must contain at least 5 character");
        }else if (!pass.equals(conf_pass)){
            conf_layout.setError("Passwords Don't Match!!!");
        }else {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("password",pass);
            editor.apply();
            Intent intent=new Intent(this,AllPasswords.class);
            startActivity(intent);
            finish();
        }
    }
}
