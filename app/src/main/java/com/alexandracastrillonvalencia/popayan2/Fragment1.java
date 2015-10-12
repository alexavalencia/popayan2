package com.alexandracastrillonvalencia.popayan2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lenovo on 04/10/2015.
 */
public class Fragment1 extends android.support.v4.app.Fragment {
    public Fragment1(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.hoteles,container,false);
    }
}
