package br.com.cetaceo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cetaceo.dto.BA2EduTesteDto;

@FeignClient("ba2edu")
public interface BA2EduClient {

	@RequestMapping("/myendpoint/{value}")
	BA2EduTesteDto getBA2EduPub(@PathVariable String value);
	
	@RequestMapping("/u/myendpoint/{value}")
	BA2EduTesteDto getBA2EduAut(@PathVariable String value);
}
