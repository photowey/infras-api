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
package io.github.photowey.infras.api.core.model;

import io.github.photowey.infras.api.core.meta.Meta;

import java.util.List;
import java.util.Map;

/**
 * {@code ResultSupportAdapter}
 *
 * @author photowey
 * @date 2024/01/28
 * @since 1.0.0
 */
public abstract class ResultSupportAdapter<T, R extends ResultSupportAdapter<T, R>> extends ResultSupport<T, R> {

    protected ResultSupportAdapter() {
        super();
    }

    protected ResultSupportAdapter(String code, String message) {
        super(code, message);
    }

    @Override
    protected ResultSupport<T, R> of(String code, String message, T data) {
        return this;
    }

    @Override
    protected ResultSupport<T, R> of(String code, String message, T data, Map<String, Object> additional) {
        return this;
    }

    @Override
    protected ResultSupport<T, R> of(String code, String message, List<T> data, Meta meta) {
        return this;
    }

    @Override
    protected ResultSupport<T, R> of(String code, String message, List<T> data, Meta meta, Map<String, Object> additional) {
        return this;
    }
}