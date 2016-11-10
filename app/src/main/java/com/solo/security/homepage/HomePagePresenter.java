package com.solo.security.homepage;

/**
 * Created by Messi on 16-11-5.
 */

public class HomePagePresenter implements HomePageContract.Presenter {


    @Override
    public void checkLaunchAnim() {
        //TODO:检测是否是第一次启动，决定展示界面
    }

    @Override
    public void checkSafeScanEnable() {
        //TODO:判断网络状况，决定安全云查杀是否可用
    }

    @Override
    public void startScan() {
        //TODO：开启一键扫描
    }

    @Override
    public void fix() {
        //TODO：修复单项
    }

    @Override
    public void fixAll() {
        //TODO:全部修复
    }

    @Override
    public void start() {

    }
}
