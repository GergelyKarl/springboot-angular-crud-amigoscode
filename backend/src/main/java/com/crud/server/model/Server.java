package com.crud.server.model;

import com.crud.server.enumeration.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "IP cím nem lehet ures")
    private String ipAddress;

    private String name;
    private String memory;
    private String type;
    // private String imageUrl;
    private Status status;

}
