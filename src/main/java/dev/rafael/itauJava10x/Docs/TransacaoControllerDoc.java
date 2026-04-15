package dev.rafael.itauJava10x.Docs;

import dev.rafael.itauJava10x.Transacao.TransacaoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
	name = "Transações",
	description = "Endpoints responsáveis por criar, adicionar e excluir as transações em uma lista")
public interface TransacaoControllerDoc {

	@Operation(
		summary = "Cria transação",
		description = "Recebe uma transação válida e adiciona em uma lista")
	@ApiResponse(
		responseCode = "201",
		description = "Transação criada com sucesso")
	@ApiResponse(
		responseCode = "422",
		description = "Erro de validação capturado")
	@ApiResponse(
		responseCode = "400",
		description = "Erro na comunicação com servidor")

	ResponseEntity<Void> adicionar (@RequestBody TransacaoRequest transacaoRequest);

	@Operation(
			summary = "Deleta transações",
			description = "Remove todas as transações presentes na lista")
	@ApiResponse(
			responseCode = "200",
			description = "Transação deletada com sucesso")

	ResponseEntity<Void> excluir ();
}
