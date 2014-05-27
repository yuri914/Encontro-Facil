package br.com.encontroFacil.util.componentes;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class CampoCpf<T> extends TextField<T> {

	private static final long serialVersionUID = -380841363534596086L;

	public CampoCpf(String id)
	{
		super(id);
	}
	
	public CampoCpf(String id, IModel<T> model)
	{
		super(id, model);
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(OnDomReadyHeaderItem.forScript("$(document).ready(function(){ $('#" + getMarkupId() + "').mask('999.999.999-99');});"));
	}
}
