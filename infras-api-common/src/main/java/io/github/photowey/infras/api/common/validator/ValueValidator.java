/*
 * Copyright © 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.photowey.infras.api.common.validator;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * {@code ValueValidator}
 *
 * @author photowey
 * @date 2024/01/28
 * @since 1.0.0
 */
public final class ValueValidator {

    private ValueValidator() {
        throw new AssertionError("No " + this.getClass().getName() + " instances for you!");
    }

    public static boolean isBlank(CharSequence sequence) {
        if (sequence != null) {
            int length = sequence.length();
            for (int i = 0; i < length; ++i) {
                if (!Character.isWhitespace(sequence.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isNotBlank(CharSequence sequence) {
        return !isBlank(sequence);
    }

    public static boolean isNullOrEmpty(Object value) {
        if (null == value) {
            return true;
        }
        if (value instanceof CharSequence) {
            return isBlank((CharSequence) value);
        }
        if (isCollectionsType(value)) {
            return sizeIsEmpty(value);
        }

        return false;
    }

    public static boolean isNotNullOrEmpty(Object value) {
        return !isNullOrEmpty(value);
    }

    private static boolean isCollectionsType(Object value) {
        return value instanceof Collection || value instanceof Map
                || value instanceof Enumeration || value instanceof Iterator
                || value.getClass().isArray();
    }

    public static <T> T defaultIfNullOrEmpty(final T source, final T defaultValue) {
        return isNotNullOrEmpty(source) ? source : defaultValue;
    }

    public static boolean sizeIsEmpty(Object object) {
        if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).isEmpty();
        } else if (object instanceof Object[]) {
            return ((Object[]) ((Object[]) object)).length == 0;
        } else if (object instanceof Iterator) {
            return !((Iterator) object).hasNext();
        } else if (object instanceof Enumeration) {
            return !((Enumeration) object).hasMoreElements();
        } else if (object == null) {
            throw new IllegalArgumentException("Unsupported object type: null");
        } else {
            try {
                return Array.getLength(object) == 0;
            } catch (IllegalArgumentException var2) {
                throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
            }
        }
    }
}
