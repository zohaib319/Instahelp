package ndroid.google.com.instahelp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import ndroid.google.com.instahelp.R;

public class Welcome extends AppCompatActivity {

    Button signup_using_email;
    LinearLayout goto_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        goto_login = (LinearLayout)findViewById(R.id.go_to_signin);
        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent a=new Intent(Welcome.this,LoginActivity.class);
                startActivity(a);

            }
        });

        signup_using_email=(Button) findViewById(R.id.signup_using_email);
        signup_using_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Welcome.this,RegisterActivity.class);
                startActivity(intent);

            }
        });

    }
}
