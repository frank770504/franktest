package CharBuilder;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.Toast;
import android.content.Context;

public class CharBuilderTest {
	  /** The main dataset that includes all the series that go into a chart. */
	  private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
	  /** The main renderer that includes all the renderers customizing a chart. */
	  private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	  /** The most recently added series. */
	  private XYSeries mCurrentSeries;
	  /** The most recently created renderer, customizing the current series. */
	  private XYSeriesRenderer mCurrentRenderer;
	  /** The chart view that displays the data. */
	  private GraphicalView mChartView;	  
	  /** Button for adding entered data to the current series. */
	  //private Button mAdd;
	  /** Edit text field for entering the X value of the data to be added. */
	  private EditText mX;
	  /** Edit text field for entering the Y value of the data to be added. */
	  private EditText mY;	  
	  public CharBuilderTest(){
		    // set some properties on the main renderer
		    mRenderer.setApplyBackgroundColor(true);
		    mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
		    mRenderer.setAxisTitleTextSize(16);
		    mRenderer.setChartTitleTextSize(20);
		    mRenderer.setLabelsTextSize(15);
		    mRenderer.setLegendTextSize(15);
		    mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
		    mRenderer.setZoomButtonsVisible(true);
		    mRenderer.setPointSize(5);
		
	        String seriesTitle = "Series " + (mDataset.getSeriesCount() + 1);
	        // create a new series of data
	        XYSeries series = new XYSeries(seriesTitle);
	        mDataset.addSeries(series);
	        mCurrentSeries = series;
	        // create a new renderer for the new series
	        XYSeriesRenderer renderer = new XYSeriesRenderer();
	        mRenderer.addSeriesRenderer(renderer);
	        // set some renderer properties
	        renderer.setPointStyle(PointStyle.CIRCLE);
	        renderer.setFillPoints(true);
	        renderer.setDisplayChartValues(true);
	        renderer.setDisplayChartValuesDistance(10);
	        mCurrentRenderer = renderer;
	        //setSeriesWidgetsEnabled(true);
	        //mChartView.repaint();		    
	  }
	  
	  public View GetChart(Context context){
	        double x = 10;
	        double y = 20;
	        try {
	          x = Double.parseDouble(mX.getText().toString());
	        } catch (NumberFormatException e) {
	          mX.requestFocus();
	          //return;
	        }
	        try {
	          y = Double.parseDouble(mY.getText().toString());
	        } catch (NumberFormatException e) {
	          mY.requestFocus();
	          //return;
	        }
	        // add a new data point to the current series
	        mCurrentSeries.add(x, y);
	        mX.setText("");
	        mY.setText("");
	        mX.requestFocus();
	        // repaint the chart such as the newly added point to be visible
	        mChartView.repaint();	  
		  
		  View view = ChartFactory.getScatterChartView(context, mDataset, mRenderer);  
		  return view;
		  
	  }
	  /**
	   * Enable or disable the add data to series widgets
	   * 
	   * @param enabled the enabled state
	   */
//	  private void setSeriesWidgetsEnabled(boolean enabled) {
//	    mX.setEnabled(enabled);
//	    mY.setEnabled(enabled);
//	    //mAdd.setEnabled(enabled);
//	  }
}
