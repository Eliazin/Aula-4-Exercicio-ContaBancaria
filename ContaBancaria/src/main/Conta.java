package main;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Conta {

    public static void main(String[] args) {
        ArrayList<contaBancaria> listaConta = new ArrayList();

        int opc = 0;

        String banco = "", agencia = "", conta = "", tipo;
        double valorSaldo = 0, valorLimite = 0;

        contaBancaria contaSelecionada = null;

        while (opc != 6) {
            opc = Integer.parseInt(JOptionPane.showInputDialog(
                    "\n1 - Abrir nova conta"
                    + "\n2 - Sacar"
                    + "\n3 - Depositar"
                    + "\n4 - Extrato"
                    + "\n5 - Aumentar limite"
                    + "\n6 - Sair"));
            if (opc == 2 || opc == 3 || opc == 4 || opc == 5) {
                contaSelecionada = buscarConta(listaConta);
                
                if(contaSelecionada == null)
                    continue;
            }

            switch (opc) {
                case 1:
                    banco = JOptionPane.showInputDialog("Banco: ");
                    agencia = JOptionPane.showInputDialog("Agencia: ");
                    conta = JOptionPane.showInputDialog("Conta: ");
                    tipo = JOptionPane.showInputDialog("Tipo da conta: ");
                    valorSaldo = Double.parseDouble(JOptionPane.showInputDialog("Valor do Saldo: "));
                    valorLimite = Double.parseDouble(JOptionPane.showInputDialog("Valor do limite: "));

                    listaConta.add(new contaBancaria(banco, agencia, conta, tipo, valorSaldo, valorLimite));

                    break;

                case 2:

                    for (contaBancaria c : listaConta) {
                        if (conta.equals(c.getConta())) {

                            double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do saque:"));
                            JOptionPane.showMessageDialog(null, "Saque Realizado");
                            contaSelecionada.sacar(valor);

                        }
                    }

                    break;

                case 3:

                    for (contaBancaria c : listaConta) {
                        if (conta.equals(c.getConta())) {
                            double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do depósito:"));
                            contaSelecionada.depositar(valor);


                        }
                    }
                    break;
                case 4:

                    JOptionPane.showMessageDialog(null, contaSelecionada.verExtrato());
                    break;

                case 5:
                    for (contaBancaria c : listaConta) {
                        if (conta.equals(c.getConta())) {
                            valorLimite = Double.parseDouble(JOptionPane.showInputDialog("Valor do limite para atualizar: "));
                            contaSelecionada.setValorLimite(valorLimite);

                        }
                    }
                    break;
                case 6:
                    System.exit(0);
                    break;
            }

        }
    }

    private static contaBancaria buscarConta(ArrayList<contaBancaria> listaConta) {
        String msg = "Selecione o código da conta: ";
        for (contaBancaria c : listaConta) {
            msg += c.verExtrato() + "\n";
        }

        String cod = JOptionPane.showInputDialog(msg);
        for (contaBancaria c : listaConta) {
            if (c.getConta().equals(cod)) {
                return c;
            }
        }
        return null;
    }
}
