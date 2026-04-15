package dev.rafael.itauJava10x.Docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
	name = "Estatisticas",
	description = "Endpoint responsável por retornar as estatisticas das transações de um determinado período")
public interface EstatisticaControllerDoc {

	@Operation(
		summary = "Retorna estatisticas",
		description = "Recebe a lista de transações e filtra para retornar apenas as estatisticas das mais recentes"
	)
	@ApiResponse(
		responseCode = "200",
		description = "Retorno das estatisticas das últimas transações, caso não houver nenhuma transação de determinado período" +
				" o retorno é zero"
	)
	ResponseEntity<Void> retornarTransacoes();
}
