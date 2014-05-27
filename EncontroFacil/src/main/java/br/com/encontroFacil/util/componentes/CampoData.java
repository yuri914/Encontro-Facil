package br.com.encontroFacil.util.componentes;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class CampoData<T> extends TextField<T> {

	private static final long serialVersionUID = 8994646806742560396L;

	public CampoData(String id)
	{
		super(id);
	}
	
	public CampoData(String id, IModel<T> model)
	{
		super(id, model);
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(OnDomReadyHeaderItem.forScript("$(document).ready(function(){ $('#" + getMarkupId() + "').mask('99/99/9999');});"));
	}

}
