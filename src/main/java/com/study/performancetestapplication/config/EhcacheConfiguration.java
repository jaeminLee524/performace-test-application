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

        Cache getNoticesCacheConfig = findAllNoticeCacheConfig();
        manager.addCache(getNoticesCacheConfig);

        Cache getAllNoticesByPage = findByPage();
        manager.addCache(getAllNoticesByPage);

        return new EhCacheCacheManager(manager);
    }

    private static Cache findAllNoticeCacheConfig() {
        CacheConfiguration getCacheConfig = new CacheConfiguration()
            .maxEntriesLocalHeap(10000)
            .maxEntriesLocalDisk(0)
            .eternal(false)
            .statistics(false)
            .timeToIdleSeconds(60)
            .timeToLiveSeconds(60)
            .overflowToDisk(false)
            .diskPersistent(false)
            .memoryStoreEvictionPolicy("LRU")
            .name("findAllNotices");

        return new Cache(getCacheConfig);
    }

    private static Cache findByPage() {
        CacheConfiguration getCacheConfig = new CacheConfiguration()
            .maxEntriesLocalHeap(10000)
            .maxEntriesLocalDisk(0)
            .eternal(false)
            .statistics(false)
            .timeToIdleSeconds(60)
            .timeToLiveSeconds(60)
            .overflowToDisk(false)
            .diskPersistent(false)
            .memoryStoreEvictionPolicy("LRU")
            .name("findByPage");

        return new Cache(getCacheConfig);
    }
}
