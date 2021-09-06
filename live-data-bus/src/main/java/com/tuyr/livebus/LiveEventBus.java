package com.tuyr.livebus;

import androidx.annotation.NonNull;

import java.util.concurrent.ConcurrentHashMap;

public class LiveEventBus {

    private static final class SingleHolder {
        private static final LiveEventBus INSTANCE = new LiveEventBus();
    }

    private static LiveEventBus get() {
        return SingleHolder.INSTANCE;
    }

    private final ConcurrentHashMap<Object, LiveEvent<Object>> mEventMap;

    private LiveEventBus() {
        mEventMap = new ConcurrentHashMap<>();
    }

    public static <T> LiveEvent<T> get(@NonNull final String key, @NonNull final Class<T> clazz) {
        return get().realWith(key, clazz);
    }

    public static <T> LiveEvent<T> get(@NonNull final Class<T> clazz) {
        return get().realWith(null, clazz);
    }

    @SuppressWarnings("unchecked")
    private <T> LiveEvent<T> realWith(final String key, final Class<T> clazz) {
        final Object objectKey;
        if (key != null) {
            objectKey = key;
        } else if (clazz != null) {
            objectKey = clazz;
        } else {
            throw new IllegalArgumentException("key and clazz, one of which must not be null");
        }
        LiveEvent<Object> result = mEventMap.get(objectKey);
        if (result != null) {
            return (LiveEvent<T>) result;
        }
        synchronized (mEventMap) {
            result = mEventMap.get(objectKey);
            if (result == null) {
                result = new LiveEvent<>();
                mEventMap.put(objectKey, result);
            }
        }
        return (LiveEvent<T>) result;
    }
}
