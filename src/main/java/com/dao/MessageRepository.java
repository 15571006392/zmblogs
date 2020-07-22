package com.dao;

import com.bean.LeavingMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<LeavingMessage, Long> {
}
