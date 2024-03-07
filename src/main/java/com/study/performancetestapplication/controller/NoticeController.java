package com.study.performancetestapplication.controller;

import com.study.performancetestapplication.entity.Notice;
import com.study.performancetestapplication.service.NoticeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/api/v1/notices")
    public List<Notice> getAllNoticesWithoutCache() {
        return noticeService.getAllNoticesWithoutCache();
    }

    @GetMapping("/api/v1/notices/cache")
    public List<Notice> getAllNoticesFromCache() {
        return noticeService.getAllNoticesFromCache();
    }
}
