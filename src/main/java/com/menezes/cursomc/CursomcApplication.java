package com.menezes.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.menezes.cursomc.domain.Categoria;
import com.menezes.cursomc.domain.Cidade;
import com.menezes.cursomc.domain.Cliente;
import com.menezes.cursomc.domain.Endereco;
import com.menezes.cursomc.domain.Estado;
import com.menezes.cursomc.domain.ItemPedido;
import com.menezes.cursomc.domain.Pagamento;
import com.menezes.cursomc.domain.PagamentoComBoleto;
import com.menezes.cursomc.domain.PagamentoComCartao;
import com.menezes.cursomc.domain.Pedido;
import com.menezes.cursomc.domain.Produto;
import com.menezes.cursomc.domain.enums.EstadoPagamento;
import com.menezes.cursomc.domain.enums.TipoCliente;
import com.menezes.cursomc.repositories.CategoriaRepository;
import com.menezes.cursomc.repositories.CidadeRepository;
import com.menezes.cursomc.repositories.ClienteRepository;
import com.menezes.cursomc.repositories.EnderecoRepository;
import com.menezes.cursomc.repositories.EstadoRepository;
import com.menezes.cursomc.repositories.ItemPedidoRepository;
import com.menezes.cursomc.repositories.PagamentoRepository;
import com.menezes.cursomc.repositories.PedidoRepository;
import com.menezes.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeReporitory;
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
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria("Informática", null);
		Categoria cat2 = new Categoria("Escritório", null);
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll( Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeReporitory.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838388"));
	
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 303", "Jardim","38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro","38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	
		SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido pedi1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido pedi2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
	
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedi1, 6);
		pedi1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedi2, sdf.parse("20/10/2017 00:00"), null);
		pedi2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(pedi1, pedi2));
	
		pedidoRepository.saveAll(Arrays.asList(pedi1, pedi2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(pedi1, p1, 0.00, 1, 2000.00);		
		ItemPedido ip2 = new ItemPedido(pedi1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(pedi2, p2, 100.00, 1, 800.00);
		
		pedi1.getItens().addAll(Arrays.asList(ip1, ip2));
		pedi2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
		
	}
	
}
