package com.mycompany.app.service.impl;

import com.mycompany.app.mapper.ClientMapper;
import com.mycompany.app.repository.database.ClientRepository;
import com.mycompany.app.response.ClientRequest;
import com.mycompany.app.response.ClientResponse;
import com.mycompany.app.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientResponse saveClient(ClientRequest request) {
        var client = ClientMapper.INSTANCE.toDomain(request);
        clientRepository.save(client);
        return ClientMapper.INSTANCE.toResponse(client);
    }
}