package com.hb0730.security5.authorization.login.authorization.server.service.impl;

import com.google.common.collect.Lists;
import com.hb0730.security5.authorization.login.authorization.server.model.ScopeDetails;
import com.hb0730.security5.authorization.login.authorization.server.service.IScopeDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限范围
 *
 * @author bing_huang
 */
@Service
public class ScopeDetailsServiceImpl implements IScopeDetailsService {
    @Override
    public List<ScopeDetails> findScopeByIds(Collection<String> ids) {
        List<ScopeDetails> scopeDetails = Lists.newArrayList();
        scopeDetails.add(ScopeDetails.builder().scopeId("read").name("读").build());
        scopeDetails.add(ScopeDetails.builder().scopeId("writer").name("写").build());
        scopeDetails.add(ScopeDetails.builder().scopeId("user").name("用户信息").build());
        return scopeDetails.stream().filter(scope -> ids.contains(scope.getScopeId())).collect(Collectors.toList());
    }
}
