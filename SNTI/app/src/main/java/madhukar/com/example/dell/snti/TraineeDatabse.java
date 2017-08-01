package madhukar.com.example.dell.snti;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.nearby.connection.Connections;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 5/28/2017.
 */

 public  class TraineeDatabse {

    private static ArrayList<TraineeDetails> traineeData = new ArrayList<TraineeDetails>();


    public static void createDatabase(Context context,ArrayList<TraineeDetails> traineeList)
    {
      /*  traineeData.add(new TraineeDetails("VT20172023","MADHUKAR RANA","NIT JAMSHEDPUR","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","9955177725","madhukarrana1997@gmail.com"));
        traineeData.add(new TraineeDetails("VT20170316","ANKITA ROY","ITER BHUBANESWAR","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","",""));
        traineeData.add(new TraineeDetails("VT20170586","ROHIT MUKHARJEE","ITER BHUBANESWAR","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","",""));
        traineeData.add(new TraineeDetails("VT20170327","SHIRIN MONDAL","KIIIT UNIVERSITY BHUBANESWAR","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","",""));
        traineeData.add(new TraineeDetails("VT20170118","RASHI CHOUDHARY","ITER BHUBANESWAR","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","",""));
        traineeData.add(new TraineeDetails("VT20172376","SUBHAM CHOWDHURY","ITER BHUBANESWAR","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","",""));
        traineeData.add(new TraineeDetails("VT20171551","SHUBHAM SINHA","BIT MESRA","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","",""));
        traineeData.add(new TraineeDetails("VT20172035","SHREYA SHALINI","NIT JAMSHEDPUR","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","",""));
        traineeData.add(new TraineeDetails("VT20172054","AJAY KUMAR","NIT JAMSHEDPUR","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","",""));
        traineeData.add(new TraineeDetails("VT20172891","SOBHA BAKSHI","NIT JAMSHEDPUR","COMPUTER SCIENCE","16/05/2017","07/06/2017","SNTI REPORT MANAGEMENT ANDROID APPLICATION","SAKET SINHA","",""));
        */

        traineeData=traineeList;
    }

    public static void insertTraineeDetails(TraineeDetails traineeDetails)
    {
        traineeData.add(traineeDetails);
    }

    public static int searchByReference(String reference)
    {
        int i,flag=0;
        for(i=0;i<traineeData.size();i++)
        {
            if(traineeData.get(i).getReferenceNo().equalsIgnoreCase(reference))
            {
                flag=1;
                break;
            }
        }
        if(flag==1)
            return i;
        else
        return -1;
    }
    public static int searchByDetails(String name,String institute,String branch,String startDate, String endDate)
    {
        int i,flag=0;
        for(i=0;i<traineeData.size();i++)
        {
           TraineeDetails traineeDetails=traineeData.get(i);
            if((traineeDetails.getName().equalsIgnoreCase(name))&&(traineeDetails.getInstitute().equalsIgnoreCase(institute))&&(traineeDetails.getBranch().equalsIgnoreCase(branch))&&(traineeDetails.getStartDate().equalsIgnoreCase(startDate))&&(traineeDetails.getEndDate().equalsIgnoreCase(endDate)))
            {
                flag=1;
                break;
            }
        }
        if(flag==1)
            return i;
        else
            return -1;

    }
    public static void ClearDatabase()
    {
        traineeData.clear();
    }

    public static TraineeDetails getTraineeDetails(int index)
    {

        return (traineeData.get(index));
    }
    public static List<TraineeUnderGuideData> searchTraineeUnderGuide(String guide)
    {
        List<TraineeUnderGuideData> traineeList = new ArrayList<>();
        int i;
        TraineeUnderGuideData trainee;
        for(i=0;i<traineeData.size();i++)
        {

            if(traineeData.get(i).getGuideName().equals(guide))
            {
                TraineeDetails traineeDetails = traineeData.get(i);
                trainee = new TraineeUnderGuideData(i,traineeDetails.getReferenceNo(),traineeDetails.getName(),traineeDetails.getInstitute(),traineeDetails.getBranch(),traineeDetails.getStartDate(),traineeDetails.getEndDate());
                traineeList.add(trainee);
            }
        }
        return traineeList;
    }
    public static List<TraineeDetails> makeReport(String startDate,String endDate,List<String> instituteList,List<String> branchList,List<String> guideList)
    {

        int i;
        List<TraineeDetails> data=new ArrayList<TraineeDetails>();
        for(i=0;i<traineeData.size();i++)
        {
            TraineeDetails details=traineeData.get(i);
            if(instituteList.indexOf(details.getInstitute())!=-1)
            {
                 if(branchList.indexOf(details.getBranch())!=-1)
                 {
                     if(guideList.indexOf(details.getGuideName())!=-1)
                     {
                         if(checkDate(startDate,details.getStartDate())&&(checkDate(details.getEndDate(),endDate)))
                         {
                             data.add(details);
                         }
                     }
                 }
            }
        }
        return data;
    }

    public static boolean checkDate(String startDate,String endDate)
    {
        int yy1,mm1,dd1,yy2,mm2,dd2;
        String[] start = startDate.split("/");
        dd1=Integer.parseInt(start[0]);
        mm1=Integer.parseInt(start[1]);
        yy1=Integer.parseInt(start[2]);
        String[] end = endDate.split("/");
        dd2=Integer.parseInt(end[0]);
        mm2=Integer.parseInt(end[1]);
        yy2=Integer.parseInt(end[2]);
        if(yy2>yy1)
            return true;
        else if(yy2==yy1)
        {
            if(mm2>mm1)
                return true;
            else if(mm2==mm1)
            {
                if(dd2>=dd1)
                    return true;
            }
        }
        return false;
    }
}
