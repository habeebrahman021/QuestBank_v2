package in.zeroonesolutions.questionpool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

public class MaterialsActivity extends AppCompatActivity {

    FirebaseFirestore mFirestore;
    String sub_name;
    ProgressBar mProgressBar;
    RecyclerView mRecyclerView;
    FirestoreRecyclerAdapter adapter;

    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mFirestore = FirebaseFirestore.getInstance();

        Intent in = getIntent();
        sub_name = in.getStringExtra("sub_name");
        getSupportActionBar().setTitle(sub_name);

        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        loadMaterials();

    }
    private void loadMaterials(){
        mProgressBar.setVisibility(View.VISIBLE);
        Query query = mFirestore.collection("materials")
                .whereEqualTo("subject",sub_name);

        FirestoreRecyclerOptions<Material> response = new FirestoreRecyclerOptions.Builder<Material>()
                .setQuery(query, Material.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Material, MaterialsHolder>(response) {
            @Override
            public void onBindViewHolder(MaterialsHolder holder, int position, final Material material) {
                mProgressBar.setVisibility(View.GONE);
                holder.txtName.setText(material.getName());
                holder.txtSub.setText("PDF File");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(getApplicationContext(),ReadActivity.class);
                        in.putExtra("name",material.getName());
                        in.putExtra("url",material.getUrl());
                        in.putExtra("subject",material.getSubject());
                        in.putExtra("board",material.getBoard());
                        in.putExtra("class",material.getClass_type());
                        startActivity(in);
                    }
                });
            }

            @Override
            public MaterialsHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.row_material, group, false);

                return new MaterialsHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };

        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);
    }
    public class MaterialsHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtSub;

        public MaterialsHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txt_name);
            txtSub = view.findViewById(R.id.txt_medium);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
