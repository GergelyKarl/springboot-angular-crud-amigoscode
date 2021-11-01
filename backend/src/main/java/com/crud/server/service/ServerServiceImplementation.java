package com.crud.server.service;

import com.crud.server.enumeration.Status;
import com.crud.server.model.Server;
import com.crud.server.repo.ServerRepo;
import com.crud.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create( Server server ) {
        //setImageUrl(setServerImageURL());
        log.info("New server created.Server name is: {}", server.getName());
        return serverRepo.save(server);

    }

    @Override
    public Collection<Server> list( int limit ) {
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();

    }

    @Override
    public Server ping( String ipAddress ) throws IOException {
        log.info("Pinged IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Server get( Long id ) {
        log.info("Server by ID: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update( Server server ) {
        log.info("Server by ID: {}", server.getName());
    return serverRepo.save(server);
    }

    @Override
    public Boolean delete( Long id ) {
        log.info("Server deleted by ID: {}", id);
       serverRepo.deleteById(id);
       return Boolean.TRUE;
    }

   /* public String setServerImageURL() {
        String[] imageNames={"1","2"};
        int randomNumber= (int) Math.floor(Math.random()*imageNames.length);
        return imageNames[randomNumber];
    }

    */
}
