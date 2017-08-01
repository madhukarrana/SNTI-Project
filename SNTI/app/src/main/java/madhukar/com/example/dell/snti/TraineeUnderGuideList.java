package madhukar.com.example.dell.snti;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TraineeUnderGuideList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TraineeUnderGuideAdapter mAdapter;
    private CollapsingToolbarLayout collapsingToolbar;
    private List<TraineeUnderGuideData> traineeUnderGuide=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee_under_guide_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.guideToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.traineeGuidecollapseToolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String guide = bundle.getString("guideName");
        prepareData(guide);
        collapsingToolbar.setTitle(guide);

        recyclerView = (RecyclerView)findViewById(R.id.traineeUnderGuideRecycler);

        mAdapter = new TraineeUnderGuideAdapter(traineeUnderGuide);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void prepareData(String guideName)
    {
        traineeUnderGuide = TraineeDatabse.searchTraineeUnderGuide(guideName);

    }
}

