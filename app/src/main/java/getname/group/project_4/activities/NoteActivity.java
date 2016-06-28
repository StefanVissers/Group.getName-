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

//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.io.OutputStreamWriter;
//import getname.group.project_4.R;

public class NoteActivity extends ActivityExtender {

//    /** Called when the activity is first created. */
//    private final static String STORETEXT="storetext.txt";
//    private EditText txtEditor;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_note);
//        txtEditor=(EditText)findViewById(R.id.textbox);
//
//        Intent sendIntent = new Intent(this.getIntent().getAction());
//        startActivity(sendIntent);
//    }
//    public void saveClicked(View v) {
//        try {
//            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(STORETEXT, 0));
//            out.write(txtEditor.getText().toString());
//            out.close();
//            Toast
//                    .makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG)
//                    .show();
//        }
//        catch (Throwable t) {
//            Toast
//                    .makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG)
//                    .show();
//        }
//    }
}