package com.ravenliquid.watchfaces;

import com.ingenic.iwds.slpt.view.core.SlptLayout;
import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptNumView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by rober on 10-3-2018.
 */

public class Utility {
    public static void setStringPictureArrayForAll(SlptLayout layout, String[] var1) {
        Field f = null;
        try {
            f = SlptLayout.class.getDeclaredField("list");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        ArrayList<SlptViewComponent> list = null; //IllegalAccessException
        try {
            list = (ArrayList<SlptViewComponent>) f.get(layout);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for(int var2 = 0; var2 < list.size(); ++var2) {
            SlptViewComponent var3 = (SlptViewComponent)list.get(var2);
            if(var3 instanceof SlptNumView) {
                ((SlptNumView)var3).setStringPictureArray(var1);
            }
        }

    }
}
