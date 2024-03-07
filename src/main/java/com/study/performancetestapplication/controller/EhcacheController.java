package com.study.performancetestapplication.controller;

import com.study.performancetestapplication.service.NoticeCacheService;
import com.study.performancetestapplication.service.NoticeService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EhcacheController {

    private final EhCacheCacheManager cacheManager;
    private final NoticeCacheService noticeCacheService;

    @GetMapping("/api/cache")
    public Object findAll(){

        List<Map<String, List<String>>> result = cacheManager.getCacheNames().stream()
            .map(cacheName -> {
                EhCacheCache cache = (EhCacheCache) cacheManager.getCache(cacheName);
                Ehcache ehcache = cache.getNativeCache();
                Map<String, List<String>> entry = new HashMap<>();

                ehcache.getKeys().forEach(key -> {
                    Element element = ehcache.get(key);
                    if (element != null) {
                        entry.computeIfAbsent(cacheName, k -> new ArrayList<>()).add(element.toString());
                    }
                });

                return entry;
            })
            .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/cache/evict")
    public void noticeCacheEvict() {
        noticeCacheService.cacheEvict();
    }
}
