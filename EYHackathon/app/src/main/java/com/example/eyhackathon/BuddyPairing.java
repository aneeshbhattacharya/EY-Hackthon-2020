package com.example.eyhackathon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.eyhackathon.Adapters.MessageAdapter;
import com.example.eyhackathon.Model.ChatModel;
import com.example.eyhackathon.Model.UsersRegistration;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BuddyPairing extends AppCompatActivity {


    FirebaseDatabase root;
    DatabaseReference reference;
    public String s;
    public ArrayList<UsersRegistration> allUsers;
    public ArrayList<ChatModel> allChats;
    Button submit;
    public UsersRegistration mainUser;
    TextView name;
    TextView email;
    TextView phoneNumber;
    DatabaseReference ref;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddy_pairing);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        phoneNumber = (TextView) findViewById(R.id.phone);





        allUsers = new ArrayList<UsersRegistration>();
        allUsers.clear();


        setListValues();

        //DO NOT WRITE CODE HERE, WRITE ALL CODE IN SETLISTVALUES METHOD








    }
    public void setListValues(){

        root = FirebaseDatabase.getInstance();
        reference = root.getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot myItem:snapshot.getChildren()){
                    UsersRegistration u = myItem.getValue(UsersRegistration.class);

                    assert u != null;
                    if(!u.getUserID().equals(GlobalVariablesClass.userID)) {
                        allUsers.add(u);
                    }
                    else{
                        mainUser = u;
                    }

                }

                //  WRITE REST OF THE CODE TO BE EXECUTED LINE BY LINE


//
                if(!mainUser.isBuddyStatus()){
                setPairedWith();
                    ref = FirebaseDatabase.getInstance().getReference("Users");
                    Query query = ref.orderByChild("userID").equalTo(GlobalVariablesClass.userBuddy);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){

                                UsersRegistration u = snapshot.child(GlobalVariablesClass.userBuddy).getValue(UsersRegistration.class);
                                assert u!=null;
                                name.setText(u.getName());
                                email.setText(u.getEmail());
                                phoneNumber.setText(u.getPhoneNumber());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    GlobalVariablesClass.userBuddy = mainUser.getBuddyID();
                    Toast.makeText(getApplicationContext(),"Buddy is "+GlobalVariablesClass.userBuddy,Toast.LENGTH_LONG).show();
                    ref = FirebaseDatabase.getInstance().getReference("Users");
                    Query query = ref.orderByChild("userID").equalTo(GlobalVariablesClass.userBuddy);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){

                                UsersRegistration u = snapshot.child(GlobalVariablesClass.userBuddy).getValue(UsersRegistration.class);
                                assert u!=null;
                                name.setText(u.getName());
                                email.setText(u.getEmail());
                                phoneNumber.setText(u.getPhoneNumber());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                submit = (Button) findViewById(R.id.clickOnMe);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(BuddyPairing.this, MessageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });




            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

    public void setPairedWith(){



        for(int i=0;i<allUsers.size();i++){
            UsersRegistration obj = allUsers.get(i);
            boolean status=true;
            if(!obj.isBuddyStatus()){
                GlobalVariablesClass.userBuddy = obj.getUserID();
                String userID = obj.getUserID();
                root = FirebaseDatabase.getInstance();
                reference = root.getReference("Users");
                reference.child(GlobalVariablesClass.userID).child("buddyStatus").setValue(true);
                reference.child(GlobalVariablesClass.userID).child("buddyID").setValue(GlobalVariablesClass.userBuddy);
                reference.child(GlobalVariablesClass.userBuddy).child("buddyID").setValue(GlobalVariablesClass.userID);
                reference.child(GlobalVariablesClass.userBuddy).child("buddyStatus").setValue(true);
                Toast.makeText(getApplicationContext(),"Buddy set to "+GlobalVariablesClass.userBuddy,Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BuddyPairing.this,Dashboard.class);
        startActivity(intent);
        finish();
    }


}