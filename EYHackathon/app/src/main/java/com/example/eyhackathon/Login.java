package com.example.eyhackathon;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    String email;
    String password;
    Button submitLogin;
    Button goToRegistration;
    EditText loginEmail;
    EditText loginPassword;
    String userID;

//    FirebaseDatabase db;
//    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        GlobalVariablesClass.userID=null;
        GlobalVariablesClass.userBuddy=null;
        GlobalVariablesClass.chats=null;
        GlobalVariablesClass.courseCodesList=null;
        GlobalVariablesClass.visitedTask1=false;
        GlobalVariablesClass.visitedTask2=false;
        GlobalVariablesClass.pointsGained=0;


        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword= (EditText) findViewById(R.id.loginPassword);
        submitLogin = (Button) findViewById(R.id.submitLogin);
        goToRegistration = (Button) findViewById(R.id.goToRegistration);
        goToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Registration_Page.class);
                startActivity(intent);
                finish();
            }
        });

        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = loginEmail.getText().toString();
                password = loginPassword.getText().toString();
                userID = createUserID(email);


                //THIS HERE I AM GETTING STRING EMAIL AND PASSWORD.
                //EMAIL and PASSWORDS are String input variables. Do whatever with them

                //THIS IS A TEST TO SEE IF THEY WORKED OR NOT
                //CHECKING TO VERIFY IF USER EXISTS IN DB OR NOT

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("userID").equalTo(userID);


                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {
                                String passwordFromDB = snapshot.child(userID).child("password").getValue(String.class);
                                if (passwordFromDB.equals(password)) {
                                    Intent intent = new Intent(Login.this, Dashboard.class);

                                    GlobalVariablesClass.userID = userID;
//                                    Toast.makeText(Login.this, GlobalVariablesClass.userID, Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Login.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(Login.this, "Email Does Not Exist", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });








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