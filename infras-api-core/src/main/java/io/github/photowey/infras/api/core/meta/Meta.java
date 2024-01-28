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
package io.github.photowey.infras.api.core.meta;

import io.github.photowey.infras.api.common.constant.InfrasConstants;
import io.github.photowey.infras.api.core.model.PageResult;

import java.io.Serializable;

/**
 * {@code Meta}
 *
 * @author photowey
 * @date 2024/01/28
 * @since 1.0.0
 */
public class Meta implements Serializable {

    private static final long serialVersionUID = -553396062548580887L;

    private Long pageNo;

    private Long pageSize;

    private Long count;

    private Long pages;

    // ----------------------------------------------------------------

    public static <D> Meta populateMeta(PageResult<D> page) {
        return Meta.builder()
                .pageNo(page.getData().pageNo())
                .pageSize(page.getData().pageSize())
                .count(page.getData().count())
                .build();
    }

    public static Meta populateDefaultMeta() {
        return populateMeta(InfrasConstants.Pagination.DEFAULT_PAGE_SIZE);
    }

    public static Meta populateMeta(long pageSize) {
        return populateMeta(InfrasConstants.Pagination.EMPTY, InfrasConstants.Pagination.DEFAULT_PAGE_NO, pageSize);
    }

    public static Meta populateMeta(Long count, Long pageNo, Long pageSize) {
        return builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .count(count)
                .pages(determinePages(count, pageSize))
                .build();
    }

    // ----------------------------------------------------------------

    private static long determinePages(Long count, Long pageSize) {
        if (pageSize == 0L) {
            return 0L;
        } else {
            long pages = count / pageSize;
            if (count % pageSize != 0L) {
                ++pages;
            }

            return pages;
        }
    }

    // ----------------------------------------------------------------

    public Long getPageNo() {
        return this.pageNo;
    }

    public Long getPageSize() {
        return this.pageSize;
    }

    public Long getCount() {
        return this.count;
    }

    public Long getPages() {
        return this.pages;
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

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public String toString() {
        Long pageNo = this.getPageNo();
        return "Meta(pageNo=" + pageNo + ", pageSize=" + this.getPageSize() + ", count=" + this.getCount() + ", pages=" + this.getPages() + ")";
    }

    public Meta() {
    }

    public Meta(Long pageNo, Long pageSize, Long count, Long pages) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.count = count;
        this.pages = pages;
    }

    // ----------------------------------------------------------------

    public static MetaBuilder builder() {
        return new MetaBuilder();
    }

    // ----------------------------------------------------------------

    public static class MetaBuilder {

        private Long pageNo;
        private Long pageSize;
        private Long count;
        private Long pages;

        MetaBuilder() {
        }

        public MetaBuilder pageNo(Long pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public MetaBuilder pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public MetaBuilder count(Long count) {
            this.count = count;
            return this;
        }

        public MetaBuilder pages(Long pages) {
            this.pages = pages;
            return this;
        }

        public Meta build() {
            return new Meta(this.pageNo, this.pageSize, this.count, this.pages);
        }

        public String toString() {
            return "Meta.MetaBuilder(pageNo=" + this.pageNo + ", pageSize=" + this.pageSize + ", count=" + this.count + ", pages=" + this.pages + ")";
        }
    }
}