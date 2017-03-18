package br.com.secureedges.core.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.zu.ardulink.Link;
import org.zu.ardulink.gui.DigitalPinStatus;
import org.zu.ardulink.protocol.IProtocol;

import br.com.secureedges.core.impl.controle.Fachada;
import br.com.secureedges.util.FacesUtil;
import br.com.secureedges.core.web.command.ICommand;
import br.com.secureedges.core.web.impl.AlterarCommand;
import br.com.secureedges.core.web.impl.ExcluirCommand;
import br.com.secureedges.core.web.impl.SalvarCommand;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Comodo;
import br.com.secureedges.domain.Tipo_Dispositivo;
import br.com.secureedges.domain.Dispositivo;

@ManagedBean
@ViewScoped
public class DispositivoBean extends EntidadeDominio {
	private Dispositivo DispositivoCadastro;
	List<EntidadeDominio> listaDispositivos;
	List<Dispositivo> listaDispositivosFiltrados;
	List<EntidadeDominio> listaComodos;
	List<EntidadeDominio> listaTipo_Dispositivos;
	Fachada Fachada = new Fachada();
	private String acao;
	private Long codigo;
	private static Map<String, ICommand> commands;

	public DispositivoBean() {
		/*
		 * Utilizando o command para chamar a fachada e indexando cada command
		 * pela opera��o garantimos que esta servelt atender� qualquer opera��o
		 */
		commands = new HashMap<String, ICommand>();
		commands.put("Salvar", new SalvarCommand());
		commands.put("Excluir", new ExcluirCommand());
		commands.put("Editar", new AlterarCommand());
	}

	public List<EntidadeDominio> getListaTipo_Dispositivos() {
		return listaTipo_Dispositivos;
	}

	public void setListaTipo_Dispositivos(List<EntidadeDominio> listaTipo_Dispositivos) {
		this.listaTipo_Dispositivos = listaTipo_Dispositivos;
	}

	public static Map<String, ICommand> getCommands() {
		return commands;
	}

	public static void setCommands(Map<String, ICommand> commands) {
		DispositivoBean.commands = commands;
	}

	public List<EntidadeDominio> getListaComodos() {
		return listaComodos;
	}

	public void setListaComodos(List<EntidadeDominio> listaComodos) {
		this.listaComodos = listaComodos;
	}

	public Dispositivo getDispositivoCadastro() {
		if (DispositivoCadastro == null)
			DispositivoCadastro = new Dispositivo();

		return DispositivoCadastro;
	}

	public void setDispositivoCadastro(Dispositivo DispositivoCadastro) {
		this.DispositivoCadastro = DispositivoCadastro;
	}

	public List<EntidadeDominio> getListaDispositivos() {
		return listaDispositivos;
	}

	public void setListaDispositivos(List<EntidadeDominio> listaDispositivos) {
		this.listaDispositivos = listaDispositivos;
	}

	public List<Dispositivo> getListaDispositivosFiltrados() {
		return listaDispositivosFiltrados;
	}

	public void setListaDispositivosFiltrados(List<Dispositivo> listaDispositivosFiltrados) {
		this.listaDispositivosFiltrados = listaDispositivosFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void novo() {
		DispositivoCadastro = new Dispositivo();
	}

	public void salvar() {
		DispositivoCadastro.setStatus("Ativo");
		try {
			// Obt�m o command para executar a respectiva opera��o
			ICommand command = commands.get(acao);
			/*
			 * Executa o command que chamar� a fachada para executar a opera��o
			 * requisitada o retorno � uma inst�ncia da classe resultado que
			 * pode conter mensagens derro ou entidades de retorno
			 */
			command.execute(DispositivoCadastro);
			DispositivoCadastro = new Dispositivo();

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMSGError("Erro ao tentar incluir Dispositivo:" + ex.getMessage());

		}

	}

	public void excluir() {
		try {

			// Obt�m o command para executar a respectiva opera��o
			ICommand command = commands.get(acao);
			/*
			 * Executa o command que chamar� a fachada para executar a opera��o
			 * requisitada o retorno � uma inst�ncia da classe resultado que
			 * pode conter mensagens derro ou entidades de retorno
			 */
			command.execute(DispositivoCadastro);

			DispositivoCadastro = new Dispositivo();

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMSGError("Erro ao tentar Excluir Dispositivo:" + ex.getMessage());

		}

	}

	public void editar() {
		try {
			// Obt�m o command para executar a respectiva opera��o
			ICommand command = commands.get(acao);
			/*
			 * Executa o command que chamar� a fachada para executar a opera��o
			 * requisitada o retorno � uma inst�ncia da classe resultado que
			 * pode conter mensagens derro ou entidades de retorno
			 */
			command.execute(DispositivoCadastro);
			DispositivoCadastro = new Dispositivo();

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMSGError("Erro ao tentar incluir Dispositivo:" + ex.getMessage());

		}

	}

	public void manipular() {
		String statusPro;
		if (DispositivoCadastro.getStatus().equals("Ativo")) {
			statusPro = "Desativado";
		} else {
			statusPro = "Ativo";
		}
		try {
			DispositivoCadastro.setStatus(statusPro);
			// Obt�m o command para executar a respectiva opera��o
			ICommand command = commands.get(acao);
			/*
			 * Executa o command que chamar� a fachada para executar a opera��o
			 * requisitada o retorno � uma inst�ncia da classe resultado que
			 * pode conter mensagens derro ou entidades de retorno
			 */
			command.execute(DispositivoCadastro);
			DispositivoCadastro = new Dispositivo();

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMSGError("Erro ao tentar incluir Dispositivo:" + ex.getMessage());

		}

	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				DispositivoCadastro = (Dispositivo) Fachada.buscarGenerico(codigo, new Dispositivo());

			}

		} catch (RuntimeException ex) {

		}
	}

	public void carregarPesquisa() {
		try {

			listaDispositivos = Fachada.listar(new Dispositivo());
		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Dispositivoes:" + ex.getMessage());

		}
	}

	public void carregarPesquisaComodo() {
		try {

			listaComodos = Fachada.listar(new Comodo());

		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Comodoes:" + ex.getMessage());

		}
	}

	public void carregarPesquisaTipo_Dispositivos() {
		try {

			listaTipo_Dispositivos = Fachada.listar(new Tipo_Dispositivo());
		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Tipo_Dispositivos:" + ex.getMessage());

		}
	}

	public boolean manipular(Dispositivo dispositivo) {

		try {
			// Create link to connect to serial arduino
			Link link = Link.getDefaultInstance(); // 1
			// Comment this row if you use just the default connection
			// link = getDigisparkConnection();
			// Get connected port on your pc
			List<String> portList = link.getPortList(); // 2
			if (portList != null && portList.size() > 0) {
				String port = portList.get(0);

				DigitalPinStatus digitalPinStatus = new DigitalPinStatus();
				System.out.println(digitalPinStatus);
				digitalPinStatus.setPin(3);
				System.out.println("Connecting on port: " + port);

				boolean connected = link.connect(port); // 3
				System.out.println("Connected:" + connected);

				Thread.sleep(8000); // 4

				int power = IProtocol.HIGH;
				String aux =dispositivo.getCodigo().toString();
				int teste=Integer.parseInt(aux);

				while (true) {
					System.out.println("Send power:" + power);
					link.sendPowerPinSwitch(teste, power); // Send energy to
														// the right pin
														// of your
														// sensor
					if (power == IProtocol.HIGH) {
						power = IProtocol.LOW;
					} else {
						power = IProtocol.HIGH;
					}
					Thread.sleep(8000);
				}
			} else {
				System.out.println("No port found!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

}