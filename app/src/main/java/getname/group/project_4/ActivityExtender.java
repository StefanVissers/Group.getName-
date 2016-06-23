package getname.group.project_4;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public abstract class ActivityExtender extends AppCompatActivity{
    public void changeActivity(AppCompatActivity activity) {
        Intent myIntent = new Intent(this, activity.getClass());
        startActivity(myIntent);
    }
}