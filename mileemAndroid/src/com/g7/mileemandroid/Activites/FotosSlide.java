package com.g7.mileemandroid.Activites;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.Propiedad;
import com.g7.mileemandroid.Model.PropiedadSingleton;

public class FotosSlide extends ActionBarActivity {
	private ImageSwitcher imageSwitcher;
	private int position = 0;
	private Propiedad propiedad = PropiedadSingleton.getPropiedad();
	private int cantFotos = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fotos_slide);		
		cantFotos = propiedad.getCantFotos();
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
		imageSwitcher.setFactory(new ViewFactory() {

			public View makeView() {
				ImageView iv = new ImageView(FotosSlide.this);
				iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

			    LayoutParams params = new ImageSwitcher.LayoutParams(
			      LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			    iv.setLayoutParams(params);		
				return iv;
			}
		});

		// Set animations
		Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		imageSwitcher.setInAnimation(fadeIn);
		imageSwitcher.setOutAnimation(fadeOut);
		if (cantFotos > 0) {
			imageSwitcher.setImageDrawable(new BitmapDrawable(this.getResources(), propiedad
					.getFotosCompleta()[position]));
		}
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fotos_slide, menu);
		return true;
	}*/

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
		// imageSwitcher.setImageResource(gallery[position]);
		position++;
		if (position == cantFotos) {
			position = 0;
		}
		if (propiedad.getFotosCompleta() != null)
			imageSwitcher.setImageDrawable(new BitmapDrawable(this.getResources(), propiedad
					.getFotosCompleta()[position]));
	}
}
