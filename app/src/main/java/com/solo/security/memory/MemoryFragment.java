package com.solo.security.memory;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.security.R;
import com.solo.security.data.Security;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoryFragment extends Fragment implements MemoryContract.DeepMemoryView, View.OnClickListener {

    private MemoryContract.DeepMemoryPresenter mPresenter;

    public MemoryFragment() {
        // Required empty public constructor
    }

    public static MemoryFragment newInstance(String param1, String param2) {
        MemoryFragment fragment = new MemoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memory, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_1).setOnClickListener(this);
        view.findViewById(R.id.btn_2).setOnClickListener(this);
        view.findViewById(R.id.btn_3).setOnClickListener(this);
        view.findViewById(R.id.btn_6).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setRunningProcessInfo(List<Security> securities) {
        Log.d("messi", "setRunningProcessInfo list :" + securities.size());
    }

    @Override
    public void setAvailableMemorySize(String size) {
        Log.d("messi", "setAvailableMemorySize size :" + size);
    }

    @Override
    public void setTotalMemorySize(String size) {
        Log.d("messi", "setTotalMemorySize size :" + size);
    }

    @Override
    public void setCurrentMemorySize(String size) {
        Log.d("messi", "setCurrentMemorySize size : " + size);
    }


    @Override
    public void killedRunningProcess() {
        Log.d("messi", "setTotalRunningProcessSize");
    }

    @Override
    public void setPresenter(@NonNull MemoryContract.BaseMemoryPresenter presenter) {
        Log.d("messi", "setPresenter");
        mPresenter = (MemoryContract.DeepMemoryPresenter) checkNotNull(presenter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_1:
                mPresenter.getAvailableMemorySize();
                break;
            case R.id.btn_2:
                mPresenter.getRunningProcessInfo();
                break;
            case R.id.btn_3:
                mPresenter.getTotalMemorySize();
                break;
            case R.id.btn_6:
                mPresenter.killRunningProcess();
                break;
        }
    }
}
