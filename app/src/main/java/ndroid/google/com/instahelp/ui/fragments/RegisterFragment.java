package ndroid.google.com.instahelp.ui.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import ndroid.google.com.instahelp.MainActivity;
import ndroid.google.com.instahelp.R;
import ndroid.google.com.instahelp.core.register.RegisterContract;
import ndroid.google.com.instahelp.core.register.RegisterPresenter;
import ndroid.google.com.instahelp.core.users.add.AddUserContract;
import ndroid.google.com.instahelp.core.users.add.AddUserPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener,AddUserContract.View,RegisterContract.View {
    private static final String TAG = RegisterFragment.class.getSimpleName();
    private RegisterPresenter mRegisterPresenter;
    private AddUserPresenter mAddUserPresenter;
    private EditText name,email,password;
    public Button sign_up,login;
    private ProgressDialog mProgressDialog;

    public RegisterFragment() {
        // Required empty public constructor
    }
    public static RegisterFragment newInstance()
    {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register, container, false);
        bindViews(view);
        return view;

    }
    private void bindViews(View view)
    {
        email = (EditText)view.findViewById(R.id.email);
        name = (EditText)view.findViewById(R.id.username);
        password = (EditText)view.findViewById(R.id.password);
        sign_up=  (Button)view.findViewById(R.id.signup_btn);
        login = (Button)view.findViewById(R.id.go_to_signin);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    private void init()
    {
        mRegisterPresenter = new RegisterPresenter(this);
        mAddUserPresenter = new AddUserPresenter(this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage("Please wait");
        mProgressDialog.setIndeterminate(true);

        sign_up.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.signup_btn:
                if (email.getText().toString().equals("")||(name.getText().toString().equals(""))||(password.getText().toString().equals("")))
                {
                    Toast.makeText(getActivity().getApplicationContext(), "One or more field is empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    onRegister(v);
                }
                    break;
            case R.id.go_to_signin:
                getActivity().finish();
        }




    }
    private void onRegister(View view)
    {
        String entered_mail = email.getText().toString();
        String emtered_name = name.getText().toString();
        String entered_password = password.getText().toString();
        mRegisterPresenter.register(getActivity(),entered_mail,entered_password);
        mProgressDialog.show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        MainActivity.startActivity(getActivity(),
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    }

    @Override
    public void onAddUserFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mProgressDialog.setMessage("Setting up Profile");
        Toast.makeText(getActivity(), "Registration Successful!", Toast.LENGTH_SHORT).show();
        mAddUserPresenter.addUser(getActivity().getApplicationContext(), firebaseUser);

    }

    @Override
    public void onRegistrationFailure(String message) {
        mProgressDialog.dismiss();
        mProgressDialog.setMessage("Please wait");
        Log.e(TAG, "onRegistrationFailure: " + message);
        Toast.makeText(getActivity(), "Registration failed!+\n" + message, Toast.LENGTH_LONG).show();

    }
}
