package com.example.rushitaa.photo_app;

import android.app.Application;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseObject;


public class LikeAppAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Parse initialization with Authentication Key and Client Key
         */
        Parse.initialize(this, "Mmag6Cge5Y0zBnn25Eo6a3tGbElPZ4Hu4uPSVIMV", "DMltGVp4uk7KjLSp4m3rv9YoCIRKpq1sDzGLvxHG");

    }


}
