package com.belibeerda.retrofit;

import com.belibeerda.retrofit.request.PostCreateRequest;
import com.belibeerda.retrofit.request.PostUpdateRequest;
import com.belibeerda.retrofit.response.PostResponse;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");

        JsonPlaceholderApi api = JsonPlaceholderService.getInstance().api();

        System.out.println("GET: /posts -------------------------");
        Response<List<PostResponse>> postResponse = api.posts(null).execute();
        System.out.println(postResponse.isSuccessful());
        System.out.println(postResponse.code());
        System.out.println(postResponse.headers());
        List<PostResponse> posts = postResponse.body();
        System.out.println(posts);

        System.out.println("POST: /posts -------------------------");
        PostCreateRequest createRequest = new PostCreateRequest();
        createRequest.setBody("Body");
        createRequest.setTitle("title");
        createRequest.setUserId(1);
        Response<PostResponse> createResponse = api.create(createRequest).execute();
        System.out.println(createResponse.body());

        System.out.println("PUT: /posts -------------------------");
        PostUpdateRequest updateRequest = new PostUpdateRequest();
        updateRequest.setBody("new Body");
        updateRequest.setTitle("new title");
        updateRequest.setUserId(1);
        updateRequest.setId(1);
        Response<PostResponse> updateResponse = api.update(1, updateRequest).execute();
        System.out.println(updateResponse.body());

        System.out.println("DELETE: /posts -------------------------");
        Integer statusCode = api.delete(1).execute().code();
        System.out.println(statusCode);
    }
}
