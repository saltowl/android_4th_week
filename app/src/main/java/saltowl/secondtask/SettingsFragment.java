package saltowl.secondtask;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment
{
    private RadioGroup radioGroup;
    public static final String APP_PREFERENCES = "mysettings";
    final String KEY_RADIOBUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    public static SettingsFragment newInstance()
    {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.settings_activity, container, false);

        radioGroup = view.findViewById(R.id.searchGroup);
        radioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);

        LoadPreferences();

        return view;
    }

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id)
        {
            RadioButton rb = (RadioButton) radioGroup.findViewById(id);
            switch (id)
            {
                case -1:
                    showMessage(R.string.nothing_chosen);
                    break;
                case R.id.googleButton:
                    showMessage(R.string.google_search);
                    break;
                case R.id.yandexButton:
                    showMessage(R.string.yandex_search);
                    break;
                case R.id.bingButton:
                    showMessage(R.string.bing_search);
                    break;

                default:
                    break;
            }
            int checkedIndex = radioGroup.indexOfChild(rb);
            SavePreferences(KEY_RADIOBUTTON_INDEX, checkedIndex);
        }
    };

    private void SavePreferences(String key, int value)
    {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(
                APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value).apply();
    }

    private void LoadPreferences()
    {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(
                APP_PREFERENCES, MODE_PRIVATE);
        int savedRadioIndex = sharedPreferences.getInt(KEY_RADIOBUTTON_INDEX, 0);
        RadioButton savedCheckedRadioButton = (RadioButton) radioGroup
                .getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);
    }

    private void showMessage(@StringRes int string)
    {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
}
