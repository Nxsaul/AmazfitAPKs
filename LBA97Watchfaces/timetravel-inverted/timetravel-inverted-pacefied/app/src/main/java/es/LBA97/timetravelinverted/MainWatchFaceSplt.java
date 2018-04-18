package es.LBA97.timetravelinverted;

import es.LBA97.timetravelinverted.widget.CirclesWidget;
import es.LBA97.timetravelinverted.widget.HeartRateWidget;
import es.LBA97.timetravelinverted.widget.MainClock;

import com.ingenic.iwds.slpt.view.core.SlptAbsoluteLayout;
import com.ingenic.iwds.slpt.view.core.SlptLayout;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

import es.LBA97.timetravelinverted.widget.Widget;

/**
 * Splt version of the watch.
 */

public class MainWatchFaceSplt extends AbstractWatchFaceSlpt {

    public MainWatchFaceSplt() {
        super(
                new MainClock(),
                new CirclesWidget(),
                new HeartRateWidget()
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