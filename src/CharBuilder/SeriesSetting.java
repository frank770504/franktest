package CharBuilder;

import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public interface SeriesSetting {
	void acc_setting(XYSeries series[], XYSeriesRenderer renderer[],
			XYMultipleSeriesRenderer mRenderer);

}
