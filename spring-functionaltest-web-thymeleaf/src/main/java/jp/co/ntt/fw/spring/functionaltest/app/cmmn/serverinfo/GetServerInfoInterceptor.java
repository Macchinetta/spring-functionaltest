package jp.co.ntt.fw.spring.functionaltest.app.cmmn.serverinfo;

import javax.annotation.Nullable;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class GetServerInfoInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            ServletContext context = request.getServletContext();
            String info = context.getServerInfo();
            modelAndView.addObject("serverInfo", info);
        }
    }
}
