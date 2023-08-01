package com.web.DDW.config.auth;

import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    //ArgumentResolver : 사용자가 컨트롤러의 메소드 인자값으로 임의의 값을 전달하려 할 때 사용된다.
    private final HttpSession session;

@Override
public boolean supportsParameter(MethodParameter parameter) {
    //supportsParameter : @LoginUser 어노테이션이 붙어 있고, 파라미터 클래스 타입이 UserDto인가 판단 후 true를 반환
    boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
    boolean isUserClass = UserDto.Response.class.equals(parameter.getParameterType());
    return isLoginUserAnnotation && isUserClass;
}
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return session.getAttribute("user");
    }

}
