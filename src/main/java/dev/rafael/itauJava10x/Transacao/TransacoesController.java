package dev.rafael.itauJava10x.Transacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacoesController {

	private final TransacaoService transacaoService;

	private final TransacaoRepository transacaoRepository;

	public TransacoesController(TransacaoService transacaoService, TransacaoRepository transacaoRepository) {
		this.transacaoService = transacaoService;
		this.transacaoRepository = transacaoRepository;
	}

	@PostMapping
	public ResponseEntity adicionar(@RequestBody TransacaoRequest transacaoRequest){

		try {
			transacaoService.validarTransacao(transacaoRequest);
			transacaoRepository.salvarDados(transacaoRequest);
			System.out.println(transacaoRequest.getDataHora());
			return ResponseEntity.status(HttpStatus.CREATED).body("Transação valida");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Regra de negócio inválida");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JSON inválido ou malformado");
		}

	}

	@DeleteMapping
	public ResponseEntity excluir(){
		transacaoService.excluirTransacao();
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
