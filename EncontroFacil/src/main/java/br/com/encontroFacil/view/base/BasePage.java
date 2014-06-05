package br.com.encontroFacil.view.base;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.service.ContatoService;
import br.com.encontroFacil.view.contato.ContatoCadastroPanel;
import br.com.encontroFacil.view.menu.MenuPanel;

public class BasePage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	private FeedbackPanel feedback;
	
	@SpringBean
	private ContatoService contatoService;
	
	public BasePage() 
	{
		criarPagina();
    }

	private void criarPagina()
	{
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);
		
		add(new MenuPanel("menuPanel"));
		add(new ContatoCadastroPanel("conteudoPanel", contatoService.recuperarContatoUsuario((Usuario) getSession().getAttribute("usuarioSessao")), feedback));
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/geral.css"));
		response.render(CssHeaderItem.forUrl("css/960.css"));
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
		response.render(CssHeaderItem.forUrl("css/signinTemplate.css"));
		response.render(JavaScriptHeaderItem.forUrl("js/bootstrap.js"));
	}
}
