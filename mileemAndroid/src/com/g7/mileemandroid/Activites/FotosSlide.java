package com.g7.mileemandroid.Activites;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

import com.g7.mileemandroid.R;

public class FotosSlide extends ActionBarActivity {
	
	   private ImageSwitcher imageSwitcher;
	   
	    private int[] gallery = {R.drawable.casa1, R.drawable.casa2, R.drawable.casa3};
	     
	    private int position = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fotos_slide);
		
		 imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
         
	        imageSwitcher.setFactory(new ViewFactory() {
	              
	             public View makeView() 
	             {
	                 return new ImageView(FotosSlide.this);
	             } 
	         });
	 
	         // Set animations
	         Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
	         Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
	         imageSwitcher.setInAnimation(fadeIn);
	         imageSwitcher.setOutAnimation(fadeOut); 
	         imageSwitcher.setImageResource(R.drawable.casa1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fotos_slide, menu);
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
	
	public void onClickSiguiente(View view) {
		imageSwitcher.setImageResource(gallery[position]);
        position++;
        if (position == 3)
        {
            position = 0;
        }
	}
}
