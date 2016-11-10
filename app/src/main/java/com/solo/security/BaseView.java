package com.solo.security;

import android.support.annotation.NonNull;

/**
 * Created by Messi on 16-11-3.
 */

public interface BaseView<T> {

    void setPresenter(@NonNull T presenter);
}
