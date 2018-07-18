package ndroid.google.com.instahelp.ui.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import ndroid.google.com.instahelp.R;
import ndroid.google.com.instahelp.ui.fragments.GeneralInfoFragment;
import ndroid.google.com.instahelp.ui.fragments.UserPreferenceFargment;

public class UserPreferencesActivity extends AppCompatActivity implements GeneralInfoFragment.OnFragmentInteractionListener,UserPreferenceFargment.OnFragmentInteractionListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference);

        FirebaseApp.initializeApp(getApplicationContext());

        Fragment someFragment = new GeneralInfoFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frag, someFragment ); // give your fragment container id in first parameter
//        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
