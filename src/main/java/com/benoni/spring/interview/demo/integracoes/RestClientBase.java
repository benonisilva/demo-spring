package com.benoni.spring.interview.demo.integracoes;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.net.ssl.SSLException;

import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * Classe responsável por chamadas a serviços http. Faz uso de Reactive via
 * {@webClient} *
 * 
 * @param <S> Tipo de retorno do body de uma chamada http.
 */
public abstract class RestClientBase<S> {
    private WebClient webClient;

    @Inject
    private WebClient.Builder builderInjected;

    protected WebClient create(String endpoint) {
        HttpClient httpClient = null;
        try {
            SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();
            httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext))
                    .tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000 * 1000))
                    .tcpConfiguration(
                            client -> client.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(1000))
                                    .addHandlerLast(new WriteTimeoutHandler(1000))))
                    .compress(true);
            final ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
            webClient = builderInjected.clone().baseUrl(endpoint).clientConnector(connector).build();
        } catch (SSLException e) {
            e.printStackTrace();
        }

        return webClient;

    }

    public Mono<S> get(String url, Class<S> type) {
        return this.webClient.get().uri(url).retrieve().bodyToMono(type);
    }
}
