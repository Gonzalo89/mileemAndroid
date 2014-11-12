package com.g7.mileemandroid;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
//import org.achartengine.chartdemo.demo.R;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Estadisticas extends Activity {
	  /** Colors to be used for the pie slices. */
	  private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN };
	  /** The main series that will include all the data. */
	  private CategorySeries mSeries = new CategorySeries("");
	  /** The main renderer for the main dataset. */
	  private DefaultRenderer mRenderer = new DefaultRenderer();
	  /** Button for adding entered data to the current series. */
//	  private Button mAdd;
	  /** Edit text field for entering the slice value. */
//	  private EditText mValue;
	  /** The chart view that displays the data. */
	  private GraphicalView mChartView;

	  @Override
	  protected void onRestoreInstanceState(Bundle savedState) {
	    super.onRestoreInstanceState(savedState);
	    mSeries = (CategorySeries) savedState.getSerializable("current_series");
	    mRenderer = (DefaultRenderer) savedState.getSerializable("current_renderer");
	  }

	  @Override
	  protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    outState.putSerializable("current_series", mSeries);
	    outState.putSerializable("current_renderer", mRenderer);
	  }

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_estadisticas);
//	    mValue = (EditText) findViewById(R.id.xValue);
	 //   mRenderer.setZoomButtonsVisible(true);
	 //   mRenderer.setStartAngle(180);
	 //   mRenderer.setDisplayValues(true);

//	    mAdd = (Button) findViewById(R.id.add);
//	    mAdd.setEnabled(true);
//	    mValue.setEnabled(true);
//
/*	    mAdd.setOnClickListener(new View.OnClickListener() {
	      public void onClick(View v) {
	        double value = 0;
	        try {
	          value = Double.parseDouble(mValue.getText().toString());
	        } catch (NumberFormatException e) {
	          mValue.requestFocus();
	          return;
	        }
	        mValue.setText("");
	        mValue.requestFocus();
	        mSeries.add("Series " + (mSeries.getItemCount() + 1), value);
	        SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
	        renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
	        mRenderer.addSeriesRenderer(renderer);
	        mChartView.repaint();
	      }
	    });*/
	  }

	  @Override
	  protected void onResume() {
	    super.onResume();
	    if (mChartView == null) {
	      LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
	      //////////////////////////////
	      mSeries.add(25.3);
	      SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
	      renderer.setColor(Color.BLUE);
	      mRenderer.addSeriesRenderer(renderer);
	      
	      mSeries.add("aaaaaaaaaaaaaaaaaaaaaa", 10.5);
	      
	      SimpleSeriesRenderer renderer2 = new SimpleSeriesRenderer();
	      renderer2.setColor(Color.GREEN);
	      //renderer2.setDisplayBoundingPoints(false);
	      renderer2.setDisplayChartValues(false);
	      renderer2.setChartValuesTextSize(20);
	      renderer2.setShowLegendItem(true);
	     
	      
	      mRenderer.addSeriesRenderer(renderer2);
	      mRenderer.setLabelsTextSize(50);
	      mRenderer.setLegendHeight(10);
	      mRenderer.setLegendTextSize(60);
	      
	      ///////////////////////////////////
	      mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);
	      //mRenderer.setClickEnabled(true);
	     /* mChartView.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	          SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
	          if (seriesSelection == null) {
	            Toast.makeText(PieChartBuilder.this, "No chart element selected", Toast.LENGTH_SHORT)
	                .show();
	          } else {
	            for (int i = 0; i < mSeries.getItemCount(); i++) {
	              mRenderer.getSeriesRendererAt(i).setHighlighted(i == seriesSelection.getPointIndex());
	            }
	            mChartView.repaint();
	            Toast.makeText(
	                PieChartBuilder.this,
	                "Chart data point index " + seriesSelection.getPointIndex() + " selected"
	                    + " point value=" + seriesSelection.getValue(), Toast.LENGTH_SHORT).show();
	          }
	        }
	      });*/
	      layout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT,
	          LayoutParams.FILL_PARENT));
	    } else {
	      mChartView.repaint();
	    }
	  }
/*	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estadisticas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estadisticas, menu);
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
	}*/
}
