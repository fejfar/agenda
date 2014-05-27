package br.com.agenda.services;

import java.util.List;

import javax.ejb.Local;

import br.com.agenda.domain.Contato;

@Local
public interface ContatoServiceLocal {

	void salvar(Contato contato);

	void remover(Long id);

	List<Contato> listar();

	Contato buscarPorId(Long id);
}
