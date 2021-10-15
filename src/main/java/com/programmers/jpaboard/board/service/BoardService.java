package com.programmers.jpaboard.board.service;

import com.programmers.jpaboard.board.controller.dto.BoardCreationDto;
import com.programmers.jpaboard.board.controller.dto.BoardResponseDto;
import com.programmers.jpaboard.board.controller.dto.BoardUpdateDto;
import com.programmers.jpaboard.board.converter.BoardConverter;
import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.board.exception.BoardNotFoundException;
import com.programmers.jpaboard.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    @Transactional
    public BoardResponseDto saveBoard(BoardCreationDto boardCreationDto) {
        Board board = boardConverter.convertBoardByCreation(boardCreationDto);
        Board saved = boardRepository.save(board);
        return this.boardConverter.convertBoardResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll().stream()
                .map(boardConverter::convertBoardResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findOne(Long boardId) {
        return boardRepository.findById(boardId)
                .map(boardConverter::convertBoardResponseDto)
                .orElseThrow(() -> new BoardNotFoundException(boardId));
    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardUpdateDto newBoard) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException(boardId));

        board.update(newBoard.getTitle(), newBoard.getContent());
        return this.boardConverter.convertBoardResponseDto(board);
    }

    @Transactional
    public Long deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
        return boardId;
    }
}
