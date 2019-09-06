package com.example.student.dbhandling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Database.DBHelper;

public class MainActivity extends AppCompatActivity {

    TextView username , password;
    DBHelper dbHelper;
    EditText txtUName , txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.txtUName);
        password = findViewById(R.id.txtPass);

        dbHelper = new DBHelper(this);
    }

    public void addIn(View view){
        String uname = username.getText().toString();
        String pword = password.getText().toString();

        long rownum = dbHelper.addInfo(uname,pword);

        if (rownum>0){
            Toast.makeText(this,"User Created Succesfully",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }


    public void getAllUsers(View view){
        List resultList = dbHelper.readAllInfo();

        for(int i = 0;i<resultList.size();i++){
            Log.i("getAllUsers()", resultList.get(i).toString());
        }
    }


    public void updateIn(View view){
        boolean result = dbHelper.updatePassword(txtUName.getText().toString(), txtPass.getText().toString());

        if (result == true){
            Toast.makeText(getApplicationContext(),"Update Succesfully",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"Error in Update",Toast.LENGTH_LONG).show();
        }
    }


    public void deleteIn(View view){
        int result = dbHelper.deleteData(txtUName.getText().toString());

        if (result == 1){
            Toast.makeText(getApplicationContext(),"Delete Succesfully",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"Error in Delete",Toast.LENGTH_LONG).show();
        }
    }
}
