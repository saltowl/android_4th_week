package saltowl.secondtask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FirstActivityFragment extends Fragment
{
    public static FirstActivityFragment newInstance()
    {

        Bundle args = new Bundle();
        FirstActivityFragment fragment = new FirstActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_first, container, false);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.actionSettings:
                showMessage(R.string.menu_settings);
                break;
            case R.id.actionSearch:
                showMessage(R.string.menu_search);
                break;
            case R.id.actionExit:
                showMessage(R.string.menu_exit);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(@StringRes int string)
    {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
}
