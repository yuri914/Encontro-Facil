package br.com.encontroFacil.view.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.encontroFacil.view.base.BasePage;
import br.com.encontroFacil.view.login.LoginPage;

public class MenuPanel extends Panel {

	private static final long serialVersionUID = -8467175106352427278L;

	public MenuPanel(String id)
	{
		super(id);
		
		criarComponentes();
	}

	private void criarComponentes()
	{
		add(new Link<Void>("linkSair"){

			private static final long serialVersionUID = 1487304160318452835L;

			@Override
			public void onClick()
			{
				getSession().invalidateNow();
				setResponsePage(new LoginPage());
			}
		});
		
		add(new Link<Void>("linkHome"){
			
			private static final long serialVersionUID = 865440152960195947L;

			@Override
			public void onClick()
			{
				setResponsePage(new BasePage());
			}
		});
	}

}
