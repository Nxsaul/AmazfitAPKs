package it.drbourbon.mywatchfaces;

import com.huami.watch.watchface.AbstractSlptClock;

import es.malvarez.mywatchfaces.AbstractWatchFace;
import it.drbourbon.mywatchfaces.widget.FuzzyTextClock;
import it.drbourbon.mywatchfaces.widget.HugeDigitClock;

/**
 * Created by fabio on 21/05/17.
 */

public class HugeDigitsWatchFace extends AbstractWatchFace {

    public HugeDigitsWatchFace() {
        super(new HugeDigitClock());
    }

    @Override
    protected Class<? extends AbstractSlptClock> slptClockClass() {
        return HugeDigitsWatchFaceSplt.class;
    }
}
