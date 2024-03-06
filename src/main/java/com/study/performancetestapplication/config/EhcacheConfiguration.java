package com.study.performancetestapplication.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.CacheConfiguration.TransactionalMode;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class EhcacheConfiguration {

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {

        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        // path는 DiskStoreConfiguration 클래스의 ENV enum 참조하거나 PhysicalPath로 설정
        configuration.diskStore(new DiskStoreConfiguration().path("java.io.tmpdir"));

        net.sf.ehcache.CacheManager manager = net.sf.ehcache.CacheManager.create(configuration);

        CacheConfiguration getCacheConfig = new CacheConfiguration()
            .maxEntriesLocalHeap(1000)
            .maxEntriesLocalDisk(10000)
            .eternal(false)
            .timeToIdleSeconds(1800)
            .timeToLiveSeconds(1800)
            .memoryStoreEvictionPolicy("LRU")
            .transactionalMode(TransactionalMode.OFF)
            .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP))
            .name("getCache");
        Cache getAuthenticatedMenuByUriCache = new Cache(getCacheConfig);

        // 캐시 추가
        manager.addCache(getAuthenticatedMenuByUriCache);

        return new EhCacheCacheManager(manager);
    }
}
