package com.g7.mileemandroid.Activites;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.Constantes;
import com.g7.mileemandroid.Model.PropiedadSingleton;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeFailureRecoveryActivity {
	private List<String> listaVideos;
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_video);
	   
	    YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
	    youTubeView.setBackgroundColor(Color.BLACK);
	    youTubeView.initialize(Constantes.APIKEYEXPORTAR, this);
	  }

	  @Override
	  public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
	      boolean wasRestored) {
	    if (!wasRestored) {
	    	if(PropiedadSingleton.getPropiedad().getCantVideos() >= 1){
	    		listaVideos = new ArrayList<String>();
	    		for(int i = 0; i < PropiedadSingleton.getPropiedad().getCantVideos() ; i++) {
	    			listaVideos.add(PropiedadSingleton.getPropiedad().getIdVideos()[i]);
	    		}
	    		player.cueVideos(listaVideos);
	    	}else{
	    		//player.cueVideo("wKJ9KzGQq0w");
	    		Toast toast1 =
	    				Toast.makeText(getApplicationContext(),
	    						"No hay videos", Toast.LENGTH_SHORT);
	    			toast1.show();
	    	}	
	    }
	  }

	  @Override
	  protected YouTubePlayer.Provider getYouTubePlayerProvider() {
	    return (YouTubePlayerView) findViewById(R.id.youtube_view);
	  }

	}