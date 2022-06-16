package in.zeroonesolutions.questionpool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class PdfActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;
    String material_name, material_url;
    RingProgressBar mRingProgressBar;
    TextView txtDwnHint;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                finish();
            }

        });

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRingProgressBar = findViewById(R.id.progress_bar);
        txtDwnHint = findViewById(R.id.dwn_hint);

        Intent in = getIntent();
        material_name = in.getStringExtra("name");
        material_url = in.getStringExtra("url");

        if (checkPermission())
            openOrDownload();


    }

    public void openOrDownload(){
        File myFile = new File(getFilesDir(), material_name);
        if(myFile.exists()) {
            showPdf();
        }
        else {
            if (isConnected()){
                new DownloadFile().execute(material_name, material_url);
            }
            else {
                Toast.makeText(PdfActivity.this, "No Internet Connection.", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private class DownloadFile extends AsyncTask<String, Integer, String> {

        String file_name, file_url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mRingProgressBar.setVisibility(View.VISIBLE);
            txtDwnHint.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            file_name = strings[0];
            file_url = strings[1];
            try {
                URL url = new URL(file_url);
                URLConnection connection = url.openConnection();
                connection.connect();

                // Detect the file lenghth
                final int fileLength = connection.getContentLength();

                // Download the file
                final InputStream input = new BufferedInputStream(url.openStream());

                // Save the downloaded file
                final FileOutputStream output = openFileOutput(file_name, MODE_PRIVATE);

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // Publish the progress
                    publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
                // Close connection
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                Log.e("ERROR",e.getMessage());
                return "error";
            }

            return "success";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mRingProgressBar.setProgress(values[0]);
            if (values[0]==100){
                mRingProgressBar.setVisibility(View.GONE);
                txtDwnHint.setVisibility(View.GONE);
                showPdf();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private void showPdf() {
        String file_name = material_name;

        PDFView pdfView = findViewById(R.id.pdf_view);
        pdfView.setVisibility(View.VISIBLE);
        pdfView.fromFile(new File(getFilesDir(),file_name))
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .load();
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public boolean checkPermission(){
        if (ContextCompat.checkSelfPermission(PdfActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else {
            ActivityCompat.requestPermissions(PdfActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
            return false;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                    openOrDownload();

                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded())
            mInterstitialAd.show();
        else
            super.onBackPressed();
    }
}
