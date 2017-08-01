package madhukar.com.example.dell.snti;

/**
 * Created by Dell on 5/30/2017.
 */

public class guideData {

    private String guideName,pnumber,department,phone,email;
    private int imageId;

    public guideData(String guideName, String pnumber, String department, String phone, String email, int imageId) {
        this.guideName = guideName;
        this.pnumber = pnumber;
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.imageId = imageId;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmial(String emial) {
        this.email = emial;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
