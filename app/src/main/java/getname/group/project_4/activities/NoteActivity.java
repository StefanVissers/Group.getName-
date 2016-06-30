package getname.group.project_4.activities;



//package getname.group.project_4.activities;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.
//import android.widget.EditText;
//
//import getname.group.project_4.R;
//
//
//public class NoteActivity extends ActivityExtender {
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.e("[Note]", "Creating Note");
//
////        case R.id.share:
//            Intent sendIntent = new Intent();
//            sendIntent.setType("vnd.android.cursor.item/event");
//            startActivity(sendIntent);
//    }
//}

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import java.util.List;
import getname.group.project_4.R;
import getname.group.project_4.debug.LogHelper;

import com.evernote.android.intent.CreateNewNoteIntentBuilder;
import com.evernote.android.intent.EvernoteIntent;

public class NoteActivity extends ActivityExtender {
    private static int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("[Note]", "Creating Note");

        // Creating debug message
        ID = counter;
        LogHelper.logDebugMessage("CREATE", this);

        // Set activity to activity_note
        setContentView(R.layout.activity_note);
    }

    private void savePlainTextNote(String Longitude, String Latitude) {
        Intent intent = EvernoteIntent.createNewNote()
                .setTitle("Location")
                .addTags("Location")
                .setTextPlain("Longitude: " + Longitude + " Latitude: " + Latitude)
                .setSourceApp(getPackageName())
                .setAppVisibility(CreateNewNoteIntentBuilder.AppVisibility.QUICK_SEND)
                .create();

        startActivity(intent);
    }
}
