package spark;

import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class HelloWorld {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

//        Greeter greeter = new Greeter()
        port(getHerokuAssignedPort());
        get("/", (req, res) -> {
            return new ModelAndView(new HashMap<>(), "hello.hbs");
        }, new HandlebarsTemplateEngine());
        get("/hello", (req, res) -> {

            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("name", "Ziyanda");
            return new ModelAndView(dataMap, "hello.hbs");

        }, new HandlebarsTemplateEngine());
    }

}
