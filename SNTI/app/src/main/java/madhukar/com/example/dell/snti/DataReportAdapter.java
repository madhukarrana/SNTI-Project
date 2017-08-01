package madhukar.com.example.dell.snti;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 6/6/2017.
 */

public class DataReportAdapter extends  RecyclerView.Adapter<DataReportAdapter.DataReportViewHolder> {


   private List<TraineeDetails> reportData = new ArrayList<TraineeDetails>();

    public DataReportAdapter(List<TraineeDetails> reportData) {
        this.reportData = reportData;
    }

    @Override
    public DataReportAdapter.DataReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trainee_under_guide_layout, parent, false);

        return new DataReportAdapter.DataReportViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(DataReportAdapter.DataReportViewHolder holder, int position) {

        TraineeDetails traineeData = reportData.get(position);
        holder.reference.setText(traineeData.getReferenceNo());
        holder.name.setText(traineeData.getName());
        holder.institute.setText(traineeData.getInstitute());
        holder.branch.setText(traineeData.getBranch());
        holder.startDate.setText("Start Date :"+traineeData.getStartDate());
        holder.endDate.setText("End Date :"+traineeData.getEndDate());

    }

    @Override
    public int getItemCount() {
        return reportData.size();
    }
    public class DataReportViewHolder extends RecyclerView.ViewHolder {
        public TextView reference,name,institute,branch,startDate,endDate;

        public DataReportViewHolder(View view) {
            super(view);
            reference = (TextView) view.findViewById(R.id.guide_trainee_vt);
            name = (TextView) view.findViewById(R.id.guide_trainee_name);
            institute = (TextView) view.findViewById(R.id.guide_trainee_institute);
            branch = (TextView) view.findViewById(R.id.guide_trainee_branch);
            startDate=(TextView)view.findViewById(R.id.guide_trainee_startDate);
            endDate = (TextView)view.findViewById(R.id.guide_trainee_endDate);
        }
    }

}
