package com.web.hosp.services.client;

import com.web.demo.response.EmpResponseRec;
import com.web.demo.response.EmpSingleResponseRec;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@HttpExchange(url = "", accept = "application/json", contentType = "application/json")
public interface EmployeeClientService {

    @GetExchange("employees")
    EmpResponseRec listAllEmployees();

    @GetExchange("/employees")
    Flux<EmpResponseRec> getAll();

    @GetExchange("/employee/{id}")
    EmpSingleResponseRec getById(@PathVariable("id") int id);

   /* @PostExchange("/")
    Mono<ResponseEntity<Void>> save(@RequestBody Student student);

    @PutExchange("/{id}")
    Mono<ResponseEntity<Void>> update(@PathVariable Long id, @RequestBody Student student);*/

    @DeleteExchange("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable Long id);

   /* Http Exchange annotations
    @HttpExchange annotation is given at the interface level and it applies to all the methods. We can also attach an endpoint to this annotation as seen in the url property.
    @GetExchange annotation is used for HTTP GET requests.
    @PostExchange annotation is used for HTTP POST requests.
    @PutExchange annotation is used for HTTP PUT requests.
    @DeleteExchange annotation is used for HTTP DELETE requests.
    @PatchExchange annotation is used for HTTP PATCH requests.*/

    /*Method Arguments
    @RequestBody provides the body of the request.
    @PathVariable is used to replace the placeholder with a value in the uri.
    @RequestParam is used to add the request parameters which are added as URL query parameters.
    @RequestHeader is used to add request header names and values.
    @CookieValue is used to add the cookies to the request.
    @RequestPart is used to add a request part (form field, resource or HttpEntity etc).*/
}
