package in.zeroonesolutions.questionpool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.animations.DescriptionAnimation;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.DefaultSliderView;
import com.glide.slider.library.slidertypes.TextSliderView;
import com.glide.slider.library.tricks.ViewPagerEx;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SliderLayout mDemoSlider;
    FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        List<Subject> mSubjectList = new ArrayList<>();
        mSubjectList.add(new Subject(1,"Chemistry",R.drawable.chemistry));
        mSubjectList.add(new Subject(2,"Physics",R.drawable.physics));
        mSubjectList.add(new Subject(3,"Biology",R.drawable.biology));
        mSubjectList.add(new Subject(4,"Maths",R.drawable.maths));
        mSubjectList.add(new Subject(5,"IT",R.drawable.computer));
        mSubjectList.add(new Subject(6,"Social Science",R.drawable.social));
        mSubjectList.add(new Subject(7,"English",R.drawable.english));
        mSubjectList.add(new Subject(8,"Malayalam",R.drawable.malayalam));
        mSubjectList.add(new Subject(9,"Hindi",R.drawable.hindi));
        mSubjectList.add(new Subject(10,"Arabic",R.drawable.arabic));
        mSubjectList.add(new Subject(11,"Urudu",R.drawable.urudu));
        mSubjectList.add(new Subject(12,"Sanskrit",R.drawable.sanscrit));

        SubjectAdapter mAdapter = new SubjectAdapter(getApplicationContext(),mSubjectList);
        mRecyclerView.setAdapter(mAdapter);


        loadSlider();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_rate:
                final String appPackageName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                return true;
            case R.id.action_share:
                Intent share=new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(android.content.Intent.EXTRA_SUBJECT, "QuestBank Android Application");
                share.putExtra(android.content.Intent.EXTRA_TEXT,"For SSLC Previous Year Question Papers, Model Question Papers and Study Materials. Download Now : http://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                startActivity(Intent.createChooser(share,"Share App"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadSlider(){
        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences",MODE_PRIVATE);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.VISIBLE);

        mDemoSlider = findViewById(R.id.slider);

        final List<String> urlList = new ArrayList<>();
        urlList.add(sharedPreferences.getString("banner1","https://www.rd.com/wp-content/uploads/2018/04/06-Education-Quotes-That-Inspire-a-Love-of-Learning-Nicole-fornabaio-rd.com-shutterstock-1024x683.jpg"));
        urlList.add(sharedPreferences.getString("banner2","https://www.rd.com/wp-content/uploads/2018/04/01-Education-Quotes-That-Inspire-a-Love-of-Learning-Nicole-fornabaio-rd.com-shutterstock-1024x683.jpg"));
        urlList.add(sharedPreferences.getString("banner3","https://www.rd.com/wp-content/uploads/2018/04/01-Education-Quotes-That-Inspire-a-Love-of-Learning-Nicole-fornabaio-rd.com-shutterstock-1024x683.jpg"));
        urlList.add(sharedPreferences.getString("banner4","https://www.rd.com/wp-content/uploads/2018/04/05-Education-Quotes-That-Inspire-a-Love-of-Learning-Nicole-fornabaio-rd.com-shutterstock-1024x683.jpg"));
        urlList.add(sharedPreferences.getString("banner5","https://www.rd.com/wp-content/uploads/2018/04/08-Education-Quotes-That-Inspire-a-Love-of-Learning-Nicole-fornabaio-rd.com-shutterstock-1024x683.jpg"));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        requestOptions.placeholder(R.color.colorGrey);
        requestOptions.error(R.color.colorGrey);

        for (int i = 0; i < urlList.size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(MainActivity.this);
            sliderView
                    .image(urlList.get(i))
                    .setRequestOption(requestOptions)
                    .setProgressBarVisible(false);
            mDemoSlider.addSlider(sliderView);
        }

        // Slider Transition Animation
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setDuration(6000);
        mDemoSlider.stopCyclingWhenTouch(false);
    }


}
