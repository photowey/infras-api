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

import io.github.photowey.infras.api.common.constant.InfrasConstants;
import io.github.photowey.infras.api.core.meta.Meta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * {@code ResultSupport}
 *
 * @author photowey
 * @date 2024/01/28
 * @since 1.0.0
 */
public abstract class ResultSupport<T, R extends ResultSupport<T, R>> implements Serializable {

    private static final long serialVersionUID = -8479667319465108597L;

    public static final String API_OK = "000000";

    protected String code;

    protected String message;

    protected ResultSupport() {
        this(determineApiOkCode(), determineApiOkMessage());
    }

    protected ResultSupport(String code, String message) {
        this.code = code;
        this.message = message;
    }

    protected abstract ResultSupport<T, R> of(String code, String message, T data);

    protected abstract ResultSupport<T, R> of(String code, String message, T data, Map<String, Object> additional);

    protected abstract ResultSupport<T, R> of(String code, String message, List<T> data, Meta meta);

    protected abstract ResultSupport<T, R> of(String code, String message, List<T> data, Meta meta, Map<String, Object> additional);

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public R code(String code) {
        this.code = code;
        return (R) this;
    }

    public R message(String message) {
        this.message = message;
        return (R) this;
    }

    public boolean determineIsSuccessful() {
        return this.determineApiOkCode().equalsIgnoreCase(this.code);
    }

    public boolean determineIsFailed() {
        return !this.determineIsSuccessful();
    }

    public static String determineApiOkCode() {
        return System.getProperty(InfrasConstants.Properties.INFRAS_API_OK_CONFIG_KEY,/*200000000*/InfrasConstants.DEFAULT_API_OK);
    }

    public static String determineApiOkMessage() {
        return System.getProperty(InfrasConstants.Properties.INFRAS_API_MESSAGE_CONFIG_KEY,/*ok*/InfrasConstants.DEFAULT_API_MESSAGE);
    }
}