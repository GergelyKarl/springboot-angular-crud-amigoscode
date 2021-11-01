package com.crud.server.controller;

import com.crud.server.enumeration.Status;
import com.crud.server.model.Response;
import com.crud.server.model.Server;
import com.crud.server.service.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
@Component
public class ServerController {
    private final ServerServiceImplementation serverServiceImpl;

    @GetMapping("/test")
    public String test() {
        return "diff";
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {

        return ResponseEntity.ok(Response
                .builder()
                .timeStamp(now())
                .data(Map.of("servers", serverServiceImpl.list(30)))
                .message("servers retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer( @PathVariable("ipAddress") String ipAddress ) throws IOException {
        Server server = serverServiceImpl.ping(ipAddress);

        return ResponseEntity.ok(Response
                .builder()
                .timeStamp(now())
                .data(Map.of("servers", server))
                .message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping Failed")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer( @RequestBody @Valid Server server ) throws IOException {


        return ResponseEntity.ok(Response
                .builder()
                .timeStamp(now())
                .data(Map.of("servers", serverServiceImpl.create(server)))
                .message("server created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer( @PathVariable("id") Long id ) throws IOException {


        return ResponseEntity.ok(Response
                .builder()
                .timeStamp(now())
                .data(Map.of("servers", serverServiceImpl.get(id)))
                .message("Server got")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer( @PathVariable("id") Long id ) throws IOException {


        return ResponseEntity.ok(Response
                .builder()
                .timeStamp(now())
                .data(Map.of("deleted", serverServiceImpl.delete(id)))
                .message("Server deleted")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }


}
