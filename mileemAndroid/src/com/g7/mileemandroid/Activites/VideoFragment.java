package com.g7.mileemandroid.Activites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.Constantes;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoFragment extends YouTubePlayerFragment implements
YouTubePlayer.OnInitializedListener{
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {	 
        View rootView = inflater.inflate(R.layout.activity_fotos_slide, container, false);
        return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle bundle) {
		super.onActivityCreated(bundle);
	    YouTubePlayerView youTubeView = (YouTubePlayerView) getActivity().findViewById(R.id.youtube_view);
	    youTubeView.initialize(Constantes.APIKEYEXPORTAR, this);
	}

	private static final int RECOVERY_DIALOG_REQUEST = 1;

	  @Override
	  public void onInitializationFailure(YouTubePlayer.Provider provider,
	      YouTubeInitializationResult errorReason) {
	    if (errorReason.isUserRecoverableError()) {
	      errorReason.getErrorDialog(getActivity(), RECOVERY_DIALOG_REQUEST).show();
	    } else {
	      String errorMessage = errorReason.toString();
	      Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
	    }
	  }

	@Override
	public void onInitializationSuccess(Provider provider, YouTubePlayer player,
			boolean wasRestored) {
		//player.cueVideo(arg0)
			
	}	
	

}
