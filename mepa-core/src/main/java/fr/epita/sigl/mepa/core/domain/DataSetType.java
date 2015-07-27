package fr.epita.sigl.mepa.core.domain;

import org.apache.commons.lang3.math.NumberUtils;

public enum DataSetType {
    TEXT, INT;

    public static boolean checkType(String data, DataSetType type) {
        switch (type) {
            case INT:
                return NumberUtils.isNumber(data);
        }
        return true;
    }
}
