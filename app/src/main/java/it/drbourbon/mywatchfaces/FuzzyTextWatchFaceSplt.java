package it.drbourbon.mywatchfaces;

import es.malvarez.mywatchfaces.AbstractWatchFaceSlpt;
import es.malvarez.mywatchfaces.widget.CirclesWidget;
import es.malvarez.mywatchfaces.widget.HeartRateWidget;
import es.malvarez.mywatchfaces.widget.MalvarezClock;
import it.drbourbon.mywatchfaces.widget.FuzzyTextClock;
import it.drbourbon.mywatchfaces.widget.HugeDigitClock;
import it.drbourbon.mywatchfaces.widget.TextClock;

/**
 * Splt version of the watch.
 */

public class FuzzyTextWatchFaceSplt extends AbstractWatchFaceSlpt {

    public FuzzyTextWatchFaceSplt() {
        super(new TextClock());
    }

//    @Override
    protected void initWatchFaceConfig() {

    }
}
