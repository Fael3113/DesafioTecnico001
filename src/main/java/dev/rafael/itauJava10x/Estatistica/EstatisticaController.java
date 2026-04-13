package dev.rafael.itauJava10x.Estatistica;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

	private final EstatisticaService estatisticaService;

	public EstatisticaController(EstatisticaService estatisticaService) {
		this.estatisticaService = estatisticaService;
	}

	@GetMapping
	public ResponseEntity retornarTransacoes() {
		log.info("Retorno das estatisticas das transações dos últimos 60 segundos");
		return ResponseEntity.ok(estatisticaService.validarEstatistica());
	}

}
