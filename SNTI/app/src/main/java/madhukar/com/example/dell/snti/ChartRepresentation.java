package madhukar.com.example.dell.snti;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartRepresentation extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Integer[] selectedInstituteList;
    private Integer[] selectedBranchList;
    private Integer[] selectedGuideList;
    private List<String> instituteList=new ArrayList<>();
    private List<String> branchList=new ArrayList<>();
    private List<String> guideList=new ArrayList<>();
    private String startDate,endDate;
    List<TraineeDetails> reportData = new ArrayList<TraineeDetails>();

    private Spinner chartOrder,chartOption;
    private String[] order={"YEAR","BRANCH","INSTITUTE","GUIDE"};
    private String[] option={"PIE","BAR"};
    private String currentOrder,currentOption;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static Map<String,Integer> yearData=new HashMap<String, Integer>();
    public static Map<String,Integer> branchData=new HashMap<String, Integer>();
    public static Map<String,Integer> instituteData=new HashMap<String, Integer>();
    public static Map<String,Integer> guideDataSet=new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_representation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chartToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getReportData();
        getYearData(startDate, endDate);
        getBranchData();
        getInstituteData();
        getGuideData();

/*        String str = "";
        for (Map.Entry m : yearData.entrySet())
            str += (m.getKey() + " " + m.getValue()) + "\n";
        for (Map.Entry m : branchData.entrySet())
            str += (m.getKey() + " " + m.getValue()) + "\n";
        for (Map.Entry m : instituteData.entrySet())
            str += (m.getKey() + " " + m.getValue()) + "\n";
        for (Map.Entry m : guideDataSet.entrySet())
            str += (m.getKey() + " " + m.getValue()) + "\n";

        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
 */

            chartOrder = (Spinner) findViewById(R.id.chartOrder);
            chartOption = (Spinner) findViewById(R.id.chartOption);

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, order);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            chartOrder.setAdapter(adapter1);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, option);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            chartOption.setAdapter(adapter2);

            chartOption.setOnItemSelectedListener(this);

            fragmentManager = getSupportFragmentManager();


            currentOption = chartOption.getSelectedItem().toString();
            if (currentOption.equals("PIE")) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.chartFrameholder, new PieChartRepresentation());
                fragmentTransaction.commit();

            } else if (currentOption.equals("BAR")) {

            } else if (currentOption.equals("LINE")) {

            }

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

    private void getYearData(String startDate,String endDate)
    {
        int start= getYearIntegerValue(startDate);
        int end= getYearIntegerValue(endDate);
        int i=start;
        while (i<=end)
        {
            yearData.put(new Integer(i).toString(),0);
            i++;
        }

        for(i=0;i<reportData.size();i++)
        {
           String key = getYearStringValue(reportData.get(i).getStartDate());
           yearData.put(key,yearData.get(key)+1);
        }

    }

    private void getBranchData()
    {
        int i;
        String key="";
        for(i=0;i<branchList.size();i++)
        {
            branchData.put(branchList.get(i),0);
        }
        for(i=0;i<reportData.size();i++)
        {
            key=reportData.get(i).getBranch();
            branchData.put(key,branchData.get(key)+1);
        }
    }
    private void getInstituteData()
    {
        int i;
        String key="";
        for(i=0;i<instituteList.size();i++)
        {
            instituteData.put(instituteList.get(i),0);
        }
        for(i=0;i<reportData.size();i++)
        {
            key=reportData.get(i).getInstitute();
            instituteData.put(key,instituteData.get(key)+1);
        }
    }
    private void getGuideData()
    {
        int i;
        String key="";
        for(i=0;i<guideList.size();i++)
        {
            guideDataSet.put(guideList.get(i),0);
        }
        for(i=0;i<reportData.size();i++)
        {
            key=reportData.get(i).getGuideName();
            guideDataSet.put(key,guideDataSet.get(key)+1);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
     switch (position)
     {
         case 0:
             fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.chartFrameholder,new PieChartRepresentation());
             fragmentTransaction.commit();
             break;
         case 1:
             fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.chartFrameholder,new BarChartRepresentation());
             fragmentTransaction.commit();
             break;
         case 2:
             break;
     }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private int getYearIntegerValue(String date)
    {
        String[] a = date.split("/");
        int year = Integer.parseInt(a[2]);
        return year;
    }

    private String getYearStringValue(String date)
    {
        String[] a = date.split("/");
        return a[2];
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
