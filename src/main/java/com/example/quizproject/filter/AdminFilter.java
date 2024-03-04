package com.example.quizproject.filter;

import com.example.quizproject.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        System.out.print("In LoginFilter:  " + request.getRequestURI());
        if (session != null && session.getAttribute("user") != null) {

            User user = (User) session.getAttribute("user");
            System.out.println(user);
            if (user.isIs_active() && user.isIs_admin()) {
                System.out.println("Pass");
                filterChain.doFilter(request,response);
            } else {
                response.sendRedirect("/logout");
            }
        } else {
            response.sendRedirect("/logout");
        }
    }

}
