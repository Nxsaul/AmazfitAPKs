package it.drbourbon.mywatchfaces;

import es.malvarez.mywatchfaces.AbstractWatchFaceSlpt;
import it.drbourbon.mywatchfaces.widget.TextClock;

/**
 * Splt version of the watch.
 */

public class TextWatchFaceSplt extends AbstractWatchFaceSlpt {

    public TextWatchFaceSplt() {
        super(new TextClock());
    }

//    @Override
    protected void initWatchFaceConfig() {

    }
}
