package com.microfocus.oo.poc.websocketstreaming;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microfocus.oo.poc.websocketstreaming.model.Workflow;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;
import java.util.HashSet;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootApplication
@EnableJpaAuditing

public class WebsocketStreamingApplication {
    public static final String URI = "http://localhost:2828";

    public static void main(String[] args) {
        SpringApplication.run(WebsocketStreamingApplication.class, args);
    }

    private static void createGetRequestsWebflux() {
        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet(URI + "/workflow-webflux");
        getRequest.addHeader(ACCEPT, APPLICATION_JSON_VALUE);

        try {
            for (int i = 0; i < 100; i++) {
                HttpResponse response = client.execute(getRequest);
                System.out.println("Response Payload is " + response.getStatusLine().getStatusCode());
                response.getEntity().getContent().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createGetRequestsPagination() {
        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet(URI + "/workflow-pagination");
        getRequest.addHeader(ACCEPT, APPLICATION_JSON_VALUE);

        try {
            for (int i = 0; i < 100; i++) {
                HttpResponse response = client.execute(getRequest);
                System.out.println("Response Payload is " + response.getStatusLine().getStatusCode());
                response.getEntity().getContent().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createPostWorkflowRequests() {
        DefaultHttpClient client = new DefaultHttpClient();

        HttpPost httpPostWorkflow = new HttpPost("http://localhost:2828/workflow");
        try {
            for (int i = 986; i < 1033; i++) {


                Workflow workflow = new Workflow("flowPath " + i,
                        "name " + i,
                        "description " + i,
                        "persistenceLevel " + i,
                        Boolean.FALSE,
                        20,
                        new HashSet<>());

                HttpEntity entity = new StringEntity(objectToJson(workflow));
                httpPostWorkflow.setEntity(entity);
                httpPostWorkflow.addHeader(ACCEPT, APPLICATION_JSON_VALUE);
                httpPostWorkflow.addHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE);

                HttpResponse response = client.execute(httpPostWorkflow);
                response.getEntity().getContent().close();
            }


        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static String objectToJson(Object obj) {
        Gson gson = (new GsonBuilder()).serializeNulls().create();
        return gson.toJson(obj);
    }

}
