package com.itstudent.springbootdemo.interceptor;
import com.alibaba.fastjson.JSON;
import com.itstudent.springbootdemo.constant.ExceptionEnum;
import com.itstudent.springbootdemo.constant.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Project: springbootdemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/8/9 15:07
 * @Description: 登录状态拦截器
 */
public class RedisSessionInterceptor implements HandlerInterceptor{
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //无论访问的地址是不是正确的，都进行登录验证，登录成功后的访问再进行分发，404的访问自然会进入到错误控制器中
        HttpSession session=request.getSession();
        if(session.getAttribute("loginUserId")!=null){
            //验证当前请求的session是否是已登录的session
            String loginSessionId = redisTemplate.opsForValue().get("loginUser:" + session.getAttribute("loginUserId"));
            if (loginSessionId != null && loginSessionId.equals(session.getId()))
            {
                return true;
            }
        }
        response401(response);
        return false;
    }
    private void response401(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            response.getWriter().print(JSON.toJSONString(new ResultVo(ExceptionEnum.ACCOUNT_IS_NOTEXIT.getErrorCode(),ExceptionEnum.ACCOUNT_IS_NOTEXIT.getErrorMsg())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
