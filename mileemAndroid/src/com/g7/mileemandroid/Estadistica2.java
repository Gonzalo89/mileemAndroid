package com.g7.mileemandroid;

import java.util.ArrayList;

import com.dacer.androidcharts.BarView;
import com.dacer.androidcharts.PieHelper;
import com.dacer.androidcharts.PieView;
import com.g7.mileemandroid.Model.PropiedadSingleton;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Estadistica2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estadistica2);
		
		TextView barrio = (TextView)findViewById(R.id.textViewBarrio);		
		barrio.setText(PropiedadSingleton.getPropiedad().getBarrio());
		
		//Grafico de torta
		PieHelper amb1 = new PieHelper(20, Color.BLUE);		
		PieHelper amb2 = new PieHelper(30, Color.GREEN);
		PieHelper amb3 = new PieHelper(30, Color.RED);
		PieHelper amb4 = new PieHelper(20, Color.MAGENTA);		
		PieView pieView = (PieView)findViewById(R.id.pie_view);
		ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
		pieHelperArrayList.add(amb1);
		pieHelperArrayList.add(amb2);
		pieHelperArrayList.add(amb3);
		pieHelperArrayList.add(amb4);
		pieView.setDate(pieHelperArrayList);
		pieView.showPercentLabel(true);		
		
		//Grafico de barras
		BarView barView = (BarView)findViewById(R.id.bar_view);		
		ArrayList<String> strList = new ArrayList<String>();
		strList.add("barrio1");
		strList.add("barrio2");
		strList.add("barrio3");
		strList.add("barrio4");
		strList.add("barrio5");
		strList.add("barrio6");
		strList.add("barrio7");
		barView.setBottomTextList(strList);
		ArrayList<Integer> dataList = new ArrayList<Integer>();
		dataList.add(Integer.valueOf(20));
		dataList.add(Integer.valueOf(30));
		dataList.add(Integer.valueOf(40));
		dataList.add(Integer.valueOf(50));
		dataList.add(Integer.valueOf(10));
		dataList.add(Integer.valueOf(22));
		dataList.add(Integer.valueOf(12));
		barView.setDataList(dataList,50);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estadistica2, menu);
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
