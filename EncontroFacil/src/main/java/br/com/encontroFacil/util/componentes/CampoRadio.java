package br.com.encontroFacil.util.componentes;

import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;

public class CampoRadio<T> extends RadioChoice<T> {

	private static final long serialVersionUID = -2828596296826686460L;

	public CampoRadio(String id)
	{
		super(id);
	}
	
	public CampoRadio(String id, IModel<? extends List<? extends T>> choices)
	{
		super(id, choices);
	}
	
	public CampoRadio(String id, IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer)
	{
		super(id, choices, renderer);
	}
	
	public CampoRadio(String id, IModel<T> model, IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer)
	{
		super(id, model, choices, renderer);
	}
	
	public CampoRadio(String id, IModel<T> model, List<? extends T> choices)
	{
		super(id, model, choices);
	}

//	@Override
//	protected void appendOptionHtml(AppendingStringBuffer asb, T t, int arg2, String arg3)
//	{
//		super.appendOptionHtml(asb, t, arg2, arg3);
//	}
	
	public CampoRadio(String id, List<? extends T> choices, IChoiceRenderer<? super T> renderer)
	{
		super(id, choices, renderer);
	}

}
