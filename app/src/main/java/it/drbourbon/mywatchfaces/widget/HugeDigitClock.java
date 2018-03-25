package it.drbourbon.mywatchfaces.widget;

import android.app.Service;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.digital.SlptHourHView;
import com.ingenic.iwds.slpt.view.digital.SlptHourLView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteHView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteLView;
import com.ingenic.iwds.slpt.view.digital.SlptTimeView;
import com.ingenic.iwds.slpt.view.utils.SimpleFile;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import es.malvarez.mywatchfaces.resource.ResourceManager;
import es.malvarez.mywatchfaces.widget.DigitalClockWidget;

import com.ravenliquid.watchfaces.R;

/**
 * Created by fabio on 21/05/17.
 */

public class HugeDigitClock extends DigitalClockWidget {
    private int background;
    private float offset;
    private int img_offset;
    private Drawable[] digits;
    private final int[] digitalNums =
            {
                    R.drawable.d0b,
                    R.drawable.d1b,
                    R.drawable.d2b,
                    R.drawable.d3b,
                    R.drawable.d4b,
                    R.drawable.d5b,
                    R.drawable.d6b,
                    R.drawable.d7b,
                    R.drawable.d8b,
                    R.drawable.d9b
            };

    @Override
    public void init(Service service) {
        this.background = service.getResources().getColor(R.color.hugedigits_background);
        this.offset = service.getResources().getDimension(R.dimen.hugedigit_offset);
        List<Drawable> dd = new ArrayList<>(10);
        for (int resId : digitalNums){
            dd.add(service.getDrawable(resId));
        }
        digits = dd.toArray(new Drawable[0]);
        this.img_offset = 52;//160 - digits[0].getBounds().height();
    }

    @Override
    public void onDrawDigital(Canvas canvas, float width, float height, float centerX, float centerY, int seconds, int minutes, int hours, int year, int month, int day, int week, int ampm) {
        canvas.drawColor(background);

        //Calendar rightNow = Calendar.getInstance();
        int hh = hours/10;
        int hl = hours%10;
        int mh = minutes/10;
        int ml = minutes%10;

        float halfWidth = width / 2;
        float halfHeight = height / 2;
        canvas.drawBitmap(Util.drawableToBitmap(digits[hh]), img_offset+offset, img_offset+offset, null);
        canvas.drawBitmap(Util.drawableToBitmap(digits[hl]), halfWidth-offset, offset+img_offset, null);
        canvas.drawBitmap(Util.drawableToBitmap(digits[mh]), offset+img_offset, halfHeight-offset, null);
        canvas.drawBitmap(Util.drawableToBitmap(digits[ml]), halfWidth-offset, halfHeight-offset, null);
    }

    private byte[] drawableToBytes(Drawable d) {
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata;
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        init(service);

        List<SlptViewComponent> retVal = new LinkedList<>();

        SlptPictureView bg = new SlptPictureView();
        bg.setImagePicture(SimpleFile.readFileFromAssets(service.getApplicationContext(), "black.png"));
        retVal.add(bg);

        List<byte[]> bitmapBytes = new LinkedList<>();
        for (Drawable d : digits){
            bitmapBytes.add(drawableToBytes(d));
        }
        final byte [][] imagesBytes = bitmapBytes.toArray(new byte[0][]);

        SlptHourHView hh = new SlptHourHView();
        SlptHourLView hl = new SlptHourLView();
        SlptMinuteHView mh = new SlptMinuteHView();
        SlptMinuteLView ml = new SlptMinuteLView();

        for (SlptTimeView c : Arrays.asList(hh, hl, mh, ml)){
            c.setImagePictureArray(imagesBytes);
            retVal.add(c);
        }

        int halfWidth = 320 / 2;
        int halfHeight = 320 / 2;
        int ioffset = (int)offset;
        hh.setStart(ioffset+img_offset,ioffset+img_offset);
        hl.setStart(halfWidth-ioffset,ioffset+img_offset);
        mh.setStart(ioffset+img_offset,halfHeight-ioffset);
        ml.setStart(halfWidth-ioffset,halfHeight-ioffset);

        return retVal;
    }
}
