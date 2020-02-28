package com.skillhunternaim.anotherloser.passwordmanager;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

public class NewPass extends AppCompatActivity {
    DBHelper dbHelper;
    TextInputEditText titlea,websitea,passworda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);
        titlea=findViewById(R.id.title);
        websitea=findViewById(R.id.website);
        passworda=findViewById(R.id.password);
        dbHelper= new DBHelper(this);
    }
    public void save(View view){
        boolean f=true;
        String title=titlea.getText().toString().trim();
        String website=websitea.getText().toString().trim();
        String password=passworda.getText().toString().trim();
        boolean res=dbHelper.addPass(title,website,password);
        if(res){
            Intent intent=new Intent(this,AllPasswords.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,"Something Went wrong",Toast.LENGTH_LONG).show();
        }
    }
}
