package com.dattran.notification;

import com.dattran.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.dattran.notification",
                "com.dattran.amqp",
        }
)
//@EnableEurekaClient // It acts as a eureka client
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
//
//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer rabbitMQMessageProducer,
//            NotificationConfig notificationConfig
//    ) {
//        return args -> {
//            rabbitMQMessageProducer.publish(
//                    new Person("Dat Tran", 25),
//                    notificationConfig.getInternalExChange(),
//                    notificationConfig.getInternalNotificationRoutingKey()
//            );
//        };
//    }
//
//    record Person(String name, int age) {}
}
