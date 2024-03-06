package com.example.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "theatre-service",url = "http://localhost:8083/api/v1/theatre")
public interface TheatreFeign {


}
