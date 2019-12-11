package in.zeroonesolutions.questionpool;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class ReadActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    AdView mAdView;
    String material_name, material_url, material_subject, material_board, material_class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                Intent in = new Intent(getApplicationContext(),PdfActivity.class);
                in.putExtra("name",material_name);
                in.putExtra("url",material_url);
                startActivity(in);
            }

        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent in = getIntent();
        material_name = in.getStringExtra("name");
        material_url = in.getStringExtra("url");
        material_subject = in.getStringExtra("subject");
        material_board = in.getStringExtra("board");
        material_class = in.getStringExtra("class");
        getSupportActionBar().setTitle(material_name);

        TextView txtName = findViewById(R.id.txt_name);
        txtName.setText(material_name);
        TextView txtSubject = findViewById(R.id.txt_subject);
        txtSubject.setText(material_subject + " - " + material_class + " " + material_board);
        TextView txtMedium = findViewById(R.id.txt_medium);
        txtMedium.setText("PDF File");

        Button btnOpen = findViewById(R.id.btn_open);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent in = new Intent(getApplicationContext(),PdfActivity.class);
                    in.putExtra("name",material_name);
                    in.putExtra("url",material_url);
                    startActivity(in);
                }

            }
        });



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
