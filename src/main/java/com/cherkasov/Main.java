package com.cherkasov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Config.class);
//        context.register(Config.class); //
//        context.refresh();

        DataSource bean = context.getBean(DataSource.class);
        System.out.println(bean.toString());
    }
}
