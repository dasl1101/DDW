package com.web.DDW.config.validator;

import com.web.DDW.domain.user.UserRepository;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CustomValidators {

    @RequiredArgsConstructor
    @Component
    public static class NameValidator extends AbstractValidator<UserDto.Request> {
        private final UserRepository userRepository;

        @Override
        protected void doValidate(UserDto.Request dto, Errors errors) {
            if (userRepository.existsByName(dto.toEntity().getName())) {
                errors.rejectValue("name", "아이디 중복", "이미 사용중인 아이디 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class EmailValidator extends AbstractValidator<UserDto.Request> {
        private final UserRepository userRepository;

        @Override
        protected void doValidate(UserDto.Request dto, Errors errors) {
            if (userRepository.existsByEmail(dto.toEntity().getEmail())) {
                errors.rejectValue("email", "이메일 중복", "이미 사용중인 이메일 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class NickNameValidator extends AbstractValidator<UserDto.Request> {
        private final UserRepository userRepository;

        @Override
        protected void doValidate(UserDto.Request dto, Errors errors) {
            if (userRepository.existsByNickName(dto.toEntity().getNickName())) {
                errors.rejectValue("nickname", "닉네임 중복", "이미 사용중인 닉네임 입니다.");
            }
        }
    }


}
