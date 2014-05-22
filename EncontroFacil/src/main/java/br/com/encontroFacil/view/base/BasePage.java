package br.com.encontroFacil.view.base;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;

import br.com.encontroFacil.view.menu.MenuPanel;

public class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public BasePage() {

		criarPagina();
		
    }

	private void criarPagina()
	{
		add(new MenuPanel("menuPanel"));
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/geral.css"));
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
		response.render(CssHeaderItem.forUrl("css/animate.css"));
		response.render(JavaScriptHeaderItem.forUrl("js/menu.js"));
	}
}
