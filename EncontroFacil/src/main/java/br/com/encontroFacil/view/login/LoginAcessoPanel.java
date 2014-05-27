package br.com.encontroFacil.view.login;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.service.UsuarioService;
import br.com.encontroFacil.view.base.BasePage;

public class LoginAcessoPanel extends Panel {

	private static final long serialVersionUID = 2531040320678069015L;
	
	@SpringBean
	private UsuarioService usuarioService;
	private FeedbackPanel feedback;
	private Usuario usuario;

	public LoginAcessoPanel(String id, Usuario usuario, FeedbackPanel feedback)
	{
		super(id, new CompoundPropertyModel<Usuario>(usuario));
		this.usuario = usuario;
		this.feedback = feedback;
		criarPagina();
	}

	private void criarPagina()
	{
		criarComponentes();
	}

	private void criarComponentes()
	{
		add(new TextField<String>("login"));
		
		add(new PasswordTextField("senha").setRequired(false));
		
		add(new AjaxButton("entrar"){

			private static final long serialVersionUID = -5366193047654914826L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				if(autenticarUsuario())
				{
					getSession().setAttribute("usuario", getUsuario());
					setResponsePage(new BasePage());
				}
				else
				{
					feedback.error("Usuário ou senha incorretos.");
					target.add(feedback);
				}
			}

		});
	}

	/** Autentica o usuário */
	private boolean autenticarUsuario()
	{
		return usuarioService.autenticarUsuario(getUsuario()) != null;
	}
	
	private Usuario getUsuario()
	{
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
}
