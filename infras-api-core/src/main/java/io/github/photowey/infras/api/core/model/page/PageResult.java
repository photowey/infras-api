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
package io.github.photowey.infras.api.core.model.page;

import io.github.photowey.infras.api.common.constant.InfrasConstants;
import io.github.photowey.infras.api.core.meta.Meta;
import io.github.photowey.infras.api.core.model.ResultSupportAdapter;
import io.github.photowey.infras.api.core.model.page.struct.ListStruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * {@code PageResult}
 *
 * @author photowey
 * @date 2024/01/28
 * @since 1.0.0
 */
public class PageResult<T> extends ResultSupportAdapter<T, PageResult<T>> {

    private static final long serialVersionUID = 8516482956446374018L;

    public ListStruct<T> data;

    // ----------------------------------------------------------------

    public PageResult() {
        super();
    }

    protected PageResult(String code, String message) {
        super(code, message);
    }

    protected PageResult(ListStruct<T> data) {
        super(determineApiOkCode(), determineApiOkMessage());
        this.data = data;
    }

    // ----------------------------------------------------------------

    public ListStruct<T> getData() {
        return data;
    }

    public ListStruct<T> struct() {
        return this.getData();
    }

    public void setData(ListStruct<T> data) {
        this.data = data;
    }

    // ----------------------------------------------------------------

    public static <T> PageResult<T> create() {
        return new PageResult<>();
    }

    public static <T> PageResult<T> create(ListStruct<T> struct) {
        return new PageResult<>(struct);
    }

    public static <T> PageResult<T> create(String code, String message) {
        return new PageResult<>(code, message);
    }

    public static <T> PageResult<T> staticOf(List<T> data) {
        return staticOf(data, Meta.populateDefaultMeta());
    }

    public static <T> PageResult<T> staticOf(List<T> data, Meta meta) {
        return staticOf(data, meta, InfrasConstants.Collection.emptyMap());
    }

    public static <T, D> PageResult<T> staticOf(List<T> data, Meta meta, Map<String, Object> additional) {
        return staticOf(data, meta, additional, Function.identity());
    }

    public static <T> PageResult<T> staticOf(List<T> data, Map<String, Object> additional) {
        return staticOf(data, Meta.populateDefaultMeta(), InfrasConstants.Collection.emptyMap());
    }

    public static <T> PageResult<T> emptyStatic() {
        PageResult<T> pageResult = create();
        return pageResult.of(new ArrayList<>(), Meta.populateDefaultMeta());
    }

    public static <T, D> PageResult<T> staticOf(PageResult<D> page, Function<List<D>, List<T>> transfer) {
        PageResult<T> pageResult = create();
        return pageResult.of(determineApiOkCode(), determineApiOkMessage(), transfer.apply(page.getData().getList()), Meta.populateMeta(page));
    }

    public static <T, D> PageResult<T> staticOf(PageResult<D> page, Map<String, Object> additional, Function<List<D>, List<T>> transfer) {
        PageResult<T> pageResult = create();
        return pageResult.of(determineApiOkCode(), determineApiOkMessage(), transfer.apply(page.getData().getList()), Meta.populateMeta(page), additional);
    }


    public static <T, D> PageResult<T> staticOf(List<D> data, Meta meta, Map<String, Object> additional, Function<List<D>, List<T>> transfer) {
        PageResult<T> pageResult = create();
        return pageResult.of(determineApiOkCode(), determineApiOkMessage(), transfer.apply(data), meta, additional);
    }

    // ----------------------------------------------------------------

    public static <T> PageResult<T> empty() {
        return create(ListStruct.empty());
    }

    // ----------------------------------------------------------

    public PageResult<T> of(List<T> data) {
        return this.of(determineApiOkCode(), determineApiOkMessage(), data);
    }

    public PageResult<T> of(List<T> data, Meta meta) {
        return this.of(data, meta, InfrasConstants.Collection.emptyMap());
    }

    public PageResult<T> of(List<T> data, Map<String, Object> additional) {
        return this.of(data, Meta.populateDefaultMeta(), additional);
    }

    public PageResult<T> of(List<T> data, Meta meta, Map<String, Object> additional) {
        return this.of(determineApiOkCode(), determineApiOkMessage(), data, meta, additional);
    }

    public PageResult<T> of(String code, String message, List<T> data) {
        return this.of(code, message, data, Meta.populateDefaultMeta());
    }

    public PageResult<T> of(String code, String message, List<T> data, Map<String, Object> additional) {
        return this.of(code, message, data, Meta.populateDefaultMeta(), additional);
    }

    @Override
    public PageResult<T> of(String code, String message, List<T> data, Meta meta) {
        return this.of(code, message, data, meta, InfrasConstants.Collection.emptyMap());
    }

    @Override
    public PageResult<T> of(String code, String message, List<T> data, Meta meta, Map<String, Object> additional) {
        this.code = code;
        this.message = message;
        this.data = new ListStruct<>(data, meta, additional);

        return this;
    }

    // ----------------------------------------------------------------

    public static <T> PageResultBuilder<T> builder() {
        return new PageResultBuilder<>();
    }

    // ----------------------------------------------------------------

    public static class PageResultBuilder<T> {

        protected String code;
        protected String message;
        protected List<T> data;
        private Meta meta;

        private Map<String, Object> additional = new HashMap<>();

        public PageResultBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        public PageResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public PageResultBuilder<T> data(List<T> data) {
            this.data = data;
            return this;
        }

        public PageResultBuilder<T> meta(Meta meta) {
            this.meta = meta;
            return this;
        }

        public PageResultBuilder<T> additional(Map<String, Object> additional) {
            this.additional = additional;
            return this;
        }

        public PageResult<T> build() {
            PageResult<T> pageResult = create();
            pageResult.of(code, message, data, meta, additional);

            return pageResult;
        }
    }
}