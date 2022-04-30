package devsearch.projects.ws.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import devsearch.projects.ws.ui.model.response.CommentResponse;
import feign.Headers;

@FeignClient("developers-ws")
public interface DeveloperClient {

    @GetMapping("/developers/comments")
    @Headers("Content-Type: application/json")
    public List<CommentResponse> getCommentsForProject(@RequestParam(value = "projectId") String projectId);
}
