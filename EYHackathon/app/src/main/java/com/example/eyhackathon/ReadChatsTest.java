package com.example.eyhackathon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.eyhackathon.Model.CoursesModel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;


public class ReadChatsTest extends AppCompatActivity {

    public DatabaseReference ref;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chats_test);
//        GlobalVariablesClass.courseCodes = null;

        getCourses();
    }

    public void getCourses(){
        ref = FirebaseDatabase.getInstance().getReference("Course");
        Query query = ref.orderByChild("userID").equalTo(GlobalVariablesClass.userID);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    GlobalVariablesClass.courseCodes = snapshot.child(GlobalVariablesClass.userID).child("courseCodes").getValue(String.class);
                    Toast.makeText(ReadChatsTest.this,GlobalVariablesClass.courseCodes,Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}