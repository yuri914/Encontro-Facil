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
	private Form<Usuario> formularioLogin;
	private Usuario usuario;

	public LoginAcessoPanel(String id, Usuario usuario, FeedbackPanel feedback)
	{
		super(id);
		this.usuario = usuario;
		this.feedback = feedback;
		criarPagina();
	}

	private void criarPagina()
	{
		formularioLogin = new Form<Usuario>("formularioLogin", new CompoundPropertyModel<Usuario>(usuario));
		add(formularioLogin);
		
		criarComponentes();
	}

	private void criarComponentes()
	{
		formularioLogin.add(new TextField<String>("login"));
		
		formularioLogin.add(new PasswordTextField("senha").setRequired(false));
		
		formularioLogin.add(new AjaxButton("entrar"){

			private static final long serialVersionUID = -5366193047654914826L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				Usuario usuarioEncontrado = autenticarUsuario(); 
				if(usuarioEncontrado != null)
				{
					getSession().setAttribute("usuarioSessao", usuarioEncontrado);
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
	private Usuario autenticarUsuario()
	{
		return usuarioService.autenticarUsuario(getUsuario());
	}
	
	private Usuario getUsuario()
	{
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
}
