package com.example.eyhackathon.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.eyhackathon.GlobalVariablesClass;
import com.example.eyhackathon.R;

import java.util.LinkedList;

//import com.example.eyhackathon.Dashboard;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag4Courses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag4Courses extends Fragment implements View.OnClickListener {

    Button submitButton;
    ImageView checkBox;
    public StringBuilder stringBuilder;
    public LinkedList<Integer> courseCodesList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static TextView t;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag4Courses() {
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
    public static Frag4Courses newInstance(String param1, String param2) {
        Frag4Courses fragment = new Frag4Courses();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
//        args.putIntegerArrayList("arrayList",GlobalVariablesClass.courseCodesList);
        fragment.setArguments(args);
        return fragment;
    }



    //DO ALL CUSTOM CODES HERE

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_frag4_courses,container,false);
        //SET ALL CUSTOM CODES HERE
        checkBox = (ImageView) view.findViewById(R.id.checkBox);

//        if(getArguments()!=null){
//            ArrayList<Integer> a = getArguments().getIntegerArrayList("arrayList");
//            if(a!=null){
//                if(a.contains(4)){
//                    int color = Color.parseColor("#228B22");
//                    checkBox.setColorFilter(color);
//                    Toast.makeText(view.getContext(),"Did work",Toast.LENGTH_SHORT).show();
//                }}
//            else{
//                Toast.makeText(view.getContext(),"Did NOT work",Toast.LENGTH_SHORT).show();
//            }
//        }
//        else {
//            Toast.makeText(view.getContext(),"ARG is null work",Toast.LENGTH_SHORT).show();
//        }

        if(GlobalVariablesClass.courseCodesList.contains(4)){
            int color = Color.parseColor("#228B22");
            checkBox.setColorFilter(color);
        }













        submitButton = (Button) view.findViewById(R.id.doTheTask);
        t = (TextView) view.findViewById(R.id.circularTextView);

        submitButton.setOnClickListener(this);


//        t.setText("7");     //CHANGE COLOR ACC TO SCORE HERE
        return view;
    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent( getActivity()  ,Dashboard.class);
//        startActivity(intent);
    }





}