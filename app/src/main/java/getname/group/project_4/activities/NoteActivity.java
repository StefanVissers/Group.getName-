package getname.group.project_4.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class NoteActivity extends ActivityExtender {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("[Note]", "Creating Note");
    }
    //send
}
