package com.pethouse.aula;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pethouse.aula.domain.Categoria;
import com.pethouse.aula.domain.Cidade;
import com.pethouse.aula.domain.Cliente;
import com.pethouse.aula.domain.Endereco;
import com.pethouse.aula.domain.Estado;
import com.pethouse.aula.domain.ItemPedido;
import com.pethouse.aula.domain.Pagamento;
import com.pethouse.aula.domain.PagamentoComBoleto;
import com.pethouse.aula.domain.PagamentoComCartao;
import com.pethouse.aula.domain.Pedido;
import com.pethouse.aula.domain.Produto;
import com.pethouse.aula.domain.enums.EstadoPagamento;
import com.pethouse.aula.domain.enums.TipoCliente;
import com.pethouse.aula.repositories.CategoriaRepository;
import com.pethouse.aula.repositories.CidadeRepository;
import com.pethouse.aula.repositories.ClienteRepository;
import com.pethouse.aula.repositories.EnderecoRepository;
import com.pethouse.aula.repositories.EstadoRepository;
import com.pethouse.aula.repositories.ItemPedidoRepository;
import com.pethouse.aula.repositories.PagamentoRepository;
import com.pethouse.aula.repositories.PedidoRepository;
import com.pethouse.aula.repositories.ProdutoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired 
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired 
	private PedidoRepository pedidoRepository;
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Inform??tica");
		Categoria cat2 = new Categoria(null, "Escrit??rio");
		
		Produto p1 = new Produto(null, "Computador",2000.00);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Cear??");
		Estado est2 = new Estado (null, "S??o Paulo");
		
		Cidade c1 = new Cidade(null, "Fortaleza",est1);
		Cidade c2 = new Cidade(null, "S??o Paulo",est2);
		Cidade c3 = new Cidade(null, "Rio de Janeiro",est2);
		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Avenida Nova", "300", "Apto 203", "Jardin", "62900000", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Feliz", "105", "Sala800", "Centro", "60800000",cli1,c2);
		
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 10:32"), e1, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2020 19:35"), e2, cli1);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PEDIDO, ped2, sdf.parse("20/10/2020 00:00"),null);
		ped2.setPagamento(pagto2);
	
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 90.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0 , 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 12.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
		
			}

}
