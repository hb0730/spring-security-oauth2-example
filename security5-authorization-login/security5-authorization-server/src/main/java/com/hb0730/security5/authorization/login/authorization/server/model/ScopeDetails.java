package com.hb0730.security5.authorization.login.authorization.server.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 权限范围
 *
 * @author bing_huang
 */
@Data
@EqualsAndHashCode
@Builder
public class ScopeDetails implements Serializable {
    private static final long serialVersionUID = 2669137009572642842L;
    /**
     * scopeid
     */
    private String scopeId;
    /**
     * name
     */
    private String name;
}
