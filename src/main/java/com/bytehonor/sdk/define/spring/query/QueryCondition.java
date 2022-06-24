package com.bytehonor.sdk.define.spring.query;

import java.util.Collection;
import java.util.Objects;

import com.bytehonor.sdk.define.spring.constant.HttpConstants;
import com.bytehonor.sdk.define.spring.constant.QueryLogic;

/**
 * 
 * @author lijianqiang
 *
 */
public final class QueryCondition {

    private static final int LIMIT_DEF = HttpConstants.LIMIT_DEF;

    private final QueryLogic logic;

    private final QueryPage page;

    private QueryOrder order;

    private final MatchColumnGroup group;

    private QueryCondition(QueryLogic logic, QueryPage page) {
        this.logic = logic;
        this.page = page;
        this.order = null;
        this.group = MatchColumnGroup.create();
    }

    public static QueryCondition one() {
        return and(0, 1);
    }

    public static QueryCondition and() {
        return and(0, LIMIT_DEF);
    }

    public static QueryCondition and(int offset, int limit) {
        return create(QueryLogic.AND, QueryPage.of(offset, limit));
    }

    public static QueryCondition or() {
        return or(0, LIMIT_DEF);
    }

    public static QueryCondition or(int offset, int limit) {
        return create(QueryLogic.OR, QueryPage.of(offset, limit));
    }

    public static QueryCondition create(QueryLogic logic, QueryPage page) {
        Objects.requireNonNull(logic, "logic");
        Objects.requireNonNull(page, "page");

        return new QueryCondition(logic, page);
    }

    private QueryCondition safeAdd(MatchColumn column) {
        group.safeAdd(column);
        return this;
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition eq(String key, String value) {
        return this.safeAdd(MatchColumn.eq(key, value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition eq(String key, Long value) {
        return this.safeAdd(MatchColumn.eq(key, value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition eq(String key, Integer value) {
        return this.safeAdd(MatchColumn.eq(key, value));
    }

    /**
     * 等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition eq(String key, Boolean value) {
        return this.safeAdd(MatchColumn.eq(key, value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition neq(String key, String value) {
        return this.safeAdd(MatchColumn.neq(key, value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition neq(String key, Long value) {
        return this.safeAdd(MatchColumn.neq(key, value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition neq(String key, Integer value) {
        return this.safeAdd(MatchColumn.neq(key, value));
    }

    /**
     * 不等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition neq(String key, Boolean value) {
        return this.safeAdd(MatchColumn.neq(key, value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition gt(String key, Long value) {
        return this.safeAdd(MatchColumn.gt(key, value));
    }

    /**
     * 大于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition gt(String key, Integer value) {
        return this.safeAdd(MatchColumn.gt(key, value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition egt(String key, Long value) {
        return this.safeAdd(MatchColumn.egt(key, value));
    }

    /**
     * 大于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition egt(String key, Integer value) {
        return this.safeAdd(MatchColumn.egt(key, value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition lt(String key, Long value) {
        return this.safeAdd(MatchColumn.lt(key, value));
    }

    /**
     * 小于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition lt(String key, Integer value) {
        return this.safeAdd(MatchColumn.lt(key, value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition elt(String key, Long value) {
        return this.safeAdd(MatchColumn.elt(key, value));
    }

    /**
     * 小于等于
     * 
     * @param key
     * @param value
     * @return
     */
    public QueryCondition elt(String key, Integer value) {
        return this.safeAdd(MatchColumn.elt(key, value));
    }

    public QueryCondition like(String key, String value) {
        return this.safeAdd(MatchColumn.like(key, value));
    }

    public QueryCondition likeLeft(String key, String value) {
        return this.safeAdd(MatchColumn.likeLeft(key, value));
    }

    public QueryCondition likeRight(String key, String value) {
        return this.safeAdd(MatchColumn.likeRight(key, value));
    }

    public QueryCondition strings(String key, Collection<String> value) {
        return this.safeAdd(MatchColumn.strings(key, value));
    }

    public QueryCondition longs(String key, Collection<Long> value) {
        return this.safeAdd(MatchColumn.longs(key, value));
    }

    public QueryCondition integers(String key, Collection<Integer> value) {
        return this.safeAdd(MatchColumn.integers(key, value));
    }

    public QueryCondition descBy(String key) {
        this.order = QueryOrder.descOf(key);
        return this;
    }

    public QueryCondition ascBy(String key) {
        this.order = QueryOrder.ascOf(key);
        return this;
    }

    public void setOffset(int offset) {
        this.page.setOffset(offset);
    }

    public void setLimit(int limit) {
        this.page.setLimit(limit);
    }

    public QueryPage getPage() {
        return page;
    }

    public QueryOrder getOrder() {
        return order;
    }

    public void setOrder(QueryOrder order) {
        this.order = order;
    }

    public QueryLogic getLogic() {
        return logic;
    }

    public MatchColumnGroup getGroup() {
        return group;
    }

}