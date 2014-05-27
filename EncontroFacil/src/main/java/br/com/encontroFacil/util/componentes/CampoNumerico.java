package br.com.encontroFacil.util.componentes;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

/**
 * Componente de campo numerico. Cria somente campos textos que aceitam numeros.
 * 
 * @param <T>
 *            tipo genérico
 * @author luis.fernandez
 * @version 1.0.0
 */
public class CampoNumerico<T> extends TextField<T> {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instância um novo campo numerico.
	 * 
	 * @param id
	 *            id
	 * @param obrigatorio
	 *            obrigatorio
	 */
	public CampoNumerico(final String id) {
		super(id);
		setOutputMarkupId(true);
	}

	/**
	 * Instância um novo campo numerico.
	 * 
	 * @param id
	 *            id
	 * @param type
	 *            type
	 * @param obrigatorio
	 *            obrigatorio
	 */
	public CampoNumerico(final String id, final Class<T> type) {
		super(id, type);
		setOutputMarkupId(true);
	}

	/**
	 * Instância um novo campo numerico.
	 * 
	 * @param id
	 *            id
	 * @param model
	 *            model
	 * @param obrigatorio
	 *            obrigatorio
	 */
	public CampoNumerico(final String id, final IModel<T> model) {
		super(id, model);
		setOutputMarkupId(true);
	}

	/**
	 * Instância um novo campo numerico.
	 * 
	 * @param id
	 *            id
	 * @param model
	 *            model
	 * @param type
	 *            type
	 * @param obrigatorio
	 *            obrigatorio
	 */
	public CampoNumerico(final String id, IModel<T> model, Class<T> type) {
		super(id, model, type);
		setOutputMarkupId(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.funai.wicket.ui.basico.CampoTexto#renderHead(org.apache.wicket
	 * .markup.head.IHeaderResponse)
	 */
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(OnDomReadyHeaderItem.forScript("$('#" + getMarkupId() + "').numeric()"));
	}
}
