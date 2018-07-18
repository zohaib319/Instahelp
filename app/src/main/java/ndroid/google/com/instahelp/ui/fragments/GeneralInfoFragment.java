package ndroid.google.com.instahelp.ui.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.hanks.library.AnimateCheckBox;

import java.util.Timer;
import java.util.TimerTask;

import ndroid.google.com.instahelp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GeneralInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GeneralInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GeneralInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GeneralInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralInfoFragment newInstance(String param1, String param2) {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
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
        View v= inflater.inflate(R.layout.fragment_general_info, container, false);

        final LinearLayout female_layout=(LinearLayout) v.findViewById(R.id.female_layout);
        LinearLayout male_layout=(LinearLayout) v.findViewById(R.id.male_layout);

        final AnimateCheckBox male_cb=(AnimateCheckBox) v.findViewById(R.id.male_cb);
        final AnimateCheckBox female_cb=(AnimateCheckBox) v.findViewById(R.id.female_cb);

        female_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                female_cb.setChecked(true);
                male_cb.setChecked(false);

            }
        });

        male_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                male_cb.setChecked(true);
                female_cb.setChecked(false);

            }
        });

        male_cb.setOnCheckedChangeListener(new AnimateCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View buttonView, boolean isChecked) {
                if(isChecked)
                    female_cb.setChecked(false);
            }
        });

        female_cb.setOnCheckedChangeListener(new AnimateCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View buttonView, boolean isChecked) {
                if(isChecked)
                    male_cb.setChecked(false);
            }
        });

        final EditText hold_on_text=(EditText) v.findViewById(R.id.hold_on_text);

        final String arr = getResources().getStringArray(R.array.welcom_strings)[3];

        new Timer().scheduleAtFixedRate(new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(count<arr.length()){
                        hold_on_text.setSelection(hold_on_text.getText().length());
                        hold_on_text.append(arr.charAt(count) + "");
                        count++;}else {

                        }
                    }
                });
            }
        }, 10, 100);


        Button next_btn=(Button) v.findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment someFragment = new UserPreferenceFargment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frag, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack("true");  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
