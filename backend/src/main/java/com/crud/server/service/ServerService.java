package com.crud.server.service;

import com.crud.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Server create( Server server );

    Collection<Server> list( int limit );

    Server ping( String ipAddress ) throws IOException;

    Server get( Long id );

    Server update( Server server );

    Boolean delete( Long id );
}
