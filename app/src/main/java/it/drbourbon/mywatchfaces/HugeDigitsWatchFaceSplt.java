package it.drbourbon.mywatchfaces;

import es.malvarez.mywatchfaces.AbstractWatchFaceSlpt;
import it.drbourbon.mywatchfaces.widget.FuzzyTextClock;
import it.drbourbon.mywatchfaces.widget.HugeDigitClock;

/**
 * Created by fabio on 21/05/17.
 */

public class HugeDigitsWatchFaceSplt extends AbstractWatchFaceSlpt {
    public HugeDigitsWatchFaceSplt() {
        super(new HugeDigitClock());
    }

//    @Override
    protected void initWatchFaceConfig() {

    }
}
