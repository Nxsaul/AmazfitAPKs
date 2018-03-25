package it.drbourbon.mywatchfaces;

import com.huami.watch.watchface.AbstractSlptClock;

import es.malvarez.mywatchfaces.AbstractWatchFace;
import it.drbourbon.mywatchfaces.widget.FuzzyTextClock;

/**
 * Amazfit watch faces
 */

public class FuzzyTextWatchFace extends AbstractWatchFace {

    public FuzzyTextWatchFace() {
      super(new FuzzyTextClock());
    }

    @Override
    protected Class<? extends AbstractSlptClock> slptClockClass() {
        return FuzzyTextWatchFaceSplt.class;
    }
}
