package com.shop.fliter;

import com.shop.bean.dto.UserInfoDto;
import com.shop.enums.BusinessEnum;
import com.shop.util.ServiceResult;
import com.shop.util.common.SessionUtil;
import com.shop.util.token.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class AuthValidFilter implements Filter {

    /**
     * 排除不拦截的url
     */
    private static final Set<String> EXCLUDE_PATH_SETS = new HashSet<>();

    static {
        EXCLUDE_PATH_SETS.add("/wxMiniShop/userInfo/checkOpenId");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        String url = httpServletRequest.getRequestURI();
        if(log.isDebugEnabled()){
            log.debug("auth valid filter requestUri:[{}]", url);
        }
        if (EXCLUDE_PATH_SETS.contains(url)) {
            log.info( " auth filter exclude url:[{}] ", url);
            filterChain.doFilter(request, response);
            return;
        }
        String thirdSessionKey = httpServletRequest.getHeader("thirdSessionKey");
        UserInfoDto userInfoDto = (UserInfoDto) SessionUtil.getSession(httpServletRequest, UserInfoDto.LOGIN_USER_INFO);
        if(userInfoDto == null){
            writeFailInfoToWeb(httpServletResponse, BusinessEnum.SESSION_OUT_PLEASE_RE_OPEN_PAGE);
            return;
        }
        boolean isLogin = CheckUtil.checkCsrfToken(httpServletRequest, httpServletResponse, log);
        if (!isLogin){
            log.warn("CsrfToken校验失败");
            writeFailInfoToWeb(httpServletResponse, BusinessEnum.SESSION_OUT_PLEASE_RE_OPEN_PAGE);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void writeFailInfoToWeb(HttpServletResponse response, BusinessEnum businessEnum){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            log.error("writeFailInfoToWeb IOException", e);
        }
        ServiceResult result = new ServiceResult();
        result.setSuccess(false);
        result.setCode(businessEnum.getValue());
        result.setMessage(businessEnum.getValueInFact());
        out.print(result.toJSON());
        out.close();
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("  ===================AuthValidFilter init finished==================  ");
    }
}
