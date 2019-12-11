package in.zeroonesolutions.questionpool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.DefaultSliderView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_LENGTH = 3000;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        sharedPreferences = getSharedPreferences("AppPreferences",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        /* New Handler to start the Main-Activity
         * and close this Splash-Screen after some seconds.*/
        loadSlider();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public void loadSlider(){

        FirebaseFirestore mFirestore;
        final List<String> urlList = new ArrayList<>();

        mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("banners")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String url = document.getString("url");
                        urlList.add(url);
                        //Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
                    }
                    for (int i=0; i<=urlList.size(); i++){
                        if (i==0){
                            editor.putString("banner1",urlList.get(i));
                        }
                        else if (i==1){
                            editor.putString("banner2",urlList.get(i));
                        }
                        else if (i==2){
                            editor.putString("banner3",urlList.get(i));
                        }
                        else if (i==3){
                            editor.putString("banner4",urlList.get(i));
                        }
                        else if (i==4){
                            editor.putString("banner5",urlList.get(i));
                        }

                    }
                    editor.apply();
                }
            }
        });

    }
}
