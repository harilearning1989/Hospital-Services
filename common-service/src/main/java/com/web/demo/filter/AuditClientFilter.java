package com.web.demo.filter;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.client.reactive.ClientHttpRequestDecorator;
import org.springframework.lang.NonNull;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

public class AuditClientFilter implements ExchangeFilterFunction {

    private static final int MAX_BYTES_LOGGED = 4_096;

	@Override
	@NonNull
	public Mono<ClientResponse> filter(@NonNull ClientRequest clientRequest, @NonNull ExchangeFunction next) {

		var capturedRequestBody = new StringBuilder();
		var capturedResponseBody = new StringBuilder();
	
		var stopWatch = new StopWatch();
		stopWatch.start();

		//ClientAudit clientAudit = new ClientAudit();
		var requestAttributes = RequestContextHolder.getRequestAttributes();
		return next.exchange(ClientRequest.from(clientRequest).body(new BodyInserter<>() {
			@Override
			@NonNull
			public Mono<Void> insert(@NonNull ClientHttpRequest req, @NonNull Context context) {
				return clientRequest.body().insert(new ClientHttpRequestDecorator(req) {
					@Override
					@NonNull
					public Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
						return super.writeWith(
								Flux.from(body).doOnNext(data -> capturedRequestBody.append(extractBytes(data))));
					}
				}, context);
			}
		}).build()).doOnNext(response -> {
			var url = String.valueOf(clientRequest.url());
			var serviceName = url.substring(url.lastIndexOf("/") + 1);
			var method = clientRequest.method().name();
			/*clientAudit.setRequestData(capturedRequestBody.toString());
			clientAudit.setService(serviceName);
			clientAudit.setUrlStr(url);
			clientAudit.setHttpMethod(method);
			String domainName = null;
			try {
				domainName = LaserCommonUtils.getDomainName(url);
				clientAudit.setHostname(domainName);
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}*/
			//addClientAuditToRequestContext(clientAudit,requestAttributes);
		}).doOnError(error -> {
			//clientAudit.setErrormsg(error.getMessage());
		}).map(response -> response.mutate()
				.body(transformer -> transformer.doOnNext(body -> capturedResponseBody.append(extractBytes(body))) // number
						.doOnTerminate(() -> {
							if (stopWatch.isRunning()) {
								stopWatch.stop();
							}
						}).doOnComplete(() -> {
							/*clientAudit.setDuration(stopWatch.getTotalTimeMillis());
							clientAudit.setHttpStatus(response.statusCode().value());
							clientAudit.setResponse(capturedResponseBody.toString());*/

							//addClientAuditToRequestContext(clientAudit, requestAttributes);
						}).doOnError(error -> {
							/*clientAudit.setDuration(stopWatch.getTotalTimeMillis());
							clientAudit.setErrormsg(error.getMessage());*/
						}))
				.build());
	}

    private  String extractBytes(DataBuffer data) {
        int currentReadPosition = data.readPosition();
        var numberOfBytesLogged = min(data.readableByteCount(), MAX_BYTES_LOGGED);
        var bytes = new byte[numberOfBytesLogged];
        data.read(bytes, 0, numberOfBytesLogged);
        data.readPosition(currentReadPosition);
        return new String(bytes);
    }
    
	@SuppressWarnings("unchecked")
	/*private void addClientAuditToRequestContext(ClientAudit clientAudit, RequestAttributes requestAttributes) {
		List<ClientAudit> clientAuditList = null;
		if (requestAttributes instanceof ServletRequestAttributes) {
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
			if (null != httpServletRequest.getAttribute(Constants.CLIENT_AUDIT_KEY)) {
				clientAuditList = (List<ClientAudit>) httpServletRequest.getAttribute(Constants.CLIENT_AUDIT_KEY);
			} else {
				clientAuditList = new ArrayList<>();
			}
			if(clientAudit.getHttpStatus() != null){
				clientAuditList.add(clientAudit);
				httpServletRequest.setAttribute(Constants.CLIENT_AUDIT_KEY, clientAuditList);
			}
		}
	}*/

	public Map<String, String> queryToMap(String query) {
		Map<String, String> result = new HashMap<>();
		if (query != null) {
			for (String param : query.split("&")) {
				String[] entry = param.split("=");
				if (entry.length > 1) {
					result.put(entry[0], entry[1]);
				} else {
					result.put(entry[0], "");
				}
			}
		}
		return result;
	}
}
