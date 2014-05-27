package br.com.agenda.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.agenda.domain.Audit;

public class AuditInterceptor {
	
	@PersistenceContext(name = "agenda-pu")
	EntityManager em;
	
	// metodo que intercepta
	@AroundInvoke
	public Object initialize(InvocationContext context) throws Exception{
		String nome = context.getTarget().getClass().getName();
		String metodo = context.getMethod().getName();
		String parametros = context.getParameters()[0].toString(); 
		
		Audit audit = new Audit();
		audit.setClasse(nome);
		audit.setMetodo(metodo);
		audit.setParametros(parametros);
		em.persist(audit);
		
		return context.proceed();
	}
	
}
