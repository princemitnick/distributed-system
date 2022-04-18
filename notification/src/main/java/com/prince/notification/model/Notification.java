package com.prince.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EnableEurekaClient
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {


    @Id
    @SequenceGenerator(
            name = "notification_sequence",
            sequenceName = "notification_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_sequence"
    )
    private Integer notificationId;
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String sender;
    private LocalDateTime sentAt;
    private String message;

}
