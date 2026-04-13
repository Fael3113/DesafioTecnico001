package dev.rafael.itauJava10x.Transacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public record TransacaoRequest (

	@NotNull (message = "Valor da transação não pode ser nulo")
	@PositiveOrZero (message = "Valor da transação deve ser maior ou igual a zero")
	BigDecimal valor,

	@NotNull (message = "Data não pode ser nula")
	OffsetDateTime dataHora) {
	public TransacaoRequest {
		if (dataHora != null) {
			dataHora = dataHora.withOffsetSameInstant(ZoneOffset.UTC);
		}
	}

}
