package com.study.performancetestapplication.service;

import com.study.performancetestapplication.entity.Notice;
import com.study.performancetestapplication.repository.NoticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> getAllNoticesWithoutCache() {
        return noticeRepository.findAll();
    }

    @Cacheable(value = "findAllNotices")
    public List<Notice> getAllNoticesFromCache() {
        return noticeRepository.findAll();
    }
}
