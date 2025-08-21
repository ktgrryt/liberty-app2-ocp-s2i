package com.app2.servlet;
import java.io.IOException;
import java.util.List;

import com.app2.common.PrefectureProcessor;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/app2/disp")
public class DispServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/PrefecturesServlet.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 文字エンコーディングを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String numStr = request.getParameter("prefectures_num"); // JSPから受け取り
        PrefectureProcessor processor = new PrefectureProcessor();
        List<String> chosen_list = processor.processPrefectures(numStr);

        // chosen_listが空の場合にエラーメッセージを表示
        if (chosen_list.isEmpty()) {
            request.setAttribute("errorMessage", "有効な値を入力してください！");
        }

        request.setAttribute("chosen_list", chosen_list);
        request.getRequestDispatcher("/disp.jsp").forward(request, response);
    }
}