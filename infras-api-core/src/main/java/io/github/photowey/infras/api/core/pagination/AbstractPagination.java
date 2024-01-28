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
package io.github.photowey.infras.api.core.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.photowey.infras.api.common.constant.InfrasConstants;

import java.io.Serializable;

/**
 * {@code AbstractPaginationModel}
 *
 * @author photowey
 * @date 2024/01/28
 * @since 1.0.0
 */
public abstract class AbstractPagination implements Pagination, Serializable {

    protected Long pageNo = InfrasConstants.Pagination.DEFAULT_PAGE_NO;

    protected Long pageSize = InfrasConstants.Pagination.DEFAULT_PAGE_SIZE;

    protected Long count = InfrasConstants.Pagination.EMPTY;

    // ----------------------------------------------------------------

    @JsonIgnore
    public Long getLimit() {
        return this.getPageSize();
    }

    @JsonIgnore
    public Long getOffset() {
        return (this.getPageNo() - /*1L*/InfrasConstants.Pagination.THRESHOLD_PAGE_NO) * this.getPageSize();
    }

    // ----------------------------------------------------------------

    @JsonIgnore
    public Long getCount() {
        return null != this.count ? this.count : /*0L*/InfrasConstants.Pagination.EMPTY;
    }

    @Override
    public Long getPageNo() {
        return Math.max(this.pageNo, /*1L*/InfrasConstants.Pagination.THRESHOLD_PAGE_NO);
    }

    @Override
    public Long getPageSize() {
        return Math.min(this.pageSize, /*100L*/InfrasConstants.Pagination.THRESHOLD_PAGE_SIZE);
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    // ----------------------------------------------------------------

    public Long count() {
        return null != this.count ? this.count : /*0L*/InfrasConstants.Pagination.EMPTY;
    }

    public Long pageNo() {
        return Math.max(this.pageNo, /*1L*/InfrasConstants.Pagination.THRESHOLD_PAGE_NO);
    }

    public Long pageSize() {
        return Math.min(this.pageSize, /*100L*/InfrasConstants.Pagination.THRESHOLD_PAGE_SIZE);
    }

    public <P extends AbstractPagination> P pageNo(Long pageNo) {
        this.pageNo = pageNo;
        return (P) this;
    }

    public <P extends AbstractPagination> P pageSize(Long pageSize) {
        this.pageSize = pageSize;
        return (P) this;
    }

    public <P extends AbstractPagination> P count(Long count) {
        this.count = count;
        return (P) this;
    }

    // ----------------------------------------------------------------

    public void selectOne() {
        this.selectLimit(InfrasConstants.Pagination.ONE_PAGE_SIZE);
    }

    public void selectLimit(Long pageSize) {
        this.selectPage(InfrasConstants.Pagination.THRESHOLD_PAGE_NO, pageSize);
    }

    public void selectPage(Long current, Long pageSize) {
        this.setPageNo(current);
        this.setPageSize(pageSize);
    }

    // ----------------------------------------------------------------

    public void triggerThresholdPageSizeEnabled() {
        this.pageSize = InfrasConstants.Pagination.THRESHOLD_PAGE_SIZE;
    }
}
