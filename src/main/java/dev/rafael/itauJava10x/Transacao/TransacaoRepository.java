package dev.rafael.itauJava10x.Transacao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

	List<TransacaoRequest> transacaoLista = new ArrayList<>();

	public void salvarDados(TransacaoRequest transacaoRequest) {
		transacaoLista.add(transacaoRequest);
	}

	public List<TransacaoRequest> retornarLista() {
		return transacaoLista;
	}

	public void deletarDados(){
		transacaoLista.clear();
	}

}
