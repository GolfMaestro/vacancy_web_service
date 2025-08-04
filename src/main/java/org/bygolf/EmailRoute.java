package org.bygolf;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
//public class EmailRoute extends RouteBuilder {
//
//    @Override
//    public void configure() {
//        from("direct:sendEmail")
//                .setHeader("subject", constant("Уведомление о новых вакансиях"))
//                .to("smtp://smtp.gmail.com:587?username=test@gmail.com&password=testpass")
//                .end();
//    }
//}