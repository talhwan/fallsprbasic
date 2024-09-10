package com.thc.fallsprbasic.service;

import com.thc.fallsprbasic.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BoardService {
    Map<String, Object> createBoard(Map<String, Object> params);
    List<Board> listBoard();
}
