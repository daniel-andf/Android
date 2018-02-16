package com.example.daniel.imageqa;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.config.AWSConfiguration;

/**
 * Created by danie on 11/2/2017.
 */

public class AppInit extends MultiDexApplication {
        private static final String LOG_TAG = Application.class.getSimpleName();
        public static AWSConfiguration awsConfiguration;


        @Override
        public void onCreate() {
            super.onCreate();
            initializeApplication();

        }

        private void initializeApplication() {

            awsConfiguration = new AWSConfiguration(getApplicationContext());

            // If IdentityManager is not created, create it
            if (IdentityManager.getDefaultIdentityManager() == null) {
                IdentityManager identityManager =
                        new IdentityManager(getApplicationContext(), awsConfiguration);
                IdentityManager.setDefaultIdentityManager(identityManager);
            }

        }
    }
