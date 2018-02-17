package saltowl.secondtask;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class SearchFragment extends Fragment
{
    public static final String APP_PREFERENCES = "mysettings";
    final String KEY_RADIOBUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";
    final String KEY_RADIOBUTTON_VALUE = "SAVED_RADIO_BUTTON_VALUE";

    private EditText mEditText;
    private Button mSearch;
    private SharedPreferences mSharedPreferences;
    private int savedSearch;
    private String searcher;

    public static SearchFragment newInstance()
    {
        return new SearchFragment();
    }

    private View.OnClickListener mOnSearchClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            try
            {
                Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(searcher
                        + mEditText.getText()));
                startActivity(browseIntent);
            }
            catch (ActivityNotFoundException e)
            {
                showMessage(R.string.error_browser);
                e.printStackTrace();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.search_activity, container, false);

        mEditText = view.findViewById(R.id.searchText);
        mSearch = view.findViewById(R.id.searchButton);

        mSharedPreferences = getContext().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        savedSearch = mSharedPreferences.getInt(KEY_RADIOBUTTON_INDEX, 0);
        searcher = mSharedPreferences.getString(KEY_RADIOBUTTON_VALUE, "");

        mSearch.setOnClickListener(mOnSearchClickListener);

        return view;
    }

    private void showMessage(@StringRes int string)
    {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
}
