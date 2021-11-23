import br.edu.ifms.pedido.ItemPedido;
import br.edu.ifms.pedido.Pedido;
import br.edu.ifms.pedido.ResumoPedido;
import br.edu.ifms.pedido.desconto.CalculadorDescontoPrimeiraFaixa;
import br.edu.ifms.pedido.desconto.CalculadoraDescontoSegundaFaixa;
import br.edu.ifms.pedido.desconto.CalculadoraDescontoTerceiraFaixa;
import br.edu.ifms.pedido.desconto.CalculadoraFaixaDesconto;
import br.edu.ifms.pedido.desconto.SemDesconto;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PedidoTest {
    
    private Pedido pedido;
    
    @Before
    public void setup(){
        CalculadoraFaixaDesconto calculadoraFaixaDesconto =
                new CalculadoraDescontoTerceiraFaixa(
                    new CalculadoraDescontoSegundaFaixa(
                        new CalculadorDescontoPrimeiraFaixa(
                            new SemDesconto(null))));
        pedido = new Pedido(calculadoraFaixaDesconto);
    }
    
    @Test
    public void testAdicionaUmItem(){
        pedido.adicionaItem(new ItemPedido("Mouse", 145.0, 10));
    }
    
    @Test
    public void testCalcularValorTotalPedidoVazio(){
        assertResumoPedido(0.0, 0.0);
    }
    
    @Test
    public void testCalcularValorTotalComDescontoPedidoVazio(){
        assertResumoPedido(0.0, 0.0);
    }
    
    @Test
    public void testCalculaResumoUmItemSemDesconto(){
        pedido.adicionaItem(new ItemPedido("Teclado", 35.0, 2));
        assertResumoPedido(70.0, 0.0);
    }
    
    @Test
    public void testCalculaResumoDoisItensSemDesconto(){
        pedido.adicionaItem(new ItemPedido("Teclado", 35.0, 2));
        pedido.adicionaItem(new ItemPedido("Mouse Pad", 25.0, 2));
        assertResumoPedido(120.0, 0.0);
    }
    
    @Test
    public void testAplicarDescontoPrimeiraFaixa(){
        //4% para pedidos acima de R$300,00
        pedido.adicionaItem(new ItemPedido("Fonte para notebook", 200.0, 2));
        assertResumoPedido(400.0, 16.0);
    }
    
    @Test
    public void testAplicarDescontoSegundaFaixa(){
        //6% para pedidos acima de R$800,00
        pedido.adicionaItem(new ItemPedido("Fonte para notebook", 200.0, 2));
        pedido.adicionaItem(new ItemPedido("WebCam", 250.0, 2));
        assertResumoPedido(900.0, 54.0);
    }
    
    @Test
    public void testAplicarDescontoTerceiraFaixa(){
        //8% para pedidos acima de R$1000,00
        pedido.adicionaItem(new ItemPedido("Fonte para notebook", 200.0, 2));
        pedido.adicionaItem(new ItemPedido("WebCam", 250.0, 2));
        pedido.adicionaItem(new ItemPedido("Luminária", 150.0, 2));
        assertResumoPedido(1200.0, 96.0);
    }
    
    private void assertResumoPedido(double valorTotal, double desconto){
        ResumoPedido resumo = pedido.resumo();
        assertEquals(valorTotal, resumo.getValorTotal(), 0.0001);
        assertEquals(desconto, resumo.getDesconto(), 0.0001);
    }
}
