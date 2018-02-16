package saltowl.secondtask;

import android.support.v4.app.Fragment;

public class FirstActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment getFragment()
    {
        return FirstActivityFragment.newInstance();
    }
}