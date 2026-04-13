package dev.rafael.itauJava10x.Estatistica;

import dev.rafael.itauJava10x.Transacao.TransacaoRepository;
import dev.rafael.itauJava10x.Transacao.TransacaoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Slf4j
@Service
public class EstatisticaService {

	private final TransacaoRepository transacaoRepository;
	private final EstatisticaProperties estatisticaProperties;

	public EstatisticaService(TransacaoRepository transacaoRepository, EstatisticaProperties estatisticaProperties) {
		this.transacaoRepository = transacaoRepository;
		this.estatisticaProperties = estatisticaProperties;
	}

	public EstatisticaDTO validarEstatistica(){

		OffsetDateTime intervalo = OffsetDateTime.now(ZoneOffset.UTC).minusSeconds(estatisticaProperties.segundos());

		log.info("Filtragem da lista: ");
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
//					.orElse(BigDecimal.ZERO);
//
//			BigDecimal menorValor = transacaoUltimoMinuto.stream()
//					.map(TransacaoRequest::getValor)
//					.min(BigDecimal::compareTo)
//					.orElse(BigDecimal.ZERO);
//
//			BigDecimal soma = transacaoUltimoMinuto.stream()
//					.map(TransacaoRequest::getValor)
//					.reduce(BigDecimal.ZERO, BigDecimal::add);
//
//			BigDecimal media = soma.divide(BigDecimal.valueOf(count), 4,
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

			log.info("Criação das estatísticas: ");
			DoubleSummaryStatistics statistics = transacaoUltimoMinuto.stream()
					.mapToDouble(t -> t.getValor().doubleValue())
					.summaryStatistics();

			return new EstatisticaDTO(
					statistics.getCount(),
					BigDecimal.valueOf(statistics.getSum()).setScale(4, RoundingMode.HALF_UP),
					BigDecimal.valueOf(statistics.getAverage()).setScale(4, RoundingMode.HALF_UP),
					BigDecimal.valueOf(statistics.getMin()).setScale(4, RoundingMode.HALF_UP),
					BigDecimal.valueOf(statistics.getMax()).setScale(4, RoundingMode.HALF_UP)
			);
		}

		return new EstatisticaDTO(0L, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

	}
}
