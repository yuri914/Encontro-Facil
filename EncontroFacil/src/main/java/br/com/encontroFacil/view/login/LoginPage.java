package br.com.encontroFacil.view.login;

import java.util.Arrays;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.service.UsuarioService;
import br.com.encontroFacil.util.enumeration.Genero;
import br.com.encontroFacil.view.base.BasePage;

/**
 * @author Yuri
 */
public class LoginPage extends WebPage {

	private static final long serialVersionUID = 7554956749029579434L;
	
	@SpringBean
	private UsuarioService usuarioService;
	private FeedbackPanel feedback;
	private Form<Usuario> formularioCadastro;
	private Usuario usuario;

	public LoginPage()
	{
		criarPagina();
	}

	private void criarPagina()
	{
		formularioCadastro = new Form<Usuario>("formularioCadastro", new CompoundPropertyModel<Usuario>(getUsuario()));
		add(formularioCadastro);
		
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);
		
		add(new LoginAcessoPanel("loginAcessoPanel", new Usuario(), feedback));
		
		criarComponentes();
	}
	
	private void criarComponentes()
	{
		formularioCadastro.add(new TextField<String>("nome"));
		
		formularioCadastro.add(new RadioChoice<Genero>("genero", Arrays.asList(Genero.values()), new ChoiceRenderer<Genero>("descricao")).setSuffix(""));
		
		formularioCadastro.add(new TextField<String>("dataNascimento"));
		
		formularioCadastro.add(new TextField<Long>("cpf"));
		
		formularioCadastro.add(new TextField<String>("login"));
		
		formularioCadastro.add(new TextField<String>("senha"));
		
		formularioCadastro.add(new AjaxButton("btnCadastrar") {

			private static final long serialVersionUID = -4309959029136917439L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				formularioCadastro.getModelObject().setDataCadastro(new Date());
				usuarioService.salvarUsuario(formularioCadastro.getModelObject());
				getSession().setAttribute("usuarioSessao", formularioCadastro.getModelObject());
				setResponsePage(new BasePage());
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		});
	}

	private Usuario getUsuario()
	{
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/geral.css"));
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
		response.render(CssHeaderItem.forUrl("css/signinTemplate.css"));
		response.render(CssHeaderItem.forUrl("css/960.css"));
		response.render(JavaScriptHeaderItem.forUrl("js/numeric.js"));
		response.render(JavaScriptHeaderItem.forUrl("js/bootstrap.js"));
	}
}
