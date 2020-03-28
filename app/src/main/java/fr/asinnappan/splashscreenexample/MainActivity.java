package fr.asinnappan.splashscreenexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.FirebaseApp;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
//        ------------------------------------------------------------------------------------------
//        Bouton de connexion qui permet de se connecter, on recupère les methodes de firebase qui premettent de se connecter via l'adresse email.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.bouton_login);

        FirebaseApp.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());
                startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.drawable.ic_logo_auth)
                        .setIsSmartLockEnabled(false, true)
                        .setAvailableProviders(providers)
                        .build(), RC_SIGN_IN);
            }
        });
    }

//        ------------------------------------------------------------------------------------------
//        Si la variable RC_SIGN_IN est retournée par la partie de code precedent alors on recupere les infos et on lance l'activité Main.

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            Intent intent = new Intent(MainActivity.this, Home.class);
            if (resultCode == RESULT_OK) {
                startActivity(intent);
            }
        }

        
    }
}


