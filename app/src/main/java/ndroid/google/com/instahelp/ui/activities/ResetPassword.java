package ndroid.google.com.instahelp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import ndroid.google.com.instahelp.R;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {
    public EditText email;
    public Button reset_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        email = (EditText)findViewById(R.id.email);
        reset_pass = (Button)findViewById(R.id.reset_password_btn);
        reset_pass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.reset_password_btn:
                String mail = email.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(mail);
                Toast.makeText(this, "Password Reset Instructions sent to your email", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
