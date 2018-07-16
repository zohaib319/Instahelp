package ndroid.google.com.instahelp.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import ndroid.google.com.instahelp.MainActivity;
import ndroid.google.com.instahelp.R;
import ndroid.google.com.instahelp.core.register.RegisterContract;
import ndroid.google.com.instahelp.core.register.RegisterPresenter;
import ndroid.google.com.instahelp.core.users.add.AddUserContract;
import ndroid.google.com.instahelp.core.users.add.AddUserPresenter;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,AddUserContract.View,RegisterContract.View {

    private RegisterPresenter mRegisterPresenter;
    private AddUserPresenter mAddUserPresenter;
    private EditText name,email,password;
    public Button sign_up;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        email = (EditText)findViewById(R.id.email);
        name = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        sign_up=  (Button)findViewById(R.id.signup_btn);

        init();
    }
    private void init()
    {
        mRegisterPresenter = new RegisterPresenter(this);
        mAddUserPresenter = new AddUserPresenter(this);

        mProgressDialog = new ProgressDialog(RegisterActivity.this);
        mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage("Please wait");
        mProgressDialog.setIndeterminate(true);

        sign_up.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.signup_btn:
                if (email.getText().toString().equals("")||(name.getText().toString().equals(""))||(password.getText().toString().equals("")))
                {
                    Toast.makeText(RegisterActivity.this, "One or more field is empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    onRegister(v);
                }
                break;
        }




    }
    private void onRegister(View view)
    {
        String entered_mail = email.getText().toString();
        String emtered_name = name.getText().toString();
        String entered_password = password.getText().toString();
        mRegisterPresenter.register(RegisterActivity.this,entered_mail,entered_password);
        mProgressDialog.show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
        MainActivity.startActivity(RegisterActivity.this,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    }

    @Override
    public void onAddUserFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mProgressDialog.setMessage("Setting up Profile");
        Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
        mAddUserPresenter.addUser(RegisterActivity.this, firebaseUser);

    }

    @Override
    public void onRegistrationFailure(String message) {
        mProgressDialog.dismiss();
        mProgressDialog.setMessage("Please wait");
        Log.e("register", "onRegistrationFailure: " + message);
        Toast.makeText(RegisterActivity.this, "Registration failed!+\n" + message, Toast.LENGTH_LONG).show();

    }
}
