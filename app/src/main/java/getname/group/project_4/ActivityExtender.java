package getname.group.project_4;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;

public abstract class ActivityExtender extends AppCompatActivity{
    public void changeActivity(AppCompatActivity activity) {
        Intent myIntent = new Intent(this, activity.getClass());
        startActivity(myIntent);
    }
}