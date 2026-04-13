package dev.rafael.itauJava10x.Transacao;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

	private TransacaoRepository transacaoRepository;

	public TransacaoService(TransacaoRepository transacaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}

	public void validarTransacao(TransacaoRequest transacaoRequest){

		if (transacaoRequest.valor() == null || transacaoRequest.dataHora() == null) {
			throw new IllegalArgumentException("Campos obrigatórios ausentes.");
		}

		if (transacaoRequest.valor().compareTo(BigDecimal.ZERO) < 0 ){
			throw new IllegalArgumentException("Erro: Isso não é uma transação válida. Apenas transações com valores positivos são válidos.");
		}

		if (transacaoRequest.dataHora().isAfter(OffsetDateTime.now())){
			throw new IllegalArgumentException("Erro: Isso não é uma transação válida. Apenas transações anteriores ao presente são válidas.");
		}

	}

	public void excluirTransacao(){
		transacaoRepository.deletarDados();
	}
}
