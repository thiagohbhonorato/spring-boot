package br.com.cetaceo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cetaceo.dto.BA2EduTesteDTO;

@FeignClient("ba2edu")
public interface BA2EduClient {

	@RequestMapping("/ba2edu/myendpoint/{value}")
	BA2EduTesteDTO getBA2EduPub(@PathVariable String value);
	
	@RequestMapping("/ba2edu/a/myendpoint/{value}")
	BA2EduTesteDTO getBA2EduAut(@PathVariable String value);
}
