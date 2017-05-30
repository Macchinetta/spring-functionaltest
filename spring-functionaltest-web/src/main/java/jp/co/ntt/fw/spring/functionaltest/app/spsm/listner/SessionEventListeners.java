/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.spsm.listner;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.ntt.fw.spring.functionaltest.app.athn.listner.AuthenticationEventListeners;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SessionEventListeners {
    private static final String HANDLE_LOGOUT_KEY = SecurityContextLogoutHandler.class
            .getName().concat(".logout");

    private static final Logger log = LoggerFactory
            .getLogger(AuthenticationEventListeners.class);

    // (1)
    @Before(value = "execution(* org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler.logout(..))")
    public void handleLogout(JoinPoint joinPoint) {
        HttpServletRequest request = HttpServletRequest.class.cast(joinPoint
                .getArgs()[0]);
        log.info("SessionEventListeners execute [logout]");
        request.getSession().setAttribute(HANDLE_LOGOUT_KEY, true);
    }

    // (2)
    @EventListener
    public void handleSessionTimeout(HttpSessionDestroyedEvent event) { // (3)

        // (4)
        Boolean isHandleLogout = Boolean.class.cast(event.getSession()
                .getAttribute(HANDLE_LOGOUT_KEY));
        if (isHandleLogout != null && isHandleLogout) {
            log.info("SessionEventListeners execute [session timeout(logout)]");
            return;
        }

        // (5)
        List<SecurityContext> securityContexts = event.getSecurityContexts();
        for (SecurityContext securityContext : securityContexts) {
            // ログアウト処理などを実装する
            securityContext.getAuthentication().setAuthenticated(false);
            log.info(
                    "SessionEventListeners execute [session timeout username:{}]",
                    securityContext.getAuthentication().getName());
        }

    }
}
