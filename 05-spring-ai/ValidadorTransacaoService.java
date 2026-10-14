package br.com.dio.springai.service;

import java.math.BigDecimal;

/**
 * Melhoria implementada para o Desafio DIO:
 * Validação de regras de negócio antes de persistir transações acionadas via Tool Calling (Spring AI)
 * e cálculo de alerta de limite de orçamento.
 */
public class ValidadorTransacaoService {

    private static final BigDecimal LIMITE_ALERTA_GASTO = new BigDecimal("1000.00");

    // 1. Valida se os dados extraídos pela IA a partir do áudio são válidos antes de salvar
    public void validarNovaTransacao(String descricao, BigDecimal valor) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição da transação não foi identificada no comando de voz.");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero.");
        }
    }

    // 2. Nova consulta para enriquecer a resposta em áudio da IA para o usuário
    public String verificarAlertaOrcamento(BigDecimal valorTransacao) {
        if (valorTransacao.compareTo(LIMITE_ALERTA_GASTO) > 0) {
            return "Atenção: Esta transação ultrapassa o limite de alerta de R$ 1.000,00 do seu orçamento.";
        }
        return "Transação dentro do limite planejado do orçamento.";
    }
}
