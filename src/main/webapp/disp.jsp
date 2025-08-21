<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>App2</title>
</head>
<body>
    <form method="post" action="/liberty01/app2/disp">
        表示する数　<input type="number" name="prefectures_num"/><br>
    <input type="submit" value="表示"><br><br>
    </form>
    
    <table border=1>
		<%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
                out.print(errorMessage);
            } else {
                List<String> chosen_list = (List<String>) request.getAttribute("chosen_list");
                int pre_num = chosen_list.size();
                    for (int i = 0; i < chosen_list.size(); i++) {
                        String data = chosen_list.get(i);
                        out.print("<tr><td>"+data+"</td></tr>"); 
                }
            }
		%>
	</table>
</body>
</html>
