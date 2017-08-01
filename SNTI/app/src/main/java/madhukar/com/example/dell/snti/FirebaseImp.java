package madhukar.com.example.dell.snti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class FirebaseImp extends AppCompatActivity {

    Button sendData;
    DatabaseReference databaseReference;
   private EditText reference,name,institue,branch,startDate,endDate,projectTitle,guideName,contact,email;
    Button submitButton;
   private String refer,nam,insti,br,start,end,project,guide,con,eml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_imp);
        sendData = (Button)findViewById(R.id.submitButton);
        reference = (EditText)findViewById(R.id.firereference);
        name = (EditText)findViewById(R.id.fireName);
        institue = (EditText)findViewById(R.id.fireInstitute);
        branch = (EditText)findViewById(R.id.firebranch);
        startDate = (EditText)findViewById(R.id.fireStartDate);
        endDate = (EditText)findViewById(R.id.fireEndDate);
        projectTitle = (EditText)findViewById(R.id.fireProjectTitle);
        guideName = (EditText)findViewById(R.id.fireGuideName);
        contact = (EditText)findViewById(R.id.fireContact);
        email = (EditText)findViewById(R.id.fireEmail);
        submitButton = (Button)findViewById(R.id.submitButton);


//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://snti-c799a.firebaseio.com/User");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendData();

            }
        });


    }
    public void sendData()
    {
        refer=reference.getText().toString();
        nam=name.getText().toString();
        insti=institue.getText().toString();
        br=branch.getText().toString();
        start=startDate.getText().toString();
        end=(endDate).getText().toString();
        guide=(guideName).getText().toString();
        project=(projectTitle).getText().toString();
        con=contact.getText().toString();
        eml=email.getText().toString();

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://snti-c799a.firebaseio.com/Trainee");
        DatabaseReference uchild = databaseReference.child(refer);
        uchild.child("name").setValue(nam);
        uchild.child("institute").setValue(insti);
        uchild.child("branch").setValue(br);
        uchild.child("referenceNo").setValue(refer);
        uchild.child("startDate").setValue(start);
        uchild.child("endDate").setValue(end);
        uchild.child("guideName").setValue(guide);
        uchild.child("projectTitle").setValue(project);
        uchild.child("contact").setValue(con);
        uchild.child("email").setValue(eml);

    }
}
