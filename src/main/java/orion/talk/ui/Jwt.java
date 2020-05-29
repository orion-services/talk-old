/**
 * @License
 * Copyright 2020 Orion Services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package orion.talk.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.JwtBuilder;

import org.jsoup.Jsoup;

@WebServlet("/jwt.io")
public class Jwt extends HttpServlet {

    private void execute(HttpServletRequest request, HttpServletResponse response) {

        // Get data from form
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        try {
            // Generates Json Web Token
            String jwt = JwtBuilder.create("jwtBuilder").jwtId(true).claim(Claims.SUBJECT, user).claim("upn", user)
                    .claim("pass", pass).claim("groups", "users").buildJwt().compact();

            // send to REST API
            Jsoup.connect("http://localhost:9081/talk/api/v1.0/jwt").header("Authorization", "Bearer " + jwt).post();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

}