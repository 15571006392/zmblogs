package com.dao;

import com.bean.LeavingMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zm-Mmm
 */
public interface MessageRepository extends JpaRepository<LeavingMessage, Long> {
}
