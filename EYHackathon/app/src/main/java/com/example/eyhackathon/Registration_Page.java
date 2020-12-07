package com.example.eyhackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eyhackathon.Model.UsersRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_Page extends AppCompatActivity {

    Button backToLogin;
    Button submitRegistration;
    String email;
    String password;
    String fullName;
    String phoneNumber;
    EditText registrationFullName;
    EditText registrationEmail;
    EditText registrationPassword;
    EditText registrationPhone;
    String userID;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration__page);






        registrationFullName = (EditText) findViewById(R.id.registrationFullName);
        registrationEmail = (EditText) findViewById(R.id.registrationEmail);
        registrationPassword= (EditText) findViewById(R.id.registrationPassword);
        registrationPhone = (EditText) findViewById(R.id.registrationPhone);
        backToLogin = (Button) findViewById(R.id.backToLogin);
        submitRegistration = (Button) findViewById(R.id.submitRegistration);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration_Page.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        submitRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = registrationEmail.getText().toString();
                password = registrationPassword.getText().toString();
                fullName = registrationFullName.getText().toString();
                phoneNumber = registrationPhone.getText().toString();

                userID = createUserID(email);

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Users");




                UsersRegistration u = new UsersRegistration(fullName,email,phoneNumber,password,userID);
                databaseReference.child(userID).setValue(u);
                Toast.makeText(Registration_Page.this,"User has been registered",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registration_Page.this,Login.class);
                startActivity(intent);





            }
        });
    }
    static String createUserID(String s){
        String userID = "";
        //Creating custom ID
        for(int i=0;i<s.length();i++){
            char x = s.charAt(i);
            if(x=='@'){
                break;
            }
            else{
                userID+=Character.toString(x);
            }
        }
        return userID;
    }


}