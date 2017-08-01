package madhukar.com.example.dell.snti;

/**
 * Created by Dell on 5/28/2017.
 */

public class TraineeDetails {
    private String referenceNo;
    private String name;
    private String institute;
    private String branch;
    private String startDate;
    private String endDate;
    private String projectTitle;
    private String guideName;
    private String contact;
    private String email;

    public TraineeDetails()
    {

    }

    public TraineeDetails(String referenceNo, String name, String institute, String branch, String startDate, String endDate, String projectTitle, String guideName, String contact, String email) {
        this.referenceNo = referenceNo;
        this.name = name;
        this.institute = institute;
        this.branch = branch;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectTitle = projectTitle;
        this.guideName = guideName;
        this.contact = contact;
        this.email = email;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
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

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
