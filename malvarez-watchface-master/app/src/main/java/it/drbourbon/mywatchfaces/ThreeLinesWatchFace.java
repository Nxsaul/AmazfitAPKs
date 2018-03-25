package it.drbourbon.mywatchfaces;

import com.huami.watch.watchface.AbstractSlptClock;

import es.malvarez.mywatchfaces.AbstractWatchFace;
import it.drbourbon.mywatchfaces.widget.BigWeather;
import it.drbourbon.mywatchfaces.widget.HugeDigitClock;
import it.drbourbon.mywatchfaces.widget.ThreeLines;
import it.drbourbon.mywatchfaces.widget.ThreeLinesStepsWidget;

/**
 * Created by fabio on 21/05/17.
 */

public class ThreeLinesWatchFace extends AbstractWatchFace {

    public ThreeLinesWatchFace() {
        super(
                new ThreeLines(0),
                new BigWeather(),
                new ThreeLinesStepsWidget()
        );
    }

    @Override
    protected Class<? extends AbstractSlptClock> slptClockClass() {
        return ThreeLinesWatchFaceSplt.class;
    }
}
