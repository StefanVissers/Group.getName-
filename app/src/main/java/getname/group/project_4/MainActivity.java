package getname.group.project_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    String msg = "Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toMainViewButton = (Button)findViewById(R.id.button3);
        toMainViewButton.setOnClickListener(new ToMainViewButtonListener(this));

        Log.d(msg, " The onCreate() event");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "the onPause() event");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "the onStop() event");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(msg, "the onDestroy() event");
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

    public void buttonOnClick(View view){
        setContentView(R.layout.secondary_activity);
        Log.d(msg, "To Secondary View");
    }
    public void onButtonClick(View view){
        setContentView(R.layout.activity_main);
        Log.d(msg, "To Main View");
    }

}

