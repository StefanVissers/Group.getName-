package getname.group.project_4.SQL;

import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.*;

import java.sql.Array;

/**
 * Created by floris-jan on 27-06-16.
 */
public class DatabaseReader extends Fragment {
    SQLiteDatabase mydatabase = getActivity().openOrCreateDatabase(":shared/libs/Data.db", android.content.Context.MODE_PRIVATE, null);

    public Array DatabaseReader(String query) {
        return null;
    }
}
