package CharBuilder;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;
import android.util.Log;
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
	private XYSeries mCurrentSeries[];
	/** The most recently created renderer, customizing the current series. */
	private XYSeriesRenderer mCurrentRenderer[];
	// number of XYSeries
	private int SeriesNum;
	// number of XYSeriesRenderer
	private int RendererNum;
	/** The chart view that displays the data. */
	private GraphicalView mChartView;

	public CharBuilderTest(int SerNum, int RendNum, SeriesSetting SS) {
		SeriesNum = SerNum;
		RendererNum = RendNum;
		mCurrentSeries = new XYSeries[SeriesNum];
		mCurrentRenderer = new XYSeriesRenderer[RendererNum];

		RendererSetting(mRenderer);

		SS.acc_setting(mCurrentSeries, mCurrentRenderer, mRenderer);
		for (int i = 0; i < 3; i++) {
			mDataset.addSeries(i, mCurrentSeries[i]);
			mRenderer.addSeriesRenderer(i, mCurrentRenderer[i]);
		}
	}

	interface SeriesSetting {
		void acc_setting(XYSeries series[], XYSeriesRenderer renderer[],
				XYMultipleSeriesRenderer mRenderer);
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

	}

	public View GetChart(Context context, List<double[]> In) {
		for (int i = 0; i < 3; i++) {
			mCurrentSeries[i].clear();
		}

		for (int i = 0; i < In.size(); i++) {
			// add a new data point to the current series
			mCurrentSeries[0].add(i, In.get(i)[0]);
			mCurrentSeries[1].add(i, In.get(i)[1]);
			mCurrentSeries[2].add(i, In.get(i)[2]);
		}

		// repaint the chart such as the newly added point to be visible
		mChartView.repaint();

		// View view = ChartFactory.getScatterChartView(context, mDataset,
		// mRenderer);
		View view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
		Log.d("CharPlot", "GetChar()");
		return view;
	}

}
