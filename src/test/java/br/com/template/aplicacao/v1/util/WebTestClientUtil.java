package br.com.template.aplicacao.v1.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@UtilityClass
public class WebTestClientUtil {

    public <T> EntityExchangeResult<T> buildGetWebClient(WebTestClient webTestClient, String uri, Class<T> responseClass) {
        return webTestClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(responseClass)
                .returnResult();
    }

    public EntityExchangeResult<Void> buildGetWebClient(WebTestClient webTestClient, String uri) {
        return webTestClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody()
                .isEmpty();
    }

    public static <T> EntityExchangeResult<T> buildGetWebClient(WebTestClient webTestClient, String uri,
                                                                ParameterizedTypeReference<T> responseType) {
        return webTestClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(responseType)
                .returnResult();
    }

    public <T> EntityExchangeResult<T> buildPostWebClient(WebTestClient webTestClient, String uri, Object requestData, Class<T> responseClass) {
        return webTestClient.post()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(requestData)
                .exchange()
                .expectBody(responseClass)
                .returnResult();
    }

    public <T> EntityExchangeResult<T> buildPostWebClient(WebTestClient webTestClient, String uri, Object requestData, Class<T> responseClass, MediaType mediaType) {
        return webTestClient.post()
                .uri(uri)
                .accept(mediaType)
                .bodyValue(requestData)
                .exchange()
                .expectBody(responseClass)
                .returnResult();
    }

    public <T> EntityExchangeResult<T> buildPutWebClient(WebTestClient webTestClient, String uri, Object requestData, Class<T> responseClass) {
        return webTestClient.put()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(requestData)
                .exchange()
                .expectBody(responseClass)
                .returnResult();
    }

    public EntityExchangeResult<Void> buildPatchWebClient(WebTestClient webTestClient, String uri) {
        return webTestClient.patch()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody()
                .isEmpty();
    }

    public <T> EntityExchangeResult<T> buildPatchWebClient(WebTestClient webTestClient, String uri, Class<T> responseClass) {
        return webTestClient.patch()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(responseClass)
                .returnResult();
    }

    public EntityExchangeResult<Void> buildDeleteWebClient(WebTestClient webTestClient, String uri) {
        return webTestClient.delete()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody()
                .isEmpty();
    }

    public <T> EntityExchangeResult<T> buildDeleteWebClient(WebTestClient webTestClient, String uri, Class<T> responseClass) {
        return webTestClient.delete()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(responseClass)
                .returnResult();
    }

    public static <T> EntityExchangeResult<List<T>> buildGetWebClientWithResponseList(WebTestClient webTestClient, String uri, Class<T> responseClass) {
        return webTestClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBodyList(responseClass)
                .returnResult();
    }
}
