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
	// private Button mAdd;
	/** Edit text field for entering the X value of the data to be added. */
	private EditText mX;
	/** Edit text field for entering the Y value of the data to be added. */
	private EditText mY;

	public CharBuilderTest() {

	}

	public View GetChart(Context context) {

		// repaint the chart such as the newly added point to be visible
		mChartView.repaint();

		View view = ChartFactory.getScatterChartView(context, mDataset,
				mRenderer);
		return view;

	}

}
