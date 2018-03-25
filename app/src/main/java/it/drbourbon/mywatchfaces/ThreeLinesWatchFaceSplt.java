package it.drbourbon.mywatchfaces;

import es.malvarez.mywatchfaces.AbstractWatchFaceSlpt;
import it.drbourbon.mywatchfaces.widget.FuzzyTextClock;
import it.drbourbon.mywatchfaces.widget.HugeDigitClock;
import it.drbourbon.mywatchfaces.widget.ThreeLines;
import it.drbourbon.mywatchfaces.widget.ThreeLinesStepsWidget;

/**
 * Created by fabio on 21/05/17.
 */

public class ThreeLinesWatchFaceSplt extends AbstractWatchFaceSlpt {
    public ThreeLinesWatchFaceSplt() {
        super(
                new ThreeLines(0),
                new ThreeLinesStepsWidget()
        );
    }

//    @Override
    protected void initWatchFaceConfig() {

    }
}
