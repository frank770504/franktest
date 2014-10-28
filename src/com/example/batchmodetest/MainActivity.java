package com.example.batchmodetest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

	SensorManager sensorManager;
	boolean accelerometerPresent;
	TextView textInfo;
	String strSensor;

	LinearLayout Chart;

	// --Char Related Declaration--//
	/** The main dataset that includes all the series that go into a chart. */
	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
	/** The main renderer that includes all the renderers customizing a chart. */
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(3);
	/** The most recently added series. */

	private XYSeries mSeries_accx_0;
	private XYSeries mSeries_accy_1;
	private XYSeries mSeries_accz_2;

	private XYSeries mSeries_3Lines[] = new XYSeries[3];

	/** The most recently created renderer, customizing the current series. */
	private XYSeriesRenderer mRenderer_accx_0;
	private XYSeriesRenderer mRenderer_accy_1;
	private XYSeriesRenderer mRenderer_accz_2;

	private XYSeriesRenderer mRenderer_3Lines[] = new XYSeriesRenderer[3];
	/** The chart view that displays the data. */
	private GraphicalView mChartView;
	private Intent intent;

	// //////////////////////////////

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// --Initializing accelerometer--//
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor accelerometerSensor;
		List<Sensor> sensor_TYPE_ACCELEROMETER_List = sensorManager
				.getSensorList(Sensor.TYPE_ACCELEROMETER);
		// ////////////////////////////////

		if (sensor_TYPE_ACCELEROMETER_List.size() > 0) {
			accelerometerPresent = true;
			accelerometerSensor = sensor_TYPE_ACCELEROMETER_List.get(0);

			strSensor = "Name: " + accelerometerSensor.getName()
					+ "\nVersion: "
					+ String.valueOf(accelerometerSensor.getVersion())
					+ "\nVendor: " + accelerometerSensor.getVendor()
					+ "\nType: "
					+ String.valueOf(accelerometerSensor.getType()) + "\nMax: "
					+ String.valueOf(accelerometerSensor.getMaximumRange())
					+ "\nResolution: "
					+ String.valueOf(accelerometerSensor.getResolution())
					+ "\nPower: "
					+ String.valueOf(accelerometerSensor.getPower())
					+ "\nClass: " + accelerometerSensor.getClass().toString();

		} else {
			accelerometerPresent = false;
		}

		setContentView(R.layout.activity_main);
		textInfo = (TextView) findViewById(R.id.TextView_SensorInfo);

		if (strSensor.isEmpty())
			textInfo.setText("Info: ");
		else
			textInfo.setText(strSensor);

		RendererSetting(mRenderer);
		// --Initial Char--//
		// intent = ChartFactory.getScatterChartIntent(this, mDataset,
		// mRenderer);
		// startActivity(intent);
		mChartView = ChartFactory
				.getScatterChartView(this, mDataset, mRenderer);
		mChartView.repaint();
		// //////////////////
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.d("CharPlot", "START--onResume()");
		super.onResume();
		double[] a = { 0.1, 0.2, 0.3 };
		List<double[]> aList = new ArrayList<double[]>();
		for (int i = 0; i < 20; i++) {
			aList.add(a);
		}
		Chart = (LinearLayout) this.findViewById(R.id.chart);

		if (accelerometerPresent) {
			/*
			 * Accelerometer usage -- old
			 * //sensorManager.registerListener(accelerometerListener,
			 * accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
			 */
			Chart.removeAllViews();
			Chart.addView(GetChart(this, aList), new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			Toast.makeText(this, "Register accelerometerListener",
					Toast.LENGTH_LONG).show();
			Log.d("CharPlot", "END--onResume()");
		}
	}

	public void Draw_acc_Setting(XYSeries mSeries_3Lines[],
			XYSeriesRenderer mRenderer_3Lines[],
			XYMultipleSeriesRenderer mRenderer) {
		for (int i = 0; i < 3; i++) {
			mRenderer.setYAxisMin(-1, i);
			mRenderer.setYAxisMax(1, i);
		}
		// create a new series of data
		mSeries_accx_0 = new XYSeries("x", 0);
		mSeries_accy_1 = new XYSeries("y", 1);
		mSeries_accz_2 = new XYSeries("z", 2);

		mSeries_3Lines[0] = mSeries_accx_0;
		mSeries_3Lines[1] = mSeries_accy_1;
		mSeries_3Lines[2] = mSeries_accz_2;

		// create a new renderer for the new series
		mRenderer_accx_0 = new XYSeriesRenderer();
		mRenderer_accx_0.setPointStyle(PointStyle.CIRCLE);
		mRenderer_accx_0.setFillPoints(true);
		mRenderer_accx_0.setColor(Color.BLUE);

		mRenderer_accy_1 = new XYSeriesRenderer();
		mRenderer_accy_1.setPointStyle(PointStyle.DIAMOND);
		mRenderer_accy_1.setFillPoints(true);
		mRenderer_accy_1.setColor(Color.RED);

		mRenderer_accz_2 = new XYSeriesRenderer();
		mRenderer_accz_2.setPointStyle(PointStyle.TRIANGLE);
		mRenderer_accz_2.setFillPoints(true);
		mRenderer_accz_2.setColor(Color.GREEN);

		mRenderer_3Lines[0] = mRenderer_accx_0;
		mRenderer_3Lines[1] = mRenderer_accy_1;
		mRenderer_3Lines[2] = mRenderer_accz_2;
	}

	public void RendererSetting(XYMultipleSeriesRenderer mRenderer) {
		// --Chart Related Initial--//

		// set some properties on the main renderer
		// mRenderer.setBackgroundColor(Color.GRAY);
		mRenderer.setMarginsColor(Color.LTGRAY);
		// mRenderer.setShowLabels(false);
		for (int i = 0; i < 3; i++) {
			mRenderer.setXAxisMin(0, i);
			mRenderer.setXAxisMax(50, i);
		}
		// mRenderer.setLabelsColor(Color.MAGENTA);
		mRenderer.setXLabelsColor(Color.BLACK);
		mRenderer.setYLabelsColor(0, Color.BLACK);
		mRenderer.setYLabelsColor(1, Color.BLACK);
		mRenderer.setYLabelsColor(2, Color.BLACK);

		mRenderer.setShowGrid(true);
		mRenderer.setXLabels(50);
		mRenderer.setYLabels(20);

		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
		mRenderer.setAxisTitleTextSize(16);
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setLabelsTextSize(18);
		mRenderer.setLegendTextSize(20);
		mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setPointSize(5);

		mRenderer.setInScroll(true);

		Draw_acc_Setting(mSeries_3Lines, mRenderer_3Lines, mRenderer);
		for (int i = 0; i < 3; i++) {
			mDataset.addSeries(i, mSeries_3Lines[i]);
			mRenderer.addSeriesRenderer(i, mRenderer_3Lines[i]);
		}
	}

	public View GetChart(Context context, List<double[]> In) {
		for (int i = 0; i < 3; i++)
			mSeries_3Lines[i].clear();

		for (int i = 0; i < In.size(); i++) {
			// add a new data point to the current series
			mSeries_3Lines[0].add(i, In.get(i)[0]);
			mSeries_3Lines[1].add(i, In.get(i)[1]);
			mSeries_3Lines[2].add(i, In.get(i)[2]);
		}
		// mSeries_3Lines[0].add(In.size()-1, In.get(In.size()-1).x);
		// mSeries_3Lines[1].add(In.size()-1, In.get(In.size()-1).y);
		// mSeries_3Lines[2].add(In.size()-1, In.get(In.size()-1).z);
		// repaint the chart such as the newly added point to be visible
		mChartView.repaint();

		// View view = ChartFactory.getScatterChartView(context, mDataset,
		// mRenderer);
		View view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
		Log.d("CharPlot", "GetChar()");
		return view;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
