package getname.group.project_4.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;

import getname.group.project_4.R;


public class NoteActivity extends ActivityExtender {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logDebugMessage("CREATE", this);

//        case R.id.share:
//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_TITLE, "");
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "");
//            sendIntent.setType("text/plain");
//            sendIntent.putExtra(Intent.EXTRA_TEXT, share.getText().toString());
//            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share_with)));
    }
}
