package com.solo.security.whitelist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.solo.security.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WhiteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WhiteListFragment extends Fragment implements WhiteListContract.View {

    private WhiteListContract.Presenter mPresenter;

    public WhiteListFragment() {
        // Required empty public constructor
    }

    public static WhiteListFragment newInstance(String param1, String param2) {
        WhiteListFragment fragment = new WhiteListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_white_list, container, false);
    }

    @Override
    public void showWhiteListPage() {

    }

    @Override
    public void showAddPage() {

    }

    @Override
    public void setPresenter(@NonNull WhiteListContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }
}
