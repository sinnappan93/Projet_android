package fr.asinnappan.splashscreenexample;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Home extends AppCompatActivity {

    ImageView userLogout;
    private ImageView display;
    private ImageView emprunter_rendre;
    private Button add;


//    ----------------------------------------------------------------------------------------------
//        Bouton de deconnexion qui redirige vers le MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userLogout = findViewById(R.id.logout);
        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Home.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity( intent);
                Toast.makeText(Home.this, "Déconnecté", Toast.LENGTH_SHORT).show();
            }
        });

//        ------------------------------------------------------------------------------------------
//        Les boutons du menu pour rediriger vers les diffèrentes pages web

        this.display = findViewById(R.id.button_display);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayDevice.class);
                startActivity(intent);
            }
        });

        this.emprunter_rendre = findViewById(R.id.button_get);
        emprunter_rendre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GetDevice.class);
                startActivity(intent);
            }
        });



//        ------------------------------------------------------------------------------------------
//        Timer sur la page home

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.text_view_date);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);
                            }
                        });
                    }
                }catch (InterruptedException e ) {
                }
            }
        };
        t.start();
    }
}




