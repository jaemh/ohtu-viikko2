package ohtu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ohtu.App.java;


public static void main(String[] args) {
    ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

    App application = ctx.getBean(App.class);
    application.run();
}
