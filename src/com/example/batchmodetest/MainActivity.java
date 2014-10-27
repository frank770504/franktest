package com.example.batchmodetest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.LinearLayout.LayoutParams;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

	SensorManager sensorManager;

    TextView textInfo;
    String strSensor;	
	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		//--Initializing accelerometer--//
	    sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		boolean accelerometerPresent;
		Sensor accelerometerSensor;
	    List<Sensor> sensor_TYPE_ACCELEROMETER_List = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
	    //////////////////////////////////
	    
	    if(sensor_TYPE_ACCELEROMETER_List.size() > 0){
          accelerometerPresent = true;
          accelerometerSensor = sensor_TYPE_ACCELEROMETER_List.get(0);
          
          strSensor  = "Name: " + accelerometerSensor.getName()
        		  + "\nVersion: " + String.valueOf(accelerometerSensor.getVersion())
        		  + "\nVendor: " + accelerometerSensor.getVendor()
        		  + "\nType: " + String.valueOf(accelerometerSensor.getType())
        		  + "\nMax: " + String.valueOf(accelerometerSensor.getMaximumRange())
        		  + "\nResolution: " + String.valueOf(accelerometerSensor.getResolution())
        		  + "\nPower: " + String.valueOf(accelerometerSensor.getPower())
        		  + "\nClass: " + accelerometerSensor.getClass().toString();

	    }
	    else{
	    	accelerometerPresent = false;
	    }	 
	    
	    setContentView(R.layout.activity_main);
	    textInfo = (TextView)findViewById(R.id.TextView_accInfo);
	    
	    if(strSensor.isEmpty())
	    	textInfo.setText("Info: ");
	    else
	    	textInfo.setText(strSensor);
	    
        
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
