package com.devicehive.auth;

import com.devicehive.utils.ThreadLocalVariablesKeeper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.net.InetAddress;

@WebFilter(urlPatterns = "/*")
public class IpDomainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String address = request.getRemoteAddr();
        InetAddress inetAddress = InetAddress.getByName(address);
        ThreadLocalVariablesKeeper.setClientIP(inetAddress);
        String canonicalHostName = inetAddress.getHostName();
        ThreadLocalVariablesKeeper.setHostName(canonicalHostName);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
