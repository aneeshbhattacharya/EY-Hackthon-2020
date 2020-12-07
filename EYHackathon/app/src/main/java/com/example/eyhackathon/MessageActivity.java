package com.example.eyhackathon;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.eyhackathon.Adapters.MessageAdapter;
import com.example.eyhackathon.Model.ChatModel;
import com.example.eyhackathon.Model.CoursesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText msg_editText;
    ImageButton sendBtn;
    Toolbar toolbar;
    public String courseCodes;

    MessageAdapter messageAdapter;
    ArrayList<ChatModel> mChat;
    DatabaseReference ref;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sendBtn = (ImageButton) findViewById(R.id.btn_send);
        msg_editText = (EditText) findViewById(R.id.text_input);

        mChat = new ArrayList<ChatModel>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);



        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = msg_editText.getText().toString();
                if(!msg.equals("")){
                    sendMessage(msg);
                }
                msg_editText.setText("");

            }
        });





        readMessages();


      }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MessageActivity.this,BuddyPairing.class);
        startActivity(intent);
        finish();

    }

    public void sendMessage(String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        ChatModel c = new ChatModel(GlobalVariablesClass.userID,GlobalVariablesClass.userBuddy,message);
        reference.child("Chats").push().setValue(c);


        String keyword = "course";
        String keyword2 = "courses";
        boolean found = Arrays.asList(message.split(" ")).contains(keyword);
        boolean found2 = Arrays.asList(message.split(" ")).contains(keyword2);
        if(found||found2){



            GlobalVariablesClass.courseCodes=null;
            buildNumbers(message+" ");
            CoursesModel cc = new CoursesModel(GlobalVariablesClass.courseCodes,GlobalVariablesClass.userBuddy);
            reference.child("Course").child(GlobalVariablesClass.userBuddy).setValue(cc);

        }
    }

    public void buildNumbers(String str){
        StringBuilder stringBuilder = new StringBuilder();
        courseCodes = "";
        for(int i=0;i<str.length()-1;i++){
            char c = str.charAt(i);
            char ch = str.charAt(i+1);
            if(Character.isDigit(c)){
                stringBuilder.append(c);
                if(!Character.isDigit(ch)){
                    courseCodes+=stringBuilder.toString()+" ";
//                    courseCodes.add(Integer.parseInt(stringBuilder.toString()));
                    stringBuilder.setLength(0);
                }
            }
    }
        GlobalVariablesClass.courseCodes = courseCodes;


    }



    public void readMessages(){


        ref = FirebaseDatabase.getInstance().getReference("Chats");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mChat.clear();
                for(DataSnapshot myItem: snapshot.getChildren()){
                    ChatModel chatModel = myItem.getValue(ChatModel.class);
                    assert chatModel!=null;

                    if(chatModel.getReceiverID().equals(GlobalVariablesClass.userID)&&chatModel.getSenderID().equals(GlobalVariablesClass.userBuddy)||
                            chatModel.getReceiverID().equals(GlobalVariablesClass.userBuddy)&&chatModel.getSenderID().equals(GlobalVariablesClass.userID)){

                        mChat.add(chatModel);





                    }
                    messageAdapter = new MessageAdapter(MessageActivity.this,mChat);
                    recyclerView.setAdapter(messageAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}