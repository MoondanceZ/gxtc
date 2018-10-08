package com.rk.common.interceptor;

import com.rk.dto.ReturnResult;
import com.rk.util.AjaxUtils;
import com.rk.util.JsonUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;

/**
 * Created by Qin_Yikai on 2018-10-01.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在拦截点执行前拦截，如果返回true则不执行拦截点后的操作（拦截成功）
        // 返回false则不执行拦截
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        //System.out.println(request.getContextPath() + uri);
        if (uri.equals("/") || uri.equals("/user/signin") || session.getAttribute("USER") != null)
            return true;

        if (AjaxUtils.isAjaxRequest(request)){
            AjaxUtils.writeJson(ReturnResult.Error("未登录或登录已超时, 请重新登录"), response);
            return false;
        } else {
            //request.getRequestDispatcher("/").forward(request, response);  //转发到登录界面
            response.sendRedirect("/");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
        // 在处理过程中，执行拦截
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
        // 执行完毕，返回前拦截
    }
}
