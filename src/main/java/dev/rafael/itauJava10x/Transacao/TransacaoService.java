package dev.rafael.itauJava10x.Transacao;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

	public void validarTransacao(TransacaoRequest transacaoRequest){

		if (transacaoRequest.getValor() == null || transacaoRequest.getDataHora() == null) {
			throw new IllegalArgumentException("Campos obrigatórios ausentes.");
		}

		if (transacaoRequest.getValor().compareTo(BigDecimal.ZERO) < 0 ){
			throw new IllegalArgumentException("Erro: Isso não é uma transação válida. Apenas transações com valores positivos são válidos.");
		}

		if (transacaoRequest.getDataHora().isAfter(OffsetDateTime.now())){
			throw new IllegalArgumentException("Erro: Isso não é uma transação válida. Apenas transações anteriores ao presente são válidas.");
		}

	}
}
