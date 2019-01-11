package linkjb.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncoidingFilter implements Filter {

    private String encoding="utf-8";
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }


    //过滤方法  是否往下执行
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)arg0;
        HttpServletResponse response=(HttpServletResponse)arg1;

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        //过滤通行证
        chain.doFilter(request, response);
    }

    //根据web.xml文件的配置进行初始化
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        this.encoding = arg0.getInitParameter("Encoding");

    }

}