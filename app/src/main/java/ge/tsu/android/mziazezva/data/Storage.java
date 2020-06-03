package ge.tsu.android.mziazezva.data;

import android.content.Context;

public interface Storage {
    void add(Context context, String key, Object value);

    Object getObject(Context context, String key, Class klass);

    String getStringValue(Context context, String key);

//    void deleteSms(Context context, String keyOfSms);
}
