package madhukar.com.example.dell.snti;

/**
 * Created by Dell on 5/30/2017.
 */

public class TraineeUnderGuideData {
    private String reference,name,institute,branch,startDate,endDate;
    private int index;

    public TraineeUnderGuideData(int index,String reference, String name, String institute, String branch,String startDate,String endDate) {
        this.reference = reference;
        this.name = name;
        this.institute = institute;
        this.branch = branch;
        this.index = index;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public String getReference() {
        return reference;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
