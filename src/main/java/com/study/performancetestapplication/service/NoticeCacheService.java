package com.study.performancetestapplication.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class NoticeCacheService {

    @CacheEvict(cacheNames = {"findAllNotices"})
    public void cacheEvict() {

    }
}
