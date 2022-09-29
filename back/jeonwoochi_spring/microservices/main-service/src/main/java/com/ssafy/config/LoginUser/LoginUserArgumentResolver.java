package com.ssafy.config.LoginUser;

import com.ssafy.exception.NotMatchException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.ssafy.exception.NotMatchException.TOKEN_NOT_MATCH;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Value("${token.secret}")
    private String SecretKey;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //return parameter.getParameterAnnotation(LoginUser.class) != null;
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public User resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        try {
            String authorizationHeader = webRequest.getHeader("Authorization");
            String jwt = authorizationHeader.replace("Bearer ", "");

            Claims body = Jwts.parser().setSigningKey(SecretKey)
                    .parseClaimsJws(jwt).getBody();
            User user = new User((Long)body.get("id"), (String)body.get("gender"),(Integer)body.get("age"));
            return user;
        } catch (ClassCastException e) {
            throw new NotMatchException(TOKEN_NOT_MATCH);
        }
    }
}