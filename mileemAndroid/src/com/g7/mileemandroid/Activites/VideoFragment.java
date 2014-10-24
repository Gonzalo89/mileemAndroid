package com.g7.mileemandroid.Activites;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.g7.mileemandroid.R;

public class VideoFragment extends Fragment {

	   @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
	         
	        return rootView;
	    }
}
