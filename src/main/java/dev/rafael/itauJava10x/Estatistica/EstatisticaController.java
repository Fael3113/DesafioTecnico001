package dev.rafael.itauJava10x.Estatistica;

import dev.rafael.itauJava10x.Transacao.TransacaoRepository;
import dev.rafael.itauJava10x.Transacao.TransacaoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

	private TransacaoRepository transacaoRepository;

	public EstatisticaController(TransacaoRepository transacaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}

	@GetMapping
	public ResponseEntity retornarTransacoes() {
		OffsetDateTime intervalo = OffsetDateTime.now().minusMinutes(1);

		List<TransacaoRequest> transacaoUltimoMinuto = transacaoRepository.retornarLista()
				.stream()
				.filter(t -> t.getDataHora().isAfter(intervalo))
				.toList();

		if (!transacaoUltimoMinuto.isEmpty()) {
			long count = transacaoUltimoMinuto.size();

			BigDecimal maiorValor = transacaoUltimoMinuto.stream()
					.map(TransacaoRequest::getValor)
					.max(BigDecimal::compareTo)
					.get();

			BigDecimal menorValor = transacaoUltimoMinuto.stream()
					.map(TransacaoRequest::getValor)
					.min(BigDecimal::compareTo)
					.get();

			BigDecimal soma = transacaoUltimoMinuto.stream()
					.map(TransacaoRequest::getValor)
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			BigDecimal media = soma.divide(BigDecimal.valueOf(count), 2,
					RoundingMode.HALF_UP);

			EstatisticaDTO estatisticaDTO = new EstatisticaDTO();
			estatisticaDTO.setCount(count);
			estatisticaDTO.setMax(maiorValor);
			estatisticaDTO.setMin(menorValor);
			estatisticaDTO.setSum(soma);
			estatisticaDTO.setAvg(media);

			return ResponseEntity.ok(estatisticaDTO);
		}

		return ResponseEntity.ok(new EstatisticaDTO(0L, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

	}

}
