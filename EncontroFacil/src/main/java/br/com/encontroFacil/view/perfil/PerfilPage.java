package br.com.encontroFacil.view.perfil;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.service.ContatoService;
import br.com.encontroFacil.view.base.BasePage;

/**
 * @author Yuri
 */
public class PerfilPage extends BasePage {

	private static final long serialVersionUID = -4026405758311652824L;

	/** Services */
	@SpringBean
	private ContatoService contatoService;
	/** feedback. */
	private FeedbackPanel feedback;
	/** panel principal. */
	private WebMarkupContainer panelPrincipal;

	public PerfilPage()
	{
		setOutputMarkupId(true);
		criarPagina();
	}

	private void criarPagina()
	{
		criarPanelPrincipal();
		
		feedback = new FeedbackPanel("feedbackPerfil");
		feedback.setOutputMarkupId(true);
		add(feedback);
		
		panelPrincipal.add(new ContatoPanel("panel", getContatoUsuarioSessao()));
		
		criarComponentes();
	}

	private void criarComponentes()
	{
		criarContatoLink();
		criarFotoPerfilLink();
		criarSegurancaLink();
	}

	private void criarPanelPrincipal()
	{
		panelPrincipal = new WebMarkupContainer("container");
		panelPrincipal.setOutputMarkupId(true);
		add(panelPrincipal);
	}

	private void criarContatoLink()
	{
		add(new AjaxLink<Void>("contatoLink"){

			private static final long serialVersionUID = -7062724356641293737L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				panelPrincipal.addOrReplace(new ContatoPanel("panel", getContatoUsuarioSessao()));
				target.add(panelPrincipal);
			}

		});
	}

	private void criarFotoPerfilLink()
	{
		add(new AjaxLink<Void>("fotoPerfilLink"){

			private static final long serialVersionUID = -4255328958421289203L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				panelPrincipal.addOrReplace(new FotoPerfilPanel("panel"));
				target.add(panelPrincipal);
			}
		});
	}

	private void criarSegurancaLink()
	{
		add(new AjaxLink<Void>("segurancaLink"){

			private static final long serialVersionUID = 7644544297276001631L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				panelPrincipal.addOrReplace(new InformacoesSegurancaPanel("panel"));
				target.add(panelPrincipal);
			}
		});
	}
	
	/**
	 * Recupera o contato do usuario logado.
	 * @return contato
	 */
	private Contato getContatoUsuarioSessao()
	{
		Contato contato = contatoService.recuperarContatoUsuario((Usuario) getSession().getAttribute("usuarioSessao"));
		if(contato.getId() == null)
		{
			contato.setUsuario((Usuario) getSession().getAttribute("usuarioSessao"));
		}
		return contato;
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/geral.css"));
		response.render(CssHeaderItem.forUrl("css/960.css"));
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
		response.render(CssHeaderItem.forUrl("css/signinTemplate.css"));
		response.render(JavaScriptHeaderItem.forUrl("js/bootstrap.js"));
		response.render(JavaScriptHeaderItem.forUrl("js/jquery-1.11.1.min.js"));
	}
}
