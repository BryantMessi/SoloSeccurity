package com.solo.security.garbage;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.solo.security.R;
import com.solo.security.utils.PermissionsChecker;


public class GarbageFragment extends Fragment implements GarbageContract.DeepGarbageView, View.OnClickListener {

    private GarbageContract.DeepGarbagePresenter mPreseneter;

    public GarbageFragment() {
        // Required empty public constructor
    }


    public static GarbageFragment newInstance(String param1, String param2) {
        GarbageFragment fragment = new GarbageFragment();
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
        return inflater.inflate(R.layout.fragment_garbage, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_1).setOnClickListener(this);
        view.findViewById(R.id.btn_2).setOnClickListener(this);
        view.findViewById(R.id.btn_3).setOnClickListener(this);
        view.findViewById(R.id.btn_4).setOnClickListener(this);
        view.findViewById(R.id.btn_5).setOnClickListener(this);
        view.findViewById(R.id.btn_6).setOnClickListener(this);
        view.findViewById(R.id.btn_7).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPreseneter.start();
        isHavePermissions();
    }

    @Override
    public void setAdFilesDetail() {
        Log.d("messi", "setAdFilesDetail");
    }

    @Override
    public void setCacheFilesDetail() {
        Log.d("messi", "setCacheFilesDetail");
    }

    @Override
    public void setTempFilesDetail() {
        Log.d("messi", "setTempFilesDetail");
    }

    @Override
    public void setResidualFilesDetail() {
        Log.d("messi", "setResidualFilesDetail");
    }

    @Override
    public void setMemoryFilesDetail() {
        Log.d("messi", "setMemoryFilesDetail");
    }

    @Override
    public void setInstalledPackages() {
        Log.d("messi", "setInstalledPackages");
    }

    @Override
    public void setBigFilesDetail() {
        Log.d("messi", "setBigFilesDetail");
    }

    @Override
    public void setCurrentGarbageSize() {
        Log.d("messi", "setCurrentGarbageSize");
    }

    @Override
    public void setPresenter(@NonNull GarbageContract.BaseGarbagePresenter presenter) {
        mPreseneter = (GarbageContract.DeepGarbagePresenter) Preconditions.checkNotNull(presenter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_1:
                mPreseneter.loadAdFiles();
                break;
            case R.id.btn_2:
                mPreseneter.loadCacheFiles();
                break;
            case R.id.btn_3:
                mPreseneter.loadTempFiles();
                break;
            case R.id.btn_4:
                mPreseneter.loadResidualFiles();
                break;
            case R.id.btn_5:
                mPreseneter.loadInstalledPackages();
                break;
            case R.id.btn_6:
                mPreseneter.loadMemoryFiles();
                break;
            case R.id.btn_7:
                mPreseneter.loadBigFiles();
                break;
        }
    }

    public boolean isHavePermissions() {
        try {
            PermissionsChecker mPermissionChecker = new PermissionsChecker(getContext());
            if (mPermissionChecker.lacksPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.GET_PACKAGE_SIZE)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.GET_PACKAGE_SIZE}, 1);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Log.d("messi", "permission exception : " + e.getMessage());
            return true;
        }
    }
}
