package com.drive.common.security.handle;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.utils.JsonUtil;
import com.drive.common.core.utils.ServletUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败
 * @author DreamChan
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ServletUtils.renderString(response,
                HttpStatus.FORBIDDEN.value(),
                JsonUtil.toJson(R.failure(ResCodeEnum.FORBIDDEN, "授权失败，没有权限访问相关资源")));

    }

}
