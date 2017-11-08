package cn.gqbit.myblog.auth;

import cn.gqbit.myblog.entity.User;
import cn.gqbit.myblog.service.IUserService;
import cn.gqbit.myblog.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    IUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =(UsernamePasswordAuthenticationToken)authentication;
        String userName=usernamePasswordAuthenticationToken.getName();
        User user=null;
        if(userName!=null){
            user=userService.loadUserByName(userName);
        }
        if(user==null){
            throw new UsernameNotFoundException("用户名/密码无效");
        }
        String password=user.getPasswd();
        String md5Passwd= Md5Utils.pwdDigest(usernamePasswordAuthenticationToken.getCredentials().toString());
        if (!password.equals(md5Passwd)) {

            throw new BadCredentialsException("Invalid username/password");
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        //授权
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
