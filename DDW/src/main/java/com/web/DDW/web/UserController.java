package com.web.DDW.web;

import com.web.DDW.config.auth.LoginUser;
import com.web.DDW.config.validator.CustomValidators;
import com.web.DDW.domain.user.User;
import com.web.DDW.web.dto.UserDto;
import com.web.DDW.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    //중복체크 유효성 검사
    private final CustomValidators.NameValidator NameValidator;
    private final CustomValidators.EmailValidator EmailValidator;
    private final CustomValidators.NickNameValidator NickNameValidator;

    // 유효성 검증을 위해 추가
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(EmailValidator);
        binder.addValidators(NickNameValidator);
        binder.addValidators(NameValidator);
    }
    @GetMapping("/user/loginPage")
    public String loginPage(){
        return "/user/loginPage";
    }

    //로그인
    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error",error);
        model.addAttribute("exception", exception);

        return "/user/user-login";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/auth/join")
    public String join(Model model){
        model.addAttribute("userDto", new UserDto.Request());
        return "user/user-join";
    }

    //회원가입
    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid UserDto.Request dto, Errors errors, Model model) {

        if (errors.hasErrors()) {
            System.out.println(":::::::::::::::::errors.toString():" + errors.toString());
            // 회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("userDto", dto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            // 회원가입 페이지로 다시 리턴
            return "/user/user-join";
        }
        System.out.println(":::::::::::::::::dto:" + dto.getPassword());
        userService.userJoin(dto);
        return "redirect:/auth/login";
    }


    /* 회원정보 수정 */
    @GetMapping("/user/user-modify")
    public String modify(@LoginUser UserDto.Response user, Model model) {
        if (user != null) {
        model.addAttribute("user", user);
        }
    return "/user/user-modify";
    }

}
