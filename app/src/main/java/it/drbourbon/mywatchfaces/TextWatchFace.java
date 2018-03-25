package it.drbourbon.mywatchfaces;

import com.huami.watch.watchface.AbstractSlptClock;

import es.malvarez.mywatchfaces.AbstractWatchFace;
import it.drbourbon.mywatchfaces.widget.FuzzyTextClock;
import it.drbourbon.mywatchfaces.widget.TextClock;

/**
 * Amazfit watch faces
 */

public class TextWatchFace extends AbstractWatchFace {

    public TextWatchFace() {
      super(new TextClock());
    }

    @Override
    protected Class<? extends AbstractSlptClock> slptClockClass() {
        return TextWatchFaceSplt.class;
    }
}
