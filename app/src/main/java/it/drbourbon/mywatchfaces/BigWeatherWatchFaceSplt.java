package it.drbourbon.mywatchfaces;

import es.malvarez.mywatchfaces.AbstractWatchFaceSlpt;
import it.drbourbon.mywatchfaces.widget.ThreeLines;
import it.drbourbon.mywatchfaces.widget.ThreeLinesStepsWidget;

/**
 * Created by fabio on 21/05/17.
 */

public class BigWeatherWatchFaceSplt extends AbstractWatchFaceSlpt {
    public BigWeatherWatchFaceSplt() {
        super(
                new ThreeLines(0),
                new ThreeLinesStepsWidget()
        );
    }

//    @Override
    protected void initWatchFaceConfig() {

    }
}
