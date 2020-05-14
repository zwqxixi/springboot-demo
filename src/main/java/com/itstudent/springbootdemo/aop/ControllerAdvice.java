/*
package com.itstudent.springbootdemo.aop;

import com.alibaba.a.config.annotation.Reference;
import com.alibaba.a.rpc.RpcException;
import com.rdf.biz.base.client.constant.ClientEnum;
import com.rdf.biz.base.client.service.AuthService;
import com.rdf.biz.common.constant.ApiResultCode;
import com.rdf.biz.common.exception.RdfException;
import com.rdf.biz.common.vo.ResultVo;
import com.rdf.thirdparty.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

*/
/**
 * @program: rdf-platform-v2
 * @description: 操作日志增强类
 * @author: LiJiufan
 * @create: 2018-11-27 19:24
 **//*


@Component
@Aspect
@Slf4j
@Order(2)
public class ControllerAdvice {

    @Autowired
    private HttpServletRequest request;

    @Reference(version = "1.0.0", timeout = CommonConstant.RPC_TIME_OUT)
    private AuthService authService;


    @Pointcut(value = "execution(public * com.rdf.appapi.controller..*.*(..)) || execution(public * com.rdf.appapi.auth.controller..*.*(..))")
    public void executePackage() {
    }


    @Around("executePackage()")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        Object result = null;
        try {
            if (request.getRequestURI().indexOf("/auth/token") == -1) {
                Integer userId = checkLogin();
                if (userId == null) {
                    ResultVo<String> resultVo = new ResultVo();
                    resultVo.setSuccess(false);
                    resultVo.setResultCode(ApiResultCode.API_CODE_TOKEN_EXPIRED.getCode());
                    resultVo.setErrorMessage(ApiResultCode.API_CODE_TOKEN_EXPIRED.getMessage());
                    result = resultVo;
                    return result;
                }
//              Parameter[] parameters = method.getParameters();
                ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
                String[] parameterNames = pnd.getParameterNames(method);
                if (parameterNames != null && parameterNames.length > 0) {
                    for (int i = 0; i < parameterNames.length; i++) {
                        if ("userId".equals(parameterNames[i])) {
                            joinPoint.getArgs()[i] = userId;
                        }
                    }
                }
                log.debug("UserId:[{}],RequestUrl:[{}],RemoteIp:[{}]", userId, request.getRequestURI(), request.getHeader("X-Forwarded-For"));
            }
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (RdfException re) {
            log.error("业务处理异常：{}", re.getErrorCode() + ":" + re.getErrorMsg());
            ResultVo<String> resultVo = new ResultVo();
            resultVo.setSuccess(false);
            resultVo.setResultCode(re.getErrorCode());
            resultVo.setErrorMessage(re.getErrorMsg());
            result = resultVo;
        } catch (RpcException re) {
            log.error("调用服务异常:{}", re.getMessage());
            ResultVo<String> resultVo = new ResultVo();
            resultVo.setSuccess(false);
            resultVo.setResultCode(ApiResultCode.API_CODE_TIMEOUT.getCode());
            resultVo.setErrorMessage(ApiResultCode.API_CODE_TIMEOUT.getMessage());
            result = resultVo;
        } catch (UndeclaredThrowableException ute) {
            log.error("调用服务异常:{}", ute.getMessage());
            Throwable undeclaredThrowable = ute.getUndeclaredThrowable();
            Throwable cause = undeclaredThrowable.getCause();
            String errorCode = ApiResultCode.API_CODE_UNKNOW.getCode();
            String errorMsg = ApiResultCode.API_CODE_UNKNOW.getMessage();
            if (cause instanceof RdfException) {
                errorCode = ((RdfException) cause).getErrorCode();
                errorMsg = ((RdfException) cause).getErrorMsg();
            }
            log.error("基础中台服务业务异常：{}", errorCode + ":" + errorMsg);
            ResultVo<String> resultVo = new ResultVo();
            resultVo.setSuccess(false);
            resultVo.setResultCode(errorCode);
            resultVo.setErrorMessage(errorMsg);
            result = resultVo;
        } catch (Throwable throwable) {
            ResultVo<String> resultVo = new ResultVo();
            resultVo.setSuccess(false);
            resultVo.setResultCode(ApiResultCode.API_CODE_UNKNOW.getCode());
            resultVo.setErrorMessage(ApiResultCode.API_CODE_UNKNOW.getMessage());
            result = resultVo;
            log.error("系统未检测异常:Message:{} \n StackTrace:{}" + "", throwable.getMessage(), throwable.getStackTrace());
        } finally {
            return result;
        }
    }

    */
/**
     * @Description: 当方法参数上没有注入了 @currentUser 在此处做登录校验
     * @Param: []
     * @return: void
     * @Author: LiJiufan
     * @Date: 2019/2/14
     *//*

    private Integer checkLogin() {
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new RdfException("", "未获取到有效token");
        }
        return authService.getUserIdByToken(token, ClientEnum.API_APP_B);

    }
}
*/
