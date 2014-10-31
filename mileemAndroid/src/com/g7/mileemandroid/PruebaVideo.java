package com.g7.mileemandroid;

import android.os.Bundle;

import com.g7.mileemandroid.Activites.YouTubeFailureRecoveryActivity;
import com.g7.mileemandroid.Model.Constantes;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PruebaVideo extends YouTubeFailureRecoveryActivity {

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_prueba_video);

	    YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
	    youTubeView.initialize(Constantes.APIKEYEXPORTAR, this);
	  }

	  @Override
	  public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
	      boolean wasRestored) {
	    if (!wasRestored) {
	      player.cueVideo("wKJ9KzGQq0w");
	    }
	  }

	  @Override
	  protected YouTubePlayer.Provider getYouTubePlayerProvider() {
	    return (YouTubePlayerView) findViewById(R.id.youtube_view);
	  }

	}