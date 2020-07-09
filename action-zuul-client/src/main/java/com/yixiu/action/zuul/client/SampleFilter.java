package com.yixiu.action.zuul.client;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.RequestContent;

import javax.servlet.http.HttpServletRequest;

public class SampleFilter extends ZuulFilter {
    /**
     * 过滤器的类型，决定过滤器在请求的哪个生命周期内执行
     * pre代表在请求被路由之前执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序，数值越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();

        String access_token = request.getParameter("access_token");
        if(StringUtils.isBlank(access_token)){
            //过滤该请求
            rc.setSendZuulResponse(false);
            //设置返回的错误码
            rc.setResponseStatusCode(401);
            //返回的body内容
            rc.setResponseBody("Error");
            return null;
        }

        return null;
    }
}
