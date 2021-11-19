package com.vansisto.controllers;

import com.google.gson.Gson;
import com.vansisto.entities.User;
import com.vansisto.repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@WebServlet("/user")
public class UserController extends HttpServlet {

    UserRepository userRepository = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idS = req.getParameter("id");
        if (Objects.isNull(idS)) {
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(userRepository.getUsers().toString());

            printWriter.close();
        } else {
            int id = Integer.valueOf(idS);

            PrintWriter printWriter = resp.getWriter();

            List<User> users = userRepository.getUsers();
            User user = users.get(id);

            printWriter.write(user.toString());

            printWriter.close();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        User user = gson.fromJson(jb.toString(), User.class);

        userRepository.save(user);

    }
}
