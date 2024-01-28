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
package io.github.photowey.infras.api.core.model.page.struct;

import io.github.photowey.infras.api.common.constant.InfrasConstants;
import io.github.photowey.infras.api.common.validator.ValueValidator;
import io.github.photowey.infras.api.core.meta.Meta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * {@code ListStruct}
 *
 * @author photowey
 * @date 2024/01/28
 * @since 1.0.0
 */
public class ListStruct<T> implements Serializable {

    private static final long serialVersionUID = -514945485472820274L;

    private Long pageNo;
    private Long pageSize;
    private Long count;

    private List<T> list = InfrasConstants.Collection.emptyList();

    private Map<String, Object> additional = InfrasConstants.Collection.emptyMap();

    public ListStruct() {
        this(InfrasConstants.Collection.emptyList());
    }

    public ListStruct(List<T> list) {
        this(list, Meta.populateDefaultMeta());
    }

    public ListStruct(List<T> list, Meta meta) {
        this(list, meta, InfrasConstants.Collection.emptyMap());
    }

    public ListStruct(List<T> list, Meta meta, Map<String, Object> additional) {
        this(meta.getPageNo(), meta.getPageSize(), meta.getCount(), list, additional);
    }

    public ListStruct(Long pageNo, Long pageSize, Long count, List<T> list) {
        this(pageNo, pageSize, count, list, InfrasConstants.Collection.emptyMap());
    }

    public ListStruct(Long pageNo, Long pageSize, Long count, List<T> list, Map<String, Object> additional) {
        this.pageNo = ValueValidator.isNotNullOrEmpty(pageNo) ? pageNo : InfrasConstants.Pagination.DEFAULT_PAGE_NO;
        this.pageSize = ValueValidator.isNotNullOrEmpty(pageSize) ? pageSize : InfrasConstants.Pagination.DEFAULT_PAGE_SIZE;
        this.count = ValueValidator.isNotNullOrEmpty(count) ? count : InfrasConstants.Pagination.EMPTY;
        this.additional = ValueValidator.isNotNullOrEmpty(additional) ? additional : InfrasConstants.Collection.emptyMap();

        this.list = list;
    }

    // ----------------------------------------------------------------

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Map<String, Object> getAdditional() {
        return additional;
    }

    public void setAdditional(Map<String, Object> additional) {
        this.additional = additional;
    }

    public Long pageNo() {
        return pageNo;
    }

    public Long pageSize() {
        return pageSize;
    }

    public Long count() {
        return count;
    }

    public List<T> list() {
        return list;
    }

    public Map<String, Object> additional() {
        return additional;
    }

    public ListStruct<T> pageNo(Long pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public ListStruct<T> pageSize(Long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ListStruct<T> count(Long count) {
        this.count = count;
        return this;
    }

    public ListStruct<T> list(List<T> list) {
        this.list = list;
        return this;
    }

    public ListStruct<T> additional(Map<String, Object> additional) {
        this.additional = additional;
        return this;
    }

    public ListStruct<T> meta(Meta meta) {
        this.pageNo(meta.pageNo());
        this.pageSize(meta.pageSize());
        this.count(meta.count());
        return this;
    }

    // ----------------------------------------------------------------

    public static <D> ListStruct<D> empty() {
        return create();
    }

    // ----------------------------------------------------------------

    public static <D> ListStruct<D> create() {
        return new ListStruct<>();
    }

    public static <D> ListStruct<D> create(Meta meta) {
        return create(InfrasConstants.Collection.emptyList(), meta);
    }

    public static <D> ListStruct<D> create(List<D> data) {
        return create(data, Meta.populateDefaultMeta());
    }

    public static <D> ListStruct<D> create(List<D> data, Meta meta) {
        return create(data, meta, InfrasConstants.Collection.emptyMap());
    }

    public static <D> ListStruct<D> create(List<D> data, Meta meta, Map<String, Object> additional) {
        return create(data).meta(meta).additional(additional);
    }

    // ----------------------------------------------------------------

    public static <D> ListStruct<D> of(List<D> data) {
        return create(data);
    }

    public static <D> ListStruct<D> of(List<D> data, Meta meta) {
        return create(data, meta);
    }

    public static <D> ListStruct<D> of(List<D> data, Meta meta, Map<String, Object> additional) {
        return create(data, meta, additional);
    }

    // ----------------------------------------------------------------

    public Meta toMeta() {
        return Meta.builder()
                .pageNo(this.pageNo())
                .pageSize(this.pageSize())
                .count(this.count())
                .build();
    }
}
