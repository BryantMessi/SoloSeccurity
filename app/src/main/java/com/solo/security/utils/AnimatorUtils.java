package com.solo.security.utils;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.solo.security.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Messi on 16-10-18.
 */

public class AnimatorUtils {
    public static void startRotateAnimator(Context context,View imageView,int typeAnim) {
        Animator animator = AnimatorInflater.loadAnimator(context,typeAnim);
        animator.setTarget(imageView);
        animator.start();
    }
}
