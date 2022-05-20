package com.example.infits;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section2Q8#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section2Q8 extends Fragment {

    Button nextbtn;
    TextView backbtn;
    CheckBox dia,hyperthy,hypothy,hyperten,pcod,fattyl;
    EditText oth;
    ArrayList<String> diagnosed;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section2Q8() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section2Q8.
     */
    // TODO: Rename and change types and number of parameters
    public static Section2Q8 newInstance(String param1, String param2) {
        Section2Q8 fragment = new Section2Q8();
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
        View view = inflater.inflate(R.layout.fragment_section2_q8, container, false);

        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        dia = view.findViewById(R.id.dia);
        hyperthy = view.findViewById(R.id.hyperthy);
        hypothy = view.findViewById(R.id.hypothy);
        hyperten = view.findViewById(R.id.hyperten);
        pcod = view.findViewById(R.id.pcod);
        fattyl = view.findViewById(R.id.fattyl);
        oth = view.findViewById(R.id.oth);

        diagnosed = new ArrayList<>();

        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dia.isChecked())
                    diagnosed.add("Diabetes");
                else
                    diagnosed.remove("Diabetes");
            }
        });

        hyperthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperthy.isChecked())
                    diagnosed.add("Hyperthyroidism");
                else
                    diagnosed.remove("Hyperthyroidism");
            }
        });

        hypothy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hypothy.isChecked())
                    diagnosed.add("Hypothyroidism");
                else
                    diagnosed.remove("Hypothyroidism");
            }
        });

        hyperten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperten.isChecked())
                    diagnosed.add("Hypertension");
                else
                    diagnosed.remove("Hypertension");
            }
        });

        pcod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pcod.isChecked())
                    diagnosed.add("PCOD/PCOS");
                else
                    diagnosed.remove("PCOD/PCOS");
            }
        });

        fattyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fattyl.isChecked())
                    diagnosed.add("Fatty liver");
                else
                    diagnosed.remove("Fatty liver");
            }
        });

        String other = oth.getText().toString();

        if(other!=null)
            diagnosed.add(other);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataSectionTwo.familyHistory = diagnosed;

                Navigation.findNavController(v).navigate(R.id.action_section2Q8_to_consultationFragment);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_section2Q8_to_section2Q7);
            }
        });

        return view;
    }
}