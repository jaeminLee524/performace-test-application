package com.study.performancetestapplication.repository;

import com.study.performancetestapplication.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
