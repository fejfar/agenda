package br.com.agenda;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.agenda.domain.Contato;
import br.com.agenda.services.ContatoServiceLocal;

@ManagedBean(name = "agendaMB")
@SessionScoped
public class AgendaMB {

	@EJB
	private ContatoServiceLocal contatoServiceLocal;

	private List<Contato> contatos = new ArrayList<Contato>();

	private Contato contatoInclusao;

	private Contato contato;

	@PostConstruct
	// primeiro metodo a ser inicializado
	public String iniciar() {
		contatos = contatoServiceLocal.listar();
		return listar();
	}

	public String listar() {
		contatos = contatoServiceLocal.listar();
		return "index.xhtml";
	}

	public String prepararInclusao() {
		contatoInclusao = new Contato();
		return "incluir.xhtml";
	}

	public String incluir() {
		contatoServiceLocal.salvar(contatoInclusao);
		return listar();
	}

	public String alterar() {
		contatoServiceLocal.salvar(contato);
		return listar();
	}

	public String prepararAlteracao() {
		String id = obterParametro("id");
		contato = contatoServiceLocal.buscarPorId(Long.valueOf(id));
		return "alterar.xhtml";
	}

	public String remover() {
		String id = obterParametro("id");
		contatoServiceLocal.remover(Long.valueOf(id));
		return listar();
	}

	public String cancelar() {
		return "index.xhtml";
	}

	private String obterParametro(String parametro) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(parametro);
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Contato getContatoInclusao() {
		return contatoInclusao;
	}

	public void setContatoInclusao(Contato contatoInclusao) {
		this.contatoInclusao = contatoInclusao;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
