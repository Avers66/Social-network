package avers66.microservice.post.resource.client;

import avers66.microservice.post.dto.statistic.StatisticResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
;
import avers66.microservice.post.dto.statistic.PostStatisticRequestDto;

/**
 * PostFriendClient
 *
 * @author Vladimir Polochanin
 */

@FeignClient(value = "StatisticFeignClient",
//        url = "http://localhost:8080",
        url = "http://microservice-post",
        path = "/api/v1/post/statistic")
public interface StatisticFeignClient {

    @GetMapping("/post")
    ResponseEntity<StatisticResponseDto> getPostStatistic(@SpringQueryMap PostStatisticRequestDto requestDto);

    @GetMapping("/comment")
    ResponseEntity<StatisticResponseDto> getCommentStatistic(@SpringQueryMap PostStatisticRequestDto requestDto);
    @GetMapping("/like")
    ResponseEntity<StatisticResponseDto> getLikeStatistic(@SpringQueryMap PostStatisticRequestDto requestDto);
}

