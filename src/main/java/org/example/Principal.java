package org.example;

import org.example.model.Funcionario;
import org.example.service.FuncionarioService;
import org.example.util.FormatadorUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = FuncionarioService.carregarFuncionarios();


        System.out.println("\n--- Funcionários ---");
        funcionarios.forEach(f -> System.out.println(
                f.getNome() + " | " +
                        FormatadorUtil.formatarData(f.getDataNascimento()) + " | " +
                        FormatadorUtil.formatarSalario(f.getSalario()) + " | " +
                        f.getFuncao()
        ));

        FuncionarioService.removerFuncionario(funcionarios, "João");
        System.out.println("\nFuncionário João removido");

        System.out.println("\n--- Funcionários ---");
        funcionarios.forEach(f -> System.out.println(
                f.getNome() + " | " +
                        FormatadorUtil.formatarData(f.getDataNascimento()) + " | " +
                        FormatadorUtil.formatarSalario(f.getSalario()) + " | " +
                        f.getFuncao()
        ));

        FuncionarioService.aplicarAumento(funcionarios, new BigDecimal("1.10"));

        System.out.println("\n--- Salários após aumento ---");
        funcionarios.forEach(f -> System.out.println(
                f.getNome() + " - Salário após aumento: " + FormatadorUtil.formatarSalario(f.getSalario())
        ));

        Map<String, List<Funcionario>> agrupados =
                FuncionarioService.agruparPorFuncao(funcionarios);

        System.out.println("\n--- Agrupados ---");
        agrupados.forEach((funcao, lista) -> {
            System.out.println("** " + funcao + " **\n");
            lista.forEach(f -> System.out.println(f.getNome()));
            System.out.println();
        });

        System.out.println("\n--- Aniversariantes ---");
        FuncionarioService.filtrarPorMes(funcionarios, 10, 12)
                .forEach(f -> System.out.println(f.getNome()));

        Funcionario maisVelho = FuncionarioService.buscarMaisVelho(funcionarios);
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.println("\nMais velho: " + maisVelho.getNome() + " - " + idade + " anos.");

        System.out.println("\n--- Ordem alfabética ---");
        FuncionarioService.ordenarPorNome(funcionarios)
                .forEach(f -> System.out.println(f.getNome()));

        System.out.println("\nTotal salários: " +
                FormatadorUtil
                        .formatarSalario(FuncionarioService
                                .somarSalarios(funcionarios)
                                .setScale(2, RoundingMode.HALF_UP)));

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\n--- Salários mínimos ---");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + qtd);
        });
    }
}
