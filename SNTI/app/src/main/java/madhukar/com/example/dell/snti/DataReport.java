package madhukar.com.example.dell.snti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataReport extends AppCompatActivity {

    private Integer[] selectedInstituteList;
    private Integer[] selectedBranchList;
    private Integer[] selectedGuideList;
    private List<String> instituteList=new ArrayList<>();
    private List<String> branchList=new ArrayList<>();
    private List<String> guideList=new ArrayList<>();
    private String startDate,endDate;
    List<TraineeDetails> reportData = new ArrayList<TraineeDetails>();

   private RecyclerView recyclerView;
    private DataReportAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_report);

        Toolbar toolbar = (Toolbar) findViewById(R.id.reportToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getReportData();

        recyclerView = (RecyclerView)findViewById(R.id.reportRecyclerView);

        mAdapter = new DataReportAdapter(reportData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }

    private void getReportData()
    {

        Intent i =getIntent();
        Bundle b = i.getExtras();
        startDate = b.getString("startDate");
        endDate = b.getString("endDate");
        selectedInstituteList =Report.selectedInstituteList;
        selectedBranchList = Report.selectedBranchList;
        selectedGuideList = Report.selectedGuideList;

        int j;
        for(j=0;j<selectedInstituteList.length;j++)
            instituteList.add(Report.instituteList[selectedInstituteList[j]]);
        for(j=0;j<selectedBranchList.length;j++)
            branchList.add(Report.branchList[selectedBranchList[j]]);
        for(j=0;j<selectedGuideList.length;j++)
            guideList.add(Report.guideList[selectedGuideList[j]]);



        reportData=TraineeDatabse.makeReport(startDate,endDate,instituteList,branchList,guideList);

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        setResult(RESULT_OK,i);
        finish();
        super.onBackPressed();
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

}
