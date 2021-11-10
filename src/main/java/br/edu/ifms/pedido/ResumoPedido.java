package br.edu.ifms.pedido;

public class ResumoPedido {
    private double valorTotal;
    private double desconto;

    public ResumoPedido(){ }
    
    public ResumoPedido(double valorTotal, double desconto) {
        this.valorTotal = valorTotal;
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
    
    
    
    
}
