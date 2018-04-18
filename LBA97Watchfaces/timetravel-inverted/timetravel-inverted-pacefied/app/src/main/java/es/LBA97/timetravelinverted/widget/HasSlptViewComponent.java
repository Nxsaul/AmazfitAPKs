package es.LBA97.timetravelinverted.widget;

import android.app.Service;

import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

import java.util.List;

/**
 * Widget with the slpt component
 */
public interface HasSlptViewComponent {

    List<SlptViewComponent> buildSlptViewComponent(Service service);
}
