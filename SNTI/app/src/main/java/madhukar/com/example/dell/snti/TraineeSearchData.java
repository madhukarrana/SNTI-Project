package madhukar.com.example.dell.snti;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class TraineeSearchData extends AppCompatActivity {

    TextView reference,name,institute,startDate,endDate,projectTitle,guide,contact,email,branch;
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee_search_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.traineeToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);

        reference=(TextView)findViewById(R.id.reference);
        name=(TextView)findViewById(R.id.name);
        institute=(TextView)findViewById(R.id.institue);
        startDate=(TextView)findViewById(R.id.startDate);
        endDate=(TextView)findViewById(R.id.endDate);
        projectTitle=(TextView)findViewById(R.id.projectTitle);
        guide =(TextView)findViewById(R.id.guideName);
        contact=(TextView)findViewById(R.id.contact);
        email=(TextView)findViewById(R.id.traineeEmail);
        branch=(TextView)findViewById(R.id.branch);

        Intent intent=getIntent();
        Bundle data=intent.getExtras();
        int index=data.getInt("searchResultIndex");
        setData(index);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            Intent i = new Intent();
            setResult(RESULT_OK,i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        setResult(RESULT_OK,i);
        finish();
        super.onBackPressed();
    }

    public void setData(int index)
    {
        TraineeDetails traineeDetails =TraineeDatabse.getTraineeDetails(index);
        collapsingToolbar.setTitle(traineeDetails.getName());
        reference.setText(traineeDetails.getReferenceNo());
        name.setText(traineeDetails.getName());
        institute.setText(traineeDetails.getInstitute());
        branch.setText(traineeDetails.getBranch());
        startDate.setText(traineeDetails.getStartDate());
        endDate.setText(traineeDetails.getEndDate());
        projectTitle.setText(traineeDetails.getProjectTitle());
        guide.setText(traineeDetails.getGuideName());
        contact.setText(traineeDetails.getContact());
        email.setText(traineeDetails.getEmail());

    }
}
