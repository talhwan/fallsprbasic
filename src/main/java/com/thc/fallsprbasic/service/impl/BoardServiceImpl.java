package com.thc.fallsprbasic.service.impl;

import com.thc.fallsprbasic.domain.Board;
import com.thc.fallsprbasic.repository.BoardRepository;
import com.thc.fallsprbasic.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    public BoardServiceImpl(
            BoardRepository boardRepository
    ) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Map<String, Object> createBoard(Map<String, Object> params) {
        System.out.println("createBoard");
        Board board = new Board();
        board.setId(Integer.parseInt(params.get("id") + ""));
        board.setTitle(params.get("title").toString());
        board.setContent(params.get("content").toString());
        board.setAuthor(params.get("author").toString());
        boardRepository.save(board);
        return null;
    }
    @Override
    public List<Board> listBoard() {
        return boardRepository.findAll();
    }
}
