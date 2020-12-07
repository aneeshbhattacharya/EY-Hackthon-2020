package com.example.eyhackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.eyhackathon.Model.ChatModel;
import com.example.eyhackathon.Model.UsersRegistration;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    DatabaseReference ref;
    StringBuilder stringBuilder;
    public ArrayList<Integer> courseCodesList;
    private ProgressBar progressbar;
    private TextView progressText;
    int i=0;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView userName;
    Button takeToLeaderBoard;
    DatabaseReference refPoints;




    public UsersRegistration mainUser;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(GlobalVariablesClass.userID);
        takeToLeaderBoard = (Button) findViewById(R.id.takeToLeaderBoard);


        progressbar=findViewById(R.id.progress_bar);
        progressText=findViewById(R.id.progressText);

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nav);
        toolbar=findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        //UPDATE AND INCREMENT PROGRESS BAR AND TEXT AFTER COMPLETION OF EACH TASK
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i<=GlobalVariablesClass.pointsGained) {


                        progressText.setText("" + i);
                        progressbar.setProgress(i);
                        i++;
                        handler.postDelayed(this, 50);}


                else{
                    handler.removeCallbacks(this);
                }

            }
        }, 50);


        takeToLeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pm =Dashboard.this.getPackageManager().getLaunchIntentForPackage("com.ErnstAndYoung.Leaderboard");
                if(pm!=null){
                    startActivity(pm);}
                else {
                    Toast.makeText(Dashboard.this,"Unity APK not found",Toast.LENGTH_SHORT).show();
                }
            }
        });

        refPoints = FirebaseDatabase.getInstance().getReference("Users");
        Query q = refPoints.orderByChild("userID").equalTo(GlobalVariablesClass.userID);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    GlobalVariablesClass.pointsGained = snapshot.child(GlobalVariablesClass.userID).child("points").getValue(Integer.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.goToCourses:

                if(GlobalVariablesClass.courseCodesList==null){

                ref = FirebaseDatabase.getInstance().getReference("Course");
                Query query = ref.orderByChild("userID").equalTo(GlobalVariablesClass.userID);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){

                            GlobalVariablesClass.courseCodes = snapshot.child(GlobalVariablesClass.userID).child("courseCodes").getValue(String.class);
//                    Toast.makeText(getApplicationContext(),GlobalVariablesClass.courseCodes,Toast.LENGTH_SHORT).show();
                            convertToList(GlobalVariablesClass.courseCodes);
                            Intent intent1 = new Intent(Dashboard.this,Courses.class);
                            startActivity(intent1);
                            finish();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });}
                else{
                    Intent intent1 = new Intent(Dashboard.this,Courses.class);
                    startActivity(intent1);
                    finish();
                }


                break;


            case R.id.goToBuddy:
                    Intent intent = new Intent(Dashboard.this,BuddyPairing.class);
                    startActivity(intent);
                    finish();
                break;


            case R.id.goToTasks:
                if(!GlobalVariablesClass.visitedTask1 || !GlobalVariablesClass.visitedTask2){

                    ref = FirebaseDatabase.getInstance().getReference("Users");
                    Query query = ref.orderByChild("userID").equalTo(GlobalVariablesClass.userID);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){

                                GlobalVariablesClass.visitedTask1 = snapshot.child(GlobalVariablesClass.userID).child("doneTask1").getValue(Boolean.class);
                                GlobalVariablesClass.visitedTask2 = snapshot.child(GlobalVariablesClass.userID).child("doneTask2").getValue(Boolean.class);
//                    Toast.makeText(getApplicationContext(),Boolean.toString(GlobalVariablesClass.visitedTask1),Toast.LENGTH_SHORT).show();

                                Intent intent2 = new Intent(Dashboard.this,Tasks.class);
                                startActivity(intent2);
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });}
                else{
                    Intent intent2 = new Intent(Dashboard.this,Tasks.class);
                    startActivity(intent2);
                    finish();
                }
                break;

            case R.id.goToHome:
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;

            case R.id.goToLogout:
                Intent intent3 = new Intent(Dashboard.this,Login.class);
                startActivity(intent3);
                Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.goToLeaderboard:
                Intent pmm =Dashboard.this.getPackageManager().getLaunchIntentForPackage("com.ErnstAndYoung.Leaderboard");
                if(pmm!=null){
                    startActivity(pmm);}
                else {
                    Toast.makeText(Dashboard.this,"Unity APK not found",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.goToFloorMap:
                Intent pmmm = Dashboard.this.getPackageManager().getLaunchIntentForPackage("com.ErnstAndYoung.FloorMap");
                if(pmmm!=null){
                    startActivity(pmmm);
                }
                else {
                    Toast.makeText(Dashboard.this,"Unity APK not found",Toast.LENGTH_SHORT).show();
                }













        }


        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

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
