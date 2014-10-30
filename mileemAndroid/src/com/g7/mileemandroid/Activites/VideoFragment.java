package com.g7.mileemandroid.Activites;

import android.app.Fragment;
import com.g7.mileemandroid.R;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoFragment extends Fragment {
	 private VideoView videoView;
	 private MediaController mController;
	 private Uri uriYouTube;
	 
	   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_video, container,
				false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		videoView = (VideoView) getView().findViewById(
				R.id.videoView1);
		/*
		 * VideoView videoView =
		 * (VideoView)getView().findViewById(R.id.videoView1);
		 * //videoView.setVideoPath("/sdcard/blonde_secretary.3gp");
		 * //videoView.
		 * setVideoURI(Uri.parse("www.youtube.com/embed/16F4Jz3dtJQ"));
		 * videoView
		 * .setVideoPath("https://www.youtube.com/watch?v=16F4Jz3dtJQ");
		 * videoView.start();
		 */
		mController = new MediaController(getActivity());
		videoView.setMediaController(mController);
		videoView.requestFocus();
		/*
		 * Uri uri = Uri.parse("android.resource://" + this.getPackageName() +
		 * "/" + R.raw.sample); videoView.setVideoURI(uri); videoView.start();
		 */

		if (savedInstanceState != null) {
			int loc = savedInstanceState.getInt("Loc");
			Log.i("Loaction: ", loc + "");
			uriYouTube = Uri.parse(savedInstanceState.getString("url"));
			videoView.setVideoURI(uriYouTube);
			videoView.seekTo(loc);
			videoView.setOnPreparedListener(new OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mp) {
					Log.v("onPrepared", "ok");
					mp.start();
				}
			});
		} else {
			RTSPUrlTask truitonTask = new RTSPUrlTask();
			truitonTask.execute("http://www.youtube.com/watch?v=2zNSgSzhBfM");
		}

	}

	void startPlaying(String url) {
		uriYouTube = Uri.parse(url);
		videoView.setVideoURI(uriYouTube);
		videoView.start();
	}

/*	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("Loc", videoView.getCurrentPosition());
		outState.putString("url", uriYouTube.toString());
	}*/

	private class RTSPUrlTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			String response = getRTSPVideoUrl(urls[0]);
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			startPlaying(result);
		}

		public String getRTSPVideoUrl(String urlYoutube) {
			try {
				String gdy = "http://gdata.youtube.com/feeds/api/videos/";
				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				String id = extractYoutubeId(urlYoutube);
				URL url = new URL(gdy + id);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				Document doc = dBuilder.parse(connection.getInputStream());
				Element el = doc.getDocumentElement();
				NodeList list = el.getElementsByTagName("media:content");
				String cursor = urlYoutube;
				for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					if (node != null) {
						NamedNodeMap nodeMap = node.getAttributes();
						HashMap<String, String> maps = new HashMap<String, String>();
						for (int j = 0; j < nodeMap.getLength(); j++) {
							Attr att = (Attr) nodeMap.item(j);
							maps.put(att.getName(), att.getValue());
						}
						if (maps.containsKey("yt:format")) {
							String f = maps.get("yt:format");
							if (maps.containsKey("url"))
								cursor = maps.get("url");
							if (f.equals("1"))
								return cursor;
						}
					}
				}
				return cursor;
			} catch (Exception ex) {
				return urlYoutube;
			}
		}

		public String extractYoutubeId(String url) throws MalformedURLException {
			String query = new URL(url).getQuery();
			String[] param = query.split("&");
			String id = null;
			for (String row : param) {
				String[] param1 = row.split("=");
				if (param1[0].equals("v")) {
					id = param1[1];
				}
			}
			return id;
		}
	}
}
