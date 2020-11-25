package com.benoni.spring.interview.demo.api;

import java.util.ArrayList;
import java.util.List;

import com.benoni.spring.interview.demo.api.model.Cidade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/v1")
public class CidadesController {

	@RequestMapping(value = "/cidades", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> getCidades() {
		List<Cidade> cidades = new ArrayList<>();
		return new ResponseEntity<>(cidades, HttpStatus.OK);
	}
}
