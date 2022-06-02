package com.example.infits;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section3Q7#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section3Q7 extends Fragment {

    Button nextbtn;
    TextView backbtn, textView77;
    RadioButton yes,some,occ,no,afterFood,beforeFood;
    String hairfall="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section3Q7() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section3Q7.
     */
    // TODO: Rename and change types and number of parameters
    public static Section3Q7 newInstance(String param1, String param2) {
        Section3Q7 fragment = new Section3Q7();
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
        View view = inflater.inflate(R.layout.fragment_section3_q7, container, false);

        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        yes = view.findViewById(R.id.yes);
        some = view.findViewById(R.id.some);
        no = view.findViewById(R.id.no);
        occ = view.findViewById(R.id.occ);
        afterFood = view.findViewById(R.id.afterFood);
        beforeFood = view.findViewById(R.id.beforeFood);

        textView77 = view.findViewById(R.id.textView77);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                yes.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                hairfall="Yes";
            }
        });

        some.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                some.setBackgroundResource(R.drawable.radiobtn_on);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                some.setTextColor(Color.WHITE);
                yes.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                hairfall="Sometimes";
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                no.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                yes.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                hairfall="No";
            }
        });

        occ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                occ.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                occ.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                yes.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                hairfall="Occasionally";
            }
        });

        afterFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afterFood.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                beforeFood.setBackgroundResource(R.drawable.radiobtn_off);

                afterFood.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                yes.setTextColor(Color.BLACK);
                beforeFood.setTextColor(Color.BLACK);

                hairfall="After having food";
            }
        });

        beforeFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beforeFood.setBackgroundResource(R.drawable.radiobtn_on);
                some.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);
                afterFood.setBackgroundResource(R.drawable.radiobtn_off);
                yes.setBackgroundResource(R.drawable.radiobtn_off);

                beforeFood.setTextColor(Color.WHITE);
                some.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);
                afterFood.setTextColor(Color.BLACK);
                yes.setTextColor(Color.BLACK);

                hairfall="Before having food";
            }
        });


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),employment, Toast.LENGTH_SHORT).show();

                DataSectionThree.hairfall = hairfall;
                DataSectionThree.s3q7 = textView77.getText().toString();
                if (hairfall.equals(""))
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                else {
                    Consultation.section3 += 1;
                    Navigation.findNavController(v).navigate(R.id.action_section3Q7_to_section3Q8);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Consultation.section3>0)
                    Consultation.section3-=1;
                Navigation.findNavController(v).navigate(R.id.action_section3Q7_to_section3Q6);
            }
        });

        return view;
    }
}