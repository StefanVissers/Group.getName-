package getname.group.project_4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends ActivityExtender {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeActivity(View view) {
        Intent intent = new Intent();
        boolean gotDestinationIntent = false;

        String tag = view.getTag().toString();
        int destination = Integer.parseInt(tag.split(",")[0]);

        if (destination == 1) {
            gotDestinationIntent = true;
            intent = new Intent(this, SecondActivity.class);
        } else if (destination == 2) {
            gotDestinationIntent = true;
            intent = new Intent(this, ThirdActivity.class);
        } else if (destination == 3) {
            gotDestinationIntent = true;
            intent = new Intent(this, CalenderActivity.class);
        }

        if (!gotDestinationIntent) {
            Log.d("[Main.changeActivity()]", "Didn't get destination intent");
            intent = new Intent();
            startActivity(intent);
        } else {
            Log.d("[Main.changeActivity()]", "Got destination intent");
            startActivity(intent);
        }
    }
}

