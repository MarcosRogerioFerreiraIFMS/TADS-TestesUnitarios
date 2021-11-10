package br.edu.ifms.pedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemPedido> itens = new ArrayList<>();

    public void adicionaItem(ItemPedido item) {
        itens.add(item);        
    }
    
    public ResumoPedido resumo(){
        double valorTotal = itens.stream().mapToDouble(i -> i.getValor() * i.getQuantidade()).sum(); 
        double desconto = 0.0;
        if(valorTotal > 300 && valorTotal <= 800){
            desconto = valorTotal * 0.04;
        } else if(valorTotal > 800 && valorTotal <= 1000){
            desconto = valorTotal * 0.06;
        } else if(valorTotal > 1000){
            desconto = valorTotal * 0.08;
        }
        return new ResumoPedido(valorTotal, desconto);
    }
    
}
