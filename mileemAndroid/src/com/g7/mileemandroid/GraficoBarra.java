package com.g7.mileemandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class GraficoBarra extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {	 
        View rootView = inflater.inflate(R.layout.activity_grafico_barra, container, false);
        return rootView;
	}

	@Override
	   public void onActivityCreated(Bundle bundle){		
		super.onActivityCreated(bundle); 
	     
		WebView webView = (WebView)getActivity().findViewById(R.id.web);
	        //webView.addJavascriptInterface(new WebAppInterface(), "Android");

	        webView.getSettings().setJavaScriptEnabled(true); 
	        webView.loadUrl("http://young-tundra-9853.herokuapp.com/columnchart");		
	}
}
