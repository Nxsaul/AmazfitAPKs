package es.LBA97.PSourceInverted;

import com.huami.watch.watchface.AbstractSlptClock;

import es.LBA97.PSourceInverted.widget.BatteryWidget;
import es.LBA97.PSourceInverted.widget.CirclesWidget;
import es.LBA97.PSourceInverted.widget.HeartRateWidget;
import es.LBA97.PSourceInverted.widget.MainClock;
import es.LBA97.PSourceInverted.widget.CaloriesWidget;
import es.LBA97.PSourceInverted.widget.WeatherWidget;
import es.LBA97.PSourceInverted.widget.FloorWidget;


/**
 * Amazfit watch faces
 */

public class MainWatchFace extends AbstractWatchFace {

    public MainWatchFace() {
        super(
                new MainClock(),
                new CirclesWidget(),
                new HeartRateWidget(),
                new CaloriesWidget(),
                new FloorWidget(),
                new WeatherWidget(),
                new BatteryWidget()

        );
    }

    @Override
    protected Class<? extends AbstractSlptClock> slptClockClass() {
        return MainWatchFaceSplt.class;
    }
}