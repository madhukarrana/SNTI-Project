package madhukar.com.example.dell.snti;

import android.content.res.Resources;
import android.content.res.TypedArray;


public class Painting {

    private final int imageId;
    private final String title;
    private final String designation;
    private final String contact;
    private final String email;
    private final String description;

    public String getDesignation() {
        return designation;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }



    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public Painting(int imageId, String title, String designation, String contact, String email, String description) {
        this.imageId = imageId;
        this.title = title;
        this.designation = designation;
        this.contact = contact;
        this.email = email;
        this.description = description;
    }

    public static Painting[] getAllPaintings(Resources res) {
        String[] titles = res.getStringArray(R.array.paintings_titles);
        String[] designations = res.getStringArray(R.array.paintings_designation);
        String[] contacts = res.getStringArray(R.array.paintings_contact);
        TypedArray images = res.obtainTypedArray(R.array.paintings_images);
        String[] emails = res.getStringArray(R.array.paintings_email);
        String[] descriptions=res.getStringArray(R.array.paintings_description);

        int size = titles.length;
        Painting[] paintings = new Painting[size];

        for (int i = 0; i < size; i++) {
            final int imageId = images.getResourceId(i, -1);
            paintings[i] = new Painting(imageId, titles[i], designations[i], contacts[i],emails[i],descriptions[i]);
        }

        images.recycle();

        return paintings;
    }

}
