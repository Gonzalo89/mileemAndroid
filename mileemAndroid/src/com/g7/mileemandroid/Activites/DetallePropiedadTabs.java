package com.g7.mileemandroid.Activites;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.g7.mileemandroid.Estadistica2;
import com.g7.mileemandroid.R;
import com.g7.mileemandroid.Model.PropiedadSingleton;

public class DetallePropiedadTabs extends Activity implements
		ActionBar.TabListener {
	
	private FotosSlide fotosSlide = null;
	private DetallePropiedad detallePropiedad = null;
	private VideoFragment videoFragment = null;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_propiedad_tabs);
		setTitle("  Detalle de Propiedad");
		fotosSlide = new FotosSlide();

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_propiedad_tabs, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.estadisticas_menu) {			
			Intent intent = new Intent(this, Estadistica2.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		switch (tab.getPosition()) {
		case 0:
			mViewPager.setCurrentItem(tab.getPosition());
			break;
		case 1:
			/*Intent intent1 = new Intent(this, FotosSlide.class);
			startActivity(intent1);*/
			mViewPager.setCurrentItem(tab.getPosition());
			break;
		case 2:
			if(PropiedadSingleton.getPropiedad().getCantVideos() >= 1) {
				Intent intent2 = new Intent(this, VideoActivity.class);
				startActivity(intent2);	
			}else{
	    		Toast toast1 =
	    				Toast.makeText(getApplicationContext(),
	    						"No hay videos", Toast.LENGTH_SHORT);

	    			toast1.show();
			}

			break;
		}
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
	        switch (position) {
	        case 0:
	        	detallePropiedad = new DetallePropiedad();
	            return detallePropiedad;
	        case 1:
	        	return fotosSlide;
	        case 2:
	        	detallePropiedad = new DetallePropiedad();//FIXME
	            return detallePropiedad;
	        }
	 
	        return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.Detalle);
			case 1:
				return getString(R.string.Fotos);
			case 2:
				return getString(R.string.Video);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_detalle_propiedad_tabs, container, false);
			return rootView;
		}
	}

	public void onClickSiguiente(View view) {
		if (this.fotosSlide != null) {
			this.fotosSlide.onClickSiguiente(view);
		}else{
			Log.e("Error", "fotosSlide es null en DetallePropiedadTabs");
		}
	}
	
	public void onClickContactar(View view) {
		if (this.detallePropiedad != null)
			this.detallePropiedad.onClickContactar(view);
	}
	
	public void onClickVerEnMapa(View view) {
		if (this.detallePropiedad != null)
			this.detallePropiedad.onClickVerEnMapa(view);
	}
	
	public void onClickShare(View view) {
		if (this.detallePropiedad != null)
			this.detallePropiedad.onClickShare(view);
	}
}
