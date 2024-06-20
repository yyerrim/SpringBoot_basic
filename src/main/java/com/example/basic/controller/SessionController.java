package com.example.basic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.entity.Board;
import com.example.basic.entity.User;
import com.example.basic.model.BoardModel;
import com.example.basic.model.UserModel;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.UserRepository;
import com.example.basic.util.EncryptUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    EncryptUtil encryptUtil;

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @PostMapping("/board")
    public String boardPost(BoardModel boardModel) {
        Board b = new Board();
        b.setTitle(boardModel.getTitle());
        boardRepository.save(b);
        return "redirect:/main";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    // @PostMapping("/join")
    // public String joinPost(User user) {
    //     com.example.basic.entity.User u = new com.example.basic.entity.User();
    //     u.setUserId(user.getUserId());
    //     u.setUserPw(user.getUserPw());
    //     userRepository.save(u);
    //     return "redirect:/login";
    // }
    // 강사님 풀이
    // @PostMapping("/join")
    // public String joinPost(UserModel user) { // User = model
    //     // com.example.basic.entity.User u = new com.example.basic.entity.User(user.getUserId(), user.getUserPw());
    //     // // 2개의 값을 넣는 생성자를 만들지 않았기 때문에 오류 발생
    //     // 생성자 안만들려면 아래 방법 사용
    //     com.example.basic.entity.User u = new com.example.basic.entity.User();
    //     u.setUserId(user.getUserId());
    //     u.setUserPw(user.getUserPw());
    //     userRepository.save(u); // u는 entity 안에 있는것이어야함 ===> com.example.basic.entity.User u : User 경로 설정
    //     return "redirect:/login";
    // }
    // model, entity 파일명 같은 경우 import 하기 불편 => 보통은 UserEntity 라고 설정 (임시방편으로 model을 UserModel로 수정했음)
    @PostMapping("/join")
    public String joinPost(UserModel user) {
        User u = new User();
        u.setUserId(user.getUserId());

        // 비밀번호 암호화
        String encodedPw = encryptUtil.encode(user.getUserPw());
        u.setUserPw(encodedPw);
        
        userRepository.save(u);
        return "redirect:/login";
    }
    // TODO 아이디가 중복되면 비밀번호가 변경되고있음

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // @PostMapping("/login")
    // public String loginPost(UserModel user, HttpSession session) {
    //     com.example.basic.entity.User dbUser = userRepository.findByUserIdAndUserPw(user.getUserId(), user.getUserPw());
    //     // 오류 발생 원인
    //     // 1. 변수 중복 : User user ===> User dbUser
    //     // 2. User user(model import), User dbUser(entity import) : 클래스의 이름이 갖고 패키지가 다른 경우 두개 한번에 import 불가능
    //     // ===> 둘 중 하나의 위치 정보를 다 써주면됨 : com.example.basic.entity.User dbUser
    //     if (dbUser == null) {
    //         return "redirect:/login";
    //     }
    //     session.setAttribute("user", user);
    //     return "redirect:/main";
    // }
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> loginPost(
            @RequestBody UserModel user, HttpSession session) {

        Map<String, Object> map = new HashMap<>();

        String encodedPw = encryptUtil.encode(user.getUserPw()); // 비밀번호 암호화
        User dbUser = userRepository.findByUserIdAndUserPw(user.getUserId(), encodedPw);
        if (dbUser == null) {
            map.put("msg", "ID 또는 PW를 확인해주세요.");
            return map;
        }
        session.setAttribute("user", user);
        map.put("msg", "로그인되었습니다.");
        return map;
        // redirect:/OOO : 사용자가 보내온 요청을 처리한 후 그 요청을 새롭게 /OOO 주소로 보냄
        // 기존에 받은 요청(ex.userId, userPw)은 없어짐
        // 기존에 받은 요청을 같이 새로운 곳으로 보내고 싶다면 ?OOO 작성해주면됨
    }

    // 로그아웃 : 세션 안에 있는 데이터를 제거
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션의 모든 데이터 삭제
        // = session.removeAttribute("user");
        return "redirect:/login";
    }

    @GetMapping("/main")
    public String main(HttpSession session) {
        UserModel u = (UserModel) session.getAttribute("user"); // object로 들어갔다가 object로 나오기 때문에 형변환
        if (u == null) {
            return "redirect:/login";
            // "로그인 하고 오세요"를 출력할 수 있는 view 파일을 만들어서 리턴시켜도됨
        }
        String id = u.getUserId();
        String pw = u.getUserPw();
        System.out.println(id + ", " + pw); // TERMINAL에 값 출력됨
        return "main";
    }
}
