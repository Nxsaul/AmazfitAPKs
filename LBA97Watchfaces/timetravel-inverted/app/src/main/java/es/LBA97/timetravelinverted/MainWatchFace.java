package es.LBA97.timetravelinverted;

import com.huami.watch.watchface.AbstractSlptClock;

import es.LBA97.timetravelinverted.widget.CirclesWidget;
import es.LBA97.timetravelinverted.widget.HeartRateWidget;
import es.LBA97.timetravelinverted.widget.MainClock;

/**
 * Amazfit watch faces
 */

public class MainWatchFace extends AbstractWatchFace {

    public MainWatchFace() {
        super(
                new MainClock(),
                new CirclesWidget(),
                new HeartRateWidget()
        );
    }

    @Override
    protected Class<? extends AbstractSlptClock> slptClockClass() {
        return MainWatchFaceSplt.class;
    }
}