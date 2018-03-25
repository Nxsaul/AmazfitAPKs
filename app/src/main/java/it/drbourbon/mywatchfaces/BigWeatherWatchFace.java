package it.drbourbon.mywatchfaces;

import com.huami.watch.watchface.AbstractSlptClock;

import es.malvarez.mywatchfaces.AbstractWatchFace;
import it.drbourbon.mywatchfaces.widget.BigImageWeather;
import it.drbourbon.mywatchfaces.widget.BigWeather;
import it.drbourbon.mywatchfaces.widget.ThreeLines;
import it.drbourbon.mywatchfaces.widget.ThreeLinesStepsWidget;

/**
 * Created by fabio on 21/05/17.
 */

public class BigWeatherWatchFace extends AbstractWatchFace {

    public BigWeatherWatchFace() {
        super(
                new ThreeLines(64),
                new BigImageWeather(14)
        );
    }

    @Override
    protected Class<? extends AbstractSlptClock> slptClockClass() {
        return BigWeatherWatchFaceSplt.class;
    }
}
