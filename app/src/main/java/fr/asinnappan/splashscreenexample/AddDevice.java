package fr.asinnappan.splashscreenexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddDevice extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId;
    private EditText editTextMarque;
    private EditText editTextOs;
    private FirebaseFirestore db;
    Button exit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        db = FirebaseFirestore.getInstance();

        editTextId = findViewById(R.id.add_id);
        editTextMarque = findViewById(R.id.add_marque);
        editTextOs = findViewById(R.id.add_os);

        findViewById(R.id.valider).setOnClickListener(this);

        this.exit = findViewById(R.id.retour);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayDevice.class);
                startActivity(intent);
            }
        });

    }



    private boolean hasValidationErrors(String id, String marque, String os){
        if (id.isEmpty()){
            editTextOs.setError("Erreur de saisie");
            editTextOs.requestFocus();
            return true;
        }

        if (marque.isEmpty()){
            editTextMarque.setError("Marque Error");
            editTextMarque.requestFocus();
            return true;
        }

        if (os.isEmpty()){
            editTextOs.setError("Os Error");
            editTextOs.requestFocus();
            return true;
        }
        return false;
    }



    @Override
    public void onClick(View v) {

        String id = editTextId.getText().toString().trim();
        String marque = editTextMarque.getText().toString().trim();
        String os = editTextOs.getText().toString().trim();

        if (!hasValidationErrors(id, marque, os)) {

            CollectionReference dbPortable = db.collection("portable");

            Portable portable = new Portable(
                    id,
                    marque,
                    os
            );

            dbPortable.add(portable)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddDevice.this, "Ajout réussi", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), DisplayDevice.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddDevice.this, "Echec de l'ajout", Toast.LENGTH_LONG).show();
                        }
                    });
        }


    }
}
