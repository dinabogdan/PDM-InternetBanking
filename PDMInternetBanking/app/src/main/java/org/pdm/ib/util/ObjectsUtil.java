package org.pdm.ib.util;

public class ObjectsUtil {

    public static boolean isAnyNull(Object...objects) {
        if (objects == null) return true;

        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }

        return false;
    }
}
