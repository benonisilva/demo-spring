package com.benoni.spring.interview.demo.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.benoni.spring.interview.demo.api.model.Cidade;
import com.benoni.spring.interview.demo.integracoes.estados.IIntegracoesEstadoService;
import com.benoni.spring.interview.demo.integracoes.estados.model.ResponseEstado;
import com.benoni.spring.interview.demo.services.ICidadeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController()
public class CidadesController {

	@Inject
	private ICidadeService cidadesService;

	@Inject
	private IIntegracoesEstadoService integracoes;

	@ApiOperation(value = "Lista de cidades por nome e ou estadoId", nickname = "cidadesGet", notes = "Cidades cadastradas por nome e ou estadoId", response = Cidade.class, responseContainer = "List", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "cidades por nome ou estado id", response = Cidade.class, responseContainer = "List") })
	@RequestMapping(value = "/cidades", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> getCidades(
			@ApiParam(value = "nome da cidade") @Valid @RequestParam(value = "nome", required = false) String nome,
			@ApiParam(value = "estadoId") @Valid @RequestParam(value = "estadoId", required = false) Integer estadoId) {

		List<Cidade> cidades = new ArrayList<>();
		cidades = cidadesService.consultarCidadeByNomeAndEstadoId(nome, estadoId);
		return new ResponseEntity<>(cidades, HttpStatus.OK);
	}

	@RequestMapping(value = "/cidades", method = RequestMethod.POST)
	ResponseEntity<Cidade> insertCidade(@RequestBody @Valid Cidade body) {
		Cidade cidadeSalva = cidadesService.cadastrarCidade(body);
		return new ResponseEntity<>(cidadeSalva, HttpStatus.OK);
	}

	// somente para demostrar testes via wiremock
	@RequestMapping(value = "/estados/{id}", method = RequestMethod.GET)
	ResponseEntity<ResponseEstado> estadosIntegracoes(@PathVariable("id") Integer id) {
		ResponseEstado estados = integracoes.getEstadoById(id);
		return new ResponseEntity<>(estados, HttpStatus.OK);
	}

}
