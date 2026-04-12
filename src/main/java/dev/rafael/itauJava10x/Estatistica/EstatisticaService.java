package dev.rafael.itauJava10x.Estatistica;

import dev.rafael.itauJava10x.Transacao.TransacaoRepository;
import dev.rafael.itauJava10x.Transacao.TransacaoRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticaService {

	private final TransacaoRepository transacaoRepository;

	public EstatisticaService(TransacaoRepository transacaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}

	public EstatisticaDTO validarEstatistica(){

		OffsetDateTime intervalo = OffsetDateTime.now(ZoneOffset.UTC).minusMinutes(1);

		List<TransacaoRequest> transacaoUltimoMinuto = transacaoRepository.retornarLista()
				.stream()
				.filter(t -> t.getDataHora().isAfter(intervalo))
				.toList();

		if (!transacaoUltimoMinuto.isEmpty()) {
//			long count = transacaoUltimoMinuto.size();
//
//			BigDecimal maiorValor = transacaoUltimoMinuto.stream()
//					.map(TransacaoRequest::getValor)
//					.max(BigDecimal::compareTo)
//					.get();
//
//			BigDecimal menorValor = transacaoUltimoMinuto.stream()
//					.map(TransacaoRequest::getValor)
//					.min(BigDecimal::compareTo)
//					.get();
//
//			BigDecimal soma = transacaoUltimoMinuto.stream()
//					.map(TransacaoRequest::getValor)
//					.reduce(BigDecimal.ZERO, BigDecimal::add);
//
//			BigDecimal media = soma.divide(BigDecimal.valueOf(count), 2,
//					RoundingMode.HALF_UP);
//
//			EstatisticaDTO estatisticaDTO = new EstatisticaDTO();
//			estatisticaDTO.setCount(count);
//			estatisticaDTO.setMax(maiorValor);
//			estatisticaDTO.setMin(menorValor);
//			estatisticaDTO.setSum(soma);
//			estatisticaDTO.setAvg(media);
//
//			return  ResponseEntity.status(HttpStatus.OK).body(estatisticaDTO);

			DoubleSummaryStatistics statistics = transacaoUltimoMinuto.stream()
					.mapToDouble(t -> t.getValor().doubleValue())
					.summaryStatistics();

			return new EstatisticaDTO(
					statistics.getCount(),
					BigDecimal.valueOf(statistics.getSum()).setScale(2, RoundingMode.HALF_UP),
					BigDecimal.valueOf(statistics.getAverage()).setScale(2, RoundingMode.HALF_UP),
					BigDecimal.valueOf(statistics.getMin()).setScale(2, RoundingMode.HALF_UP),
					BigDecimal.valueOf(statistics.getMax()).setScale(2, RoundingMode.HALF_UP)
			);
		}

		return new EstatisticaDTO(0L, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

	}
}
