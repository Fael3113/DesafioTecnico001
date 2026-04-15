package dev.rafael.itauJava10x.Transacao;

import dev.rafael.itauJava10x.Docs.TransacaoControllerDoc;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transacao")
public class TransacoesController implements TransacaoControllerDoc {

	private final TransacaoService transacaoService;

	private final TransacaoRepository transacaoRepository;

	public TransacoesController(TransacaoService transacaoService, TransacaoRepository transacaoRepository) {
		this.transacaoService = transacaoService;
		this.transacaoRepository = transacaoRepository;
	}

	@PostMapping
	public ResponseEntity adicionar(@RequestBody @Valid TransacaoRequest transacaoRequest){

		try {
			transacaoService.validarTransacao(transacaoRequest);
			transacaoRepository.salvarDados(transacaoRequest);
			log.info("Transação com valores corretos e sem nenhum problema no Json");
			return ResponseEntity.status(HttpStatus.CREATED).body("Transação valida");
		} catch (IllegalArgumentException e) {
			log.error("Erro: Valor negativo ou data futura");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Regra de negócio inválida");
		} catch (Exception e) {
			log.error("Erro: Campo de variável do Json está com algum erro");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JSON inválido ou malformado");
		}

	}

	@DeleteMapping
	public ResponseEntity excluir(){
		transacaoService.excluirTransacao();
		log.info("Transações foram excluídas");
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
