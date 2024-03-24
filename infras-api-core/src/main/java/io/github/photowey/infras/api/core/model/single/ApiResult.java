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
package io.github.photowey.infras.api.core.model.single;

import io.github.photowey.infras.api.common.constant.InfrasConstants;
import io.github.photowey.infras.api.core.model.ResultSupportAdapter;
import io.github.photowey.infras.api.core.model.factory.EmptyEntityFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code ApiResult}
 *
 * @author photowey
 * @date 2024/01/28
 * @since 1.0.0
 */
public class ApiResult<T> extends ResultSupportAdapter<T, ApiResult<T>> {

    public ApiResult() {
        this(determineApiOkCode(), determineApiOkMessage());
    }

    // ----------------------------------------------------------------

    public static <T> ApiResult<T> create() {
        return new ApiResult<>();
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult<>(determineApiOkCode(), determineApiOkMessage(), (T) EmptyEntityFactory.empty());
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = create();
        return apiResult.of(determineApiOkCode(), determineApiOkMessage(), data);
    }

    public static <T> ApiResult<T> success(T data, Map<String, Object> additional) {
        ApiResult<T> apiResult = create();
        return apiResult.of(determineApiOkCode(), determineApiOkMessage(), data, additional);
    }

    public static <T> ApiResult<T> failure() {
        return new ApiResult<>(InfrasConstants.DEFAULT_API_INNER_ERROR, InfrasConstants.DEFAULT_API_INNER_ERROR_MESSAGE);
    }

    public static <T> ApiResult<T> failure(Map<String, Object> additional) {
        ApiResult<T> apiResult = create();
        return apiResult.of(determineApiInnerErrorCode(), determineApiInnerErrorMessage(), (T) EmptyEntityFactory.empty(), additional);
    }

    public static <T> ApiResultBuilder<T> builder() {
        return new ApiResultBuilder<>();
    }

    // ----------------------------------------------------------------

    private T data;

    private Map<String, Object> additional = new HashMap<>();

    public ApiResult(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    protected ApiResult(String code, String message) {
        super(code, message);
    }

    @Override
    public ApiResult<T> of(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;

        return this;
    }

    public ApiResult<T> of(String code, String message, T data, Map<String, Object> additional) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.additional = additional;

        return this;
    }

    public static class ApiResultBuilder<T> {

        protected String code;

        protected String message;

        protected T data;

        protected Map<String, Object> additional;

        public ApiResultBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        public ApiResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ApiResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResultBuilder<T> data(Map<String, Object> additional) {
            this.additional = additional;
            return this;
        }

        public ApiResult<T> build() {
            ApiResult<T> apiResult = ApiResult.create();
            apiResult.of(code, message, data, additional);
            return apiResult;
        }
    }
}