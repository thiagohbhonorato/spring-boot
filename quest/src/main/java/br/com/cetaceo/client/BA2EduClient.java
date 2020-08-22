package br.com.cetaceo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cetaceo.dto.BA2EduTesteDTO;

@FeignClient("ba2edu")
public interface BA2EduClient {

	@RequestMapping("/ba2edu/teste/{value}")
	BA2EduTesteDTO getBA2EduTeste(@PathVariable String value);
}
