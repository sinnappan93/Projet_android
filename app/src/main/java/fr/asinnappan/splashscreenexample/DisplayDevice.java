package fr.asinnappan.splashscreenexample;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class DisplayDevice extends AppCompatActivity {

    FloatingActionButton add;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_device);

        firebaseFirestore = firebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.listview);

        Query query = firebaseFirestore.collection("portable");
        FirestoreRecyclerOptions<ProductsModel> options = new FirestoreRecyclerOptions.Builder<ProductsModel>()
                .setQuery(query, ProductsModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ProductsModel, ProductViewHolder>(options) {
            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new ProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull ProductsModel model) {
                holder.list_id.setText(model.getId());
                holder.list_marque.setText(model.getMarque());
                holder.list_etat.setText(model.getEtat());

            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);

        this.add =  findViewById(R.id.add_device);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDevice.class);
                startActivity(intent);
            }
        });
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView list_id;
        private TextView list_marque;
        private TextView list_etat;

        public ProductViewHolder (@NonNull View itemView) {
            super(itemView);

            list_id = itemView.findViewById(R.id.list_id);
            list_marque = itemView.findViewById(R.id.list_marque);
            list_etat = itemView.findViewById(R.id.list_etat);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
