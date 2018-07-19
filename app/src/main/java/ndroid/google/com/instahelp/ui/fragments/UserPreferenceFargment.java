package ndroid.google.com.instahelp.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ndroid.google.com.instahelp.R;
import ndroid.google.com.instahelp.models.Categories;
import ndroid.google.com.instahelp.models.SubCategories;

import static android.content.ContentValues.TAG;


public class UserPreferenceFargment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Categories> categoriesArrayList=new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    public UserPreferenceFargment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UserPreferenceFargment newInstance(String param1, String param2) {
        UserPreferenceFargment fragment = new UserPreferenceFargment();
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
        View v= inflater.inflate(R.layout.fragment_user_preference, container, false);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.getKey();
                    Categories categories=new Categories();
                    categories.setName(name);
                    ArrayList<SubCategories> childs=new ArrayList<>();
                    for(DataSnapshot ds1 : ds.getChildren()) {
                        String key1 = ds1.getValue(String.class);
                        childs.add(new SubCategories(key1));
                        Log.d("TAG", name + " / " + key1 + " / ");
                    }
                    categories.setSubCategories(childs);
                    categoriesArrayList.add(categories);
                }
                getActivity().setProgressBarIndeterminateVisibility(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        Query query = database.child("categories").orderByValue();
        query.addValueEventListener(postListener);


        LinearLayout layout=(LinearLayout) v.findViewById(R.id.layout);

        for(int i=0;i<categoriesArrayList.size();i++)
        {

            RecyclerView recyclerView=new RecyclerView(getActivity());
            recyclerView.setHasFixedSize(false);
            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManagaer);



        }

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
