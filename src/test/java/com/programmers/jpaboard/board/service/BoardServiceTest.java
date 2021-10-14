package com.programmers.jpaboard.board.service;

import com.programmers.jpaboard.board.controller.dto.BoardCreationDto;
import com.programmers.jpaboard.board.controller.dto.BoardResponseDto;
import com.programmers.jpaboard.board.controller.dto.BoardUpdateDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("게시글을 저장한다")
    @Transactional
    public void saveTest() {
        // Given
        BoardCreationDto boardCreationDto = new BoardCreationDto("title", "content");

        // When
        BoardResponseDto responseDto = boardService.saveBoard(boardCreationDto);

        // Then
        assertThat(responseDto.getTitle()).isEqualTo(boardCreationDto.getTitle());
        assertThat(responseDto.getContent()).isEqualTo(boardCreationDto.getContent());
    }

    @Test
    @DisplayName("게시글 한 개를 조회한다.")
    @Transactional
    public void findOneTest() {
        // Given
        BoardCreationDto boardCreationDto = new BoardCreationDto("title", "content");
        BoardResponseDto responseDto = boardService.saveBoard(boardCreationDto);

        // When
        BoardResponseDto actual = boardService.findOne(responseDto.getId());

        // Then
        assertThat(actual.getId()).isEqualTo(responseDto.getId());
    }

    @Test
    @DisplayName("모든 게시글을 조회한다")
    @Transactional
    public void findAllTest() {
        // Given
        BoardCreationDto boardCreationDto = new BoardCreationDto("dev", "course");
        for (int i = 0; i < 3; i++) {
            boardService.saveBoard(boardCreationDto);
        }

        // When
        List<BoardResponseDto> all = boardService.findAll();

        // Then
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("게시글을 수정한다")
    @Transactional
    public void updateTest() {
        // Given
        BoardCreationDto boardCreationDto = new BoardCreationDto("updatedTitle", "updatedContent");
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto("updatedTitle","updatedContent");

        BoardResponseDto responseDto = boardService.saveBoard(boardCreationDto);

        // When
        BoardResponseDto actual = boardService.updateBoard(responseDto.getId(), boardUpdateDto);

        // Then
        assertThat(actual.getTitle()).isEqualTo(boardUpdateDto.getTitle());
        assertThat(actual.getContent()).isEqualTo(boardUpdateDto.getContent());
    }
}