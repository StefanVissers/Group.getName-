package getname.group.project_4;

import android.content.Context;
import android.util.Log;
import android.view.View;

/**
 * Created by stefan on 21-Jun-16.
 */
public class ToMainViewButtonListener extends MainActivity implements View.OnClickListener {
    private final Context mContext;

    ToMainViewButtonListener(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button3:
                Log.d("Button : ", "ViaAnotherClass");
            break;
        }

    }
}
