package com.g7.mileemandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class PruebaWebView extends Activity {
	WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prueba_web_view);
		
		 webView = (WebView)findViewById(R.id.web);
	    //    webView.addJavascriptInterface(new WebAppInterface(), "Android");

	        webView.getSettings().setJavaScriptEnabled(true); 
	        try{
	        	webView.loadUrl("http://young-tundra-9853.herokuapp.com/piechart");
	        	//webView.loadUrl("http://young-tundra-9853.herokuapp.com/propiedads/filtro.json?g7=2");
	        }catch(Exception ex){
	        	Log.e("error", "Error en webView: " + ex.getMessage());
	        }
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prueba_web_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
