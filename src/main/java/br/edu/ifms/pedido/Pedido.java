package br.edu.ifms.pedido;

import br.edu.ifms.pedido.desconto.CalculadoraFaixaDesconto;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private CalculadoraFaixaDesconto calculadoraFaixaDesconto;
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
        this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
    }
    
    public void adicionaItem(ItemPedido item) {
        itens.add(item);        
    }
    
    public ResumoPedido resumo(){
        double valorTotal = itens.stream().mapToDouble(i -> i.getValor() * i.getQuantidade()).sum(); 
        
        double desconto = calculadoraFaixaDesconto.desconto(valorTotal);
        return new ResumoPedido(valorTotal, desconto);
    }
    
}
