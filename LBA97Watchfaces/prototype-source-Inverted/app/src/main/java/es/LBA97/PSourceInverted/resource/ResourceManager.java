package es.LBA97.PSourceInverted.resource;

import android.content.res.Resources;
import android.graphics.Typeface;

import java.util.EnumMap;
import java.util.Map;

/**
 * Resource manager for caching purposes
 */
public class ResourceManager {

    public enum Font {
        PROTOTYPE_SOURCE("fonts/PrototypeSource.otf"), BEBAS_NEUE("fonts/BebasNeue.otf"),IRRESISTIBLE_HOLLYWOOD("fonts/irresistible_hollywood.ttf");

        private final String path;

        Font(String path) {
            this.path = path;
        }
    }


    private static Map<Font, Typeface> TYPE_FACES = new EnumMap<>(Font.class);

    public static Typeface getTypeFace(final Resources resources, final Font font) {
        Typeface typeface = TYPE_FACES.get(font);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(resources.getAssets(), font.path);
            TYPE_FACES.put(font, typeface);
        }
        return typeface;
    }

}
