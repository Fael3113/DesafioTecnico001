package dev.rafael.itauJava10x.Estatistica;

import java.math.BigDecimal;

public record EstatisticaDTO (

	Long count,
	BigDecimal sum,
	BigDecimal avg,
	BigDecimal min,
	BigDecimal max){

}
