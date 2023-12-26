package com.web.demo.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url="",accept = "application/json",contentType = "application/json")
public interface AuditClientService {

    @Async
    @PostExchange("audit/saveAudit")
    void saveAudit(@RequestBody Object obj);
}
