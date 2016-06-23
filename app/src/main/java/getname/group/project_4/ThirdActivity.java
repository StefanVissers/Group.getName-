package getname.group.project_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class ThirdActivity extends ActivityExtender {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.third_activity);
    }
}
