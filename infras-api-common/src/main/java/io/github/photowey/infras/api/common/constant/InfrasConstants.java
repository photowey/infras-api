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
package io.github.photowey.infras.api.common.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code InfrasConstants}
 *
 * @author photowey
 * @date 2024/01/28
 * @since 1.0.0
 */
public interface InfrasConstants {

    // ---------------------------------------------------------------- int.http.status

    int HTTP_STATUS_OK = 200;
    int HTTP_STATUS_CREATED = 201;

    int HTTP_STATUS_BAD_REQUEST = 400;
    int HTTP_STATUS_UNAUTHORIZED = 401;
    int HTTP_STATUS_FORBIDDEN = 403;
    int HTTP_STATUS_NOT_FOUND = 404;
    int HTTP_STATUS_METHOD_NOT_ALLOWED = 405;

    int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;
    int HTTP_STATUS_BAD_GATEWAY = 502;

    // ---------------------------------------------------------------- string.biz.status

    String BIZ_STATUS_OK = "000000";

    // ---------------------------------------------------------------- string.api.ok

    /**
     * 200000000
     * |- 200 - http.status
     * |- 000000 - biz.status
     */
    String DEFAULT_API_OK = HTTP_STATUS_OK + BIZ_STATUS_OK;
    String DEFAULT_API_MESSAGE = "ok";

    interface Pagination {

        long EMPTY = 0L;

        long DEFAULT_PAGE_NO = 1L;
        long DEFAULT_PAGE_SIZE = 10L;
        long ONE_PAGE_SIZE = 1L;

        long THRESHOLD_PAGE_NO = 1L;
        long THRESHOLD_PAGE_SIZE = 100L;
    }

    interface Properties {
        String INFRAS_API_OK_CONFIG_KEY = "io.github.photowey.infras.api.ok.code";
        String INFRAS_API_MESSAGE_CONFIG_KEY = "io.github.photowey.infras.api.ok.message";
    }

    interface Collection {
        static <T> List<T> emptyList() {
            return new ArrayList<>(0);
        }

        static Map<String, Object> emptyMap() {
            return emptyMap(2);
        }

        static Map<String, Object> emptyMap(int initialCapacity) {
            return new HashMap<>(initialCapacity);
        }
    }
}
