package ndroid.google.com.instahelp.ui.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ndroid.google.com.instahelp.MainActivity;
import ndroid.google.com.instahelp.R;
import ndroid.google.com.instahelp.core.login.LoginContract;
import ndroid.google.com.instahelp.core.login.LoginPresenter;
import ndroid.google.com.instahelp.core.register.RegisterContract;
import ndroid.google.com.instahelp.ui.activities.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener,LoginContract.View {
    private LoginPresenter mLoginPresenter;
    public EditText email,password;
    public Button login,register;
    private ProgressDialog mProgressDialog;


    public LoginFragment() {
        // Required empty public constructor
    }
    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        bindViews(view);
        return view;
    }
    private void bindViews(View view)
    {
        email = (EditText)view.findViewById(R.id.email);
        password = (EditText)view.findViewById(R.id.password);
        login = (Button) view.findViewById(R.id.login_btn);
        register = (Button)view.findViewById(R.id.go_to_register);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    private void init()
    {
        mLoginPresenter = new LoginPresenter(this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle("Logging In");
        mProgressDialog.setMessage("Please wait");
        mProgressDialog.setIndeterminate(true);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.login_btn:
                if (email.getText().toString().equals("")||(password.getText().toString().equals("")))
                {
                    Toast.makeText(getActivity().getApplicationContext(), "One or more field is empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    onLogin(v);
                }
                break;
            case R.id.go_to_register:
                Intent i = new Intent(getActivity(), RegisterActivity.class);
                startActivity(i);
        }

    }
    private void onLogin(View view) {
        String emailId = email.getText().toString();
        String pass = password.getText().toString();

        mLoginPresenter.login(getActivity(), emailId, pass);
        mProgressDialog.show();
    }

    @Override
    public void onLoginSuccess(String message) {
        MainActivity.startActivity(getActivity(),
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_SHORT).show();

    }
}
