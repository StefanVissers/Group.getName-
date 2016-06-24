package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class PieChart extends Chart{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
    }

    @Override
    public void draw() {

    }
}
