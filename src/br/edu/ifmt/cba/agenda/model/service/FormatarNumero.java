package br.edu.ifmt.cba.agenda.model.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatarNumero {
	public static double format(double arg) {
		/*
		 * cria uma instancia da classe BigDecimal para formatar o double recebido no argumento
		 * setScale() seta a formatação para 4 casas após a virgula usando como prioridade
		 * arredondamento para cima de um numero maior que 5 e arredondamento para baixo caso contrário*/
		BigDecimal bd = new BigDecimal(String.valueOf(arg));
		/*
		 * setScale retorna uma String formatada, para converter para Double utiliza-se .doubleValue() */	
		return bd.setScale(3, RoundingMode.HALF_EVEN).doubleValue();
	}
}
