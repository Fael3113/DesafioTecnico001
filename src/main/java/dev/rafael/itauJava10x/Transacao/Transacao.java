package dev.rafael.itauJava10x.Transacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {

	private BigDecimal valor;
	private OffsetDateTime dataHora;

	public void setDataHora(OffsetDateTime dataHora) {
		if (dataHora != null) {
			this.dataHora = dataHora.withOffsetSameInstant(ZoneOffset.UTC);
		}
	}

}
