package com.eddyfidel.marvella.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by eddyfidel on 10/24/17.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment,
                                             int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(frameId, fragment);
        transaction.commit();
    }

}
