package ndroid.google.com.instahelp.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import ndroid.google.com.instahelp.MainActivity;
import ndroid.google.com.instahelp.R;
import ndroid.google.com.instahelp.core.login.LoginContract;
import ndroid.google.com.instahelp.core.login.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginContract.View  {

    private LoginPresenter mLoginPresenter;
    public EditText email,password;
    public Button login;
    LinearLayout forgot_password;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        setContentView(R.layout.activity_login);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_btn);
        forgot_password = (LinearLayout) findViewById(R.id.go_to_forgot_password);
        init();

    }

    private void init()
    {
        mLoginPresenter = new LoginPresenter(this);

        mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setTitle("Logging In");
        mProgressDialog.setMessage("Please wait");
        mProgressDialog.setIndeterminate(true);
        login.setOnClickListener(this);
        forgot_password.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.login_btn:
                if (email.getText().toString().equals("")||(password.getText().toString().equals("")))
                {
                    Toast.makeText(LoginActivity.this, "One or more field is empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    onLogin(v);
                }
                break;
            case R.id.go_to_forgot_password:
                Intent i = new Intent(LoginActivity.this, ResetPassword.class);
                startActivity(i);
        }

    }
    private void onLogin(View view) {
        String emailId = email.getText().toString();
        String pass = password.getText().toString();

        mLoginPresenter.login(LoginActivity.this, emailId, pass);
        mProgressDialog.show();
    }

    @Override
    public void onLoginSuccess(String message) {
        MainActivity.startActivity(LoginActivity.this,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    }

    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(LoginActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();

    }
}
