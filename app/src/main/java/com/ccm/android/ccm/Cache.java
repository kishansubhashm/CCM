package com.ccm.android.ccm;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by KishanSubhashMiryala on 2/25/2016.
 */
public class Cache implements Serializable {
    private FileInputStream fis;
    private Cache(){

    }
    public static void writeObject(Context context, String key, Object object) throws IOException {
        try {
            FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (Exception e) {
//            Log.d("Cache", "In Writing");
            e.printStackTrace();
        }
    }

    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        Object object=null;
        try{
            FileInputStream fis = context.openFileInput(key);
            ObjectInputStream ois = new ObjectInputStream(fis);
            object = ois.readObject();

        } catch (Exception e) {
            Log.d("Cache", "In reading");
            e.printStackTrace();
        }
        return object;
    }
}
