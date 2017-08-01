package madhukar.com.example.dell.snti;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Dell on 6/1/2017.
 */

public class SNTI extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        if(FirebaseApp.getApps(this).isEmpty())
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
