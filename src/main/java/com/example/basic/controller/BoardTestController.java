package com.example.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.entity.BoardFileTest;
import com.example.basic.entity.BoardTest;
import com.example.basic.repository.BoardFileTestRepository;
import com.example.basic.repository.BoardTestRepository;

@RestController
public class BoardTestController {
    @Autowired
    BoardTestRepository boardTestRepository;
    @Autowired
    BoardFileTestRepository boardFileTestRepository;

    @Transactional
    @GetMapping("/board_test")
    public String boardTest(@ModelAttribute BoardTest board) {
        BoardTest dbBoard = boardTestRepository.save(board);

        BoardFileTest file = new BoardFileTest();
        file.setFileName("A");
        file.setBoard(dbBoard);

        if (true) {
            // save 하기 전에 RuntimeException 오류 발생시키기
            throw new RuntimeException();
        }
        // board_test에는 제목22 데이터 들어감. board_file_test에는 데이터 들어가지 않음.
        // ===> transaction 적용 안된거 - 모든 작업은 같이 성공하거나 같이 실패해야함
        // @Transactional 적용시키면 오류가 발생했을때 알아서 작업 취소시킴(롤백)
        // @Transactional 사용시 주의사항 : RuntimeException 계열만 롤백됨
        // @Transactional(rollbackFor = {Exception.class, IOException.class}) : 다른 예외에도 사용하고 싶을때
        // 예외 : Exception(체크 예외 - 코드 작성할때 오류 발생), RuntimeException(언체크 예외 - 코드가 실행되는 도중에 오류 발생)

        boardFileTestRepository.save(file);

        return "완료";
    }
}
