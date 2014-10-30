package com.g7.mileemandroid.Activites;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.g7.mileemandroid.R;

public class VideoFragment extends Fragment {

	   @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
	         
	        return rootView;
	    }
	   
	   @Override
	    public void onActivityCreated(Bundle bundle){		
		super.onActivityCreated(bundle); 
		VideoView videoView = (VideoView)getView().findViewById(R.id.videoView1);
        //videoView.setVideoPath("/sdcard/blonde_secretary.3gp");
		//videoView.setVideoURI(Uri.parse("www.youtube.com/embed/16F4Jz3dtJQ"));
		videoView.setVideoPath("www.youtube.com/embed/16F4Jz3dtJQ");
        videoView.start();  
	   }
}
