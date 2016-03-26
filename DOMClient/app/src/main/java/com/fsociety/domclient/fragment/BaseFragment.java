package com.fsociety.domclient.fragment;

import android.app.Fragment;

import com.fsociety.domclient.core.Application;

import org.androidannotations.annotations.EFragment;

/**
 * Created by atundrea on 3/26/2016.
 */
@EFragment
public class BaseFragment extends Fragment {
	protected Application application = Application.getInstance();
}
