package com.example.eyhackathon.ui.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.eyhackathon.Dashboard;
import com.example.eyhackathon.GlobalVariablesClass;
import com.example.eyhackathon.Login;
import com.example.eyhackathon.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag1 extends Fragment implements View.OnClickListener {

    Button submitButton;
    DatabaseReference ref;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static TextView t;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag1.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag1 newInstance(String param1, String param2) {
        Frag1 fragment = new Frag1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//
//
//
//
//        }
//    }


    //DO ALL CUSTOM CODES HERE

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_frag1,container,false);
        //SET ALL CUSTOM CODES HERE

        submitButton = (Button) view.findViewById(R.id.doTheTask);
        t = (TextView) view.findViewById(R.id.circularTextView);

        submitButton.setOnClickListener(this);

        int color = Color.parseColor("#228B22");
//        GradientDrawable gradientDrawable = (GradientDrawable) t.getBackground().mutate();
//        gradientDrawable.setColor(color);



        if(GlobalVariablesClass.visitedTask1){

            t.setTextColor(color);

        }


        return view;
    }

    @Override
    public void onClick(View v) {
        Intent pm = getActivity().getPackageManager().getLaunchIntentForPackage("com.ErnstAndYoung.Leadership");
        if(pm!=null){
        startActivity(pm);
        int color = Color.parseColor("#228B22");
        t.setTextColor(color);
            if(!GlobalVariablesClass.visitedTask1){
                GlobalVariablesClass.pointsGained+=20;
                GlobalVariablesClass.visitedTask1=true;
                ref = FirebaseDatabase.getInstance().getReference("Users");
                ref.child(GlobalVariablesClass.userID).child("doneTask1").setValue(true);
                ref.child(GlobalVariablesClass.userID).child("points").setValue(GlobalVariablesClass.pointsGained);
            }






        }
        else{
            Toast.makeText(getActivity(),"Unity APK not found",Toast.LENGTH_SHORT).show();

        }
    }



}