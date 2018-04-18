package es.LBA97.PSourceInverted;

import es.LBA97.PSourceInverted.widget.BatteryWidget;
import es.LBA97.PSourceInverted.widget.CaloriesWidget;
import es.LBA97.PSourceInverted.widget.CirclesWidget;
import es.LBA97.PSourceInverted.widget.FloorWidget;
import es.LBA97.PSourceInverted.widget.HeartRateWidget;
import es.LBA97.PSourceInverted.widget.MainClock;

import com.ingenic.iwds.slpt.view.core.SlptAbsoluteLayout;
import com.ingenic.iwds.slpt.view.core.SlptLayout;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

import es.LBA97.PSourceInverted.widget.WeatherWidget;
import es.LBA97.PSourceInverted.widget.Widget;

/**
 * Splt version of the watch.
 */

public class MainWatchFaceSplt extends AbstractWatchFaceSlpt {

    public MainWatchFaceSplt() {
        super(
                new MainClock(),
                new CirclesWidget(),
                new HeartRateWidget(),
                new CaloriesWidget(),
                new WeatherWidget(),
                new BatteryWidget(),
                new FloorWidget()
        );
    }

    @Override
    protected SlptLayout createClockLayout26WC() {
        SlptAbsoluteLayout result = new SlptAbsoluteLayout();
        for (SlptViewComponent component : clock.buildSlptViewComponent(this)) {
            result.add(component);
        }
        for (Widget widget : widgets) {
            for (SlptViewComponent component : widget.buildSlptViewComponent(this)) {
                result.add(component);
            }
        }
        return result;
    }

    @Override
    protected SlptLayout createClockLayout8C() {
        SlptAbsoluteLayout result = new SlptAbsoluteLayout();
        for (SlptViewComponent component : clock.buildSlptViewComponent(this)) {
            result.add(component);
        }
        for (Widget widget : widgets) {
            for (SlptViewComponent component : widget.buildSlptViewComponent(this)) {
                result.add(component);
            }
        }
        return result;
    }
}