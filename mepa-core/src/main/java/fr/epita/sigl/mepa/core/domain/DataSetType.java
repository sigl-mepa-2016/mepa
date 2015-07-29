package fr.epita.sigl.mepa.core.domain;

import org.apache.commons.lang3.math.NumberUtils;

public enum DataSetType {
    TEXT, NUMBER;

    public static boolean checkType(String data, DataSetType type) {
        switch (type) {
            case NUMBER:
                return NumberUtils.isNumber(data);
        }
        return true;
    }
}
