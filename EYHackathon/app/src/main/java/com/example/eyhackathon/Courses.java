package com.example.eyhackathon;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.example.eyhackathon.Model.CoursesModel;
import com.example.eyhackathon.ui.main.SectionsPagerAdapterCourses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class Courses extends AppCompatActivity {

    public DatabaseReference ref;
    public StringBuilder stringBuilder;
    public ArrayList<Integer> courseCodesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        SectionsPagerAdapterCourses sectionsPagerAdapterCourses = new SectionsPagerAdapterCourses(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapterCourses);
//        getCourses();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Courses.this,Dashboard.class);
        startActivity(intent);
        finish();
    }
    public void getCourses(){
        ref = FirebaseDatabase.getInstance().getReference("Course");
        Query query = ref.orderByChild("userID").equalTo(GlobalVariablesClass.userID);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    GlobalVariablesClass.courseCodes = snapshot.child(GlobalVariablesClass.userID).child("courseCodes").getValue(String.class);
//                    Toast.makeText(getApplicationContext(),GlobalVariablesClass.courseCodes,Toast.LENGTH_SHORT).show();
                    convertToList(GlobalVariablesClass.courseCodes);






                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void convertToList(String str){

        stringBuilder = new StringBuilder();
        courseCodesList = new ArrayList<>();
        for(int i=0;i<str.length()-1;i++){
            char c = str.charAt(i);
            char ch = str.charAt(i+1);
            if(Character.isDigit(c)){
                stringBuilder.append(c);
                if(!Character.isDigit(ch)){

                    courseCodesList.add(Integer.parseInt(stringBuilder.toString()));
                    stringBuilder.setLength(0);
                }
            }
        }
        GlobalVariablesClass.courseCodesList = courseCodesList;
    }






}




