package br.com.encontroFacil.view.perfil;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.model.vo.LoginVO;
import br.com.encontroFacil.service.UsuarioService;

public class InformacoesSegurancaPanel extends Panel {

	private static final long serialVersionUID = -6029226650747255548L;
	
	@SpringBean
	private UsuarioService usuarioService;
	private Form<LoginVO> formulario;
	private FeedbackPanel feedback;
	private LoginVO loginVO;

	private TextField<String> campoSenhaAntiga;

	private PasswordTextField campoNovaSenha;

	private PasswordTextField campoConfirmacaoSenha;

	public InformacoesSegurancaPanel(String id)
	{
		super(id);
		setOutputMarkupId(true);
		criarPagina();
	}

	private void criarPagina()
	{
		formulario = new Form<LoginVO>("formulario", new CompoundPropertyModel<LoginVO>(getLoginVO()));
		add(formulario);
		
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		formulario.add(feedback);
		
		criarComponentes();
	}

	private void criarComponentes()
	{
		campoSenhaAntiga = new TextField<String>("senhaAntiga");
		campoSenhaAntiga.setRequired(true);
		campoSenhaAntiga.setLabel(Model.of("Senha antiga"));
		formulario.add(campoSenhaAntiga);
		
		campoNovaSenha = new PasswordTextField("novaSenha");
		campoNovaSenha.setRequired(true);
		campoNovaSenha.setLabel(Model.of("Nova senha"));
		formulario.add(campoNovaSenha);
		
		campoConfirmacaoSenha = new PasswordTextField("confirmacaoNovaSenha");
		campoConfirmacaoSenha.setRequired(true);
		campoConfirmacaoSenha.setLabel(Model.of("Confirmação de senha"));
		formulario.add(campoConfirmacaoSenha);
		
		formulario.add(new AjaxButton("btnAtualizar"){

			private static final long serialVersionUID = -1334811295043757208L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				if(validarAtualizacaoSenha())
				{
					getUsuarioSessao().setSenha(getLoginVO().getNovaSenha());
					usuarioService.alterarUsuario(getUsuarioSessao());
					success("Senha atualizada com sucesso.");
					limparCampos();
					target.add(InformacoesSegurancaPanel.this);
				}
				else
				{
					target.add(feedback);
				}
			}
		});
	}

	protected void limparCampos()
	{
		getLoginVO().setSenhaAntiga(null);
		getLoginVO().setNovaSenha(null);
		getLoginVO().setConfirmacaoNovaSenha(null);
	}

	protected boolean validarAtualizacaoSenha()
	{
		boolean isValido = true;
		
		if(!validarSenhaAntiga())
		{
			error("Senha antiga incorreta.");
			isValido = false;
		}
		
		if(!validarNovaSenha())
		{
			error("Confirmação de senha incorreta.");
			isValido = false;
		}
		
		return isValido;
	}

	private boolean validarSenhaAntiga()
	{
		return getLoginVO().getSenhaAntiga().equals(getUsuarioSessao().getSenha());
	}
	
	protected boolean validarNovaSenha()
	{
		return getLoginVO().getNovaSenha().equals(getLoginVO().getConfirmacaoNovaSenha());
	}

	public Usuario getUsuarioSessao()
	{
		return (Usuario) getSession().getAttribute("usuarioSessao");
	}
	
	public LoginVO getLoginVO()
	{
		if (loginVO == null) {
			loginVO = new LoginVO();
		}
		return loginVO;
	}

}
