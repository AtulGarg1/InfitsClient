package com.example.infits;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeightTrackerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeightTrackerFragment extends Fragment {

    ImageButton adddet, imgback;
    TextView textbmi, curWeight;
    public int userWeight;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeightTrackerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeightTrackerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeightTrackerFragment newInstance(String param1, String param2) {
        WeightTrackerFragment fragment = new WeightTrackerFragment();
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
        View view = inflater.inflate(R.layout.fragment_weight_tracker, container, false);

        adddet = view.findViewById(R.id.adddet);
        textbmi = view.findViewById(R.id.textbmi);
        imgback = view.findViewById(R.id.imgback);
        curWeight = view.findViewById(R.id.curWeight);

        adddet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_weightTrackerFragment_to_weightDateFragment);
            }
        });

        getParentFragmentManager().setFragmentResultListener("weightData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String uWeight = result.getString("weight");
                curWeight.setText(uWeight);
                userWeight = Integer.parseInt(uWeight);
                String dateChange = result.getString("weightChangeDate");
            }
        });

        getParentFragmentManager().setFragmentResultListener("personalData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String uGender = result.getString("gender");
                int uAge = Integer.parseInt(result.getString("age"));
                float uHeight = Float.parseFloat(result.getString("height"));
                int uWeight = Integer.parseInt(result.getString("weight"));

                float hsquare = (uHeight/100) * (uHeight/100);
                float bmi = uWeight/hsquare;
                textbmi.setText(String.format("%.2f",bmi));
                //userWeight = Integer.parseInt(uWeight);
            }
        });





        /*

        adddet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(v).navigate(R.id.action_weightTrackerFragment_to_weightDateFragment);

                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.bmidialog);

                dialog.show();
            }
        });

         */

        textbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_weightTrackerFragment_to_bmiFragment);
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_weightTrackerFragment_to_dashBoardFragment);
            }
        });



        return view;
    }
}