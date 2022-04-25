package devsearch.projects.ws.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import devsearch.projects.ws.ui.model.request.ProjectImageRequest;
import devsearch.projects.ws.ui.model.response.ProjectImageResponse;

@FeignClient("images-ws")
public interface ImageClient {

    @PostMapping("/images/project")
    public ResponseEntity<ProjectImageResponse> addProjectImage(@RequestBody ProjectImageRequest imageRequest);
}
