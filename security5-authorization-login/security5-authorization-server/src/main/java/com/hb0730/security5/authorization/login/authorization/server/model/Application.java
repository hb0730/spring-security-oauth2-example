package com.hb0730.security5.authorization.login.authorization.server.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bing_huang
 */
@Data
public class Application implements Serializable {
    private static final long serialVersionUID = -2092675914831375846L;
    /**
     * appid
     */
    private String appid;
    /**
     * name
     */
    private String name;
}
