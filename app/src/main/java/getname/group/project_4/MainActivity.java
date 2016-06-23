package getname.group.project_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toGraphOne(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void toGraphTwo(View view){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    public void toGraphThree(View view){
        Intent intent = new Intent(this, BarChart.class);
        startActivity(intent);
    }

    public void toCalender(View view){
        Intent intent = new Intent(this, CalenderProxy.class);
        startActivity(intent);
    }

}

