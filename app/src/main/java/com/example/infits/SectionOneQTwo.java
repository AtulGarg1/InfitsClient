package com.example.infits;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectionOneQTwo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionOneQTwo extends Fragment {

    Button nextbtn;
    TextView backbtn, nametv;
    EditText eTextName;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SectionOneQTwo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionOneQTwo.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionOneQTwo newInstance(String param1, String param2) {
        SectionOneQTwo fragment = new SectionOneQTwo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section_one_q_two, container, false);

        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        eTextName = view.findViewById(R.id.eTextName);

        nametv = view.findViewById(R.id.textView80);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = eTextName.getText().toString();
                //Toast.makeText(getContext(),user_name, Toast.LENGTH_SHORT).show();

                DataSectionOne.name = user_name;
                DataSectionOne.s1q2 = nametv.getText().toString();
                if(user_name.equals("")|| user_name.equals(" "))
                    Toast.makeText(getContext(),"Add your name",Toast.LENGTH_SHORT).show();
                else{
                    ConsultationFragment.psection1+=1;
                Navigation.findNavController(v).navigate(R.id.action_sectionOneQTwo_to_sectionOneQThree);
            }}
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConsultationFragment.psection1>0)
                    ConsultationFragment.psection1-=1;
                Navigation.findNavController(v).navigate(R.id.action_sectionOneQTwo_to_sectionOneQOne);
            }
        });

        return view;
    }
}