package br.com.encontroFacil.view.perfil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.encontroFacil.model.Cidade;
import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Email;
import br.com.encontroFacil.model.Estado;
import br.com.encontroFacil.model.Telefone;
import br.com.encontroFacil.model.vo.EnderecoVO;
import br.com.encontroFacil.service.CidadeService;
import br.com.encontroFacil.service.ContatoService;
import br.com.encontroFacil.service.EmailService;
import br.com.encontroFacil.service.EstadoService;
import br.com.encontroFacil.service.TelefoneService;

public class ContatoPanel extends Panel {

	private static final long serialVersionUID = 8027264158676896989L;
	
	/** Services */
	@SpringBean
	private ContatoService contatoService;
	@SpringBean
	private CidadeService cidadeService;
	@SpringBean
	private EstadoService estadoService;
	@SpringBean
	private EmailService emailService;
	@SpringBean
	private TelefoneService telefoneService;
	/** Contato. */
	private Contato contato;
	/** Lista Email. */
	private List<Email> listaEmail;
	/** Lista Telefone. */
	private List<Telefone> listaTelefone;
	/** Feedback. */
	private FeedbackPanel feedback;
	/** Cep TextField. */
	private TextField<Integer> cep;
	/** Estados DropDownChoice */
	private DropDownChoice<Estado> estados;
	/** Cidades DropDownChoice */
	private DropDownChoice<Cidade> cidades;
	/** Container Email. */
	private WebMarkupContainer containerEmails;
	/** Container Endereco. */
	private WebMarkupContainer containerEndereco;
	/** Container Telefones. */
	private WebMarkupContainer containerTelefones;
	private Form<Contato> formulario;


	public ContatoPanel(String id, Contato contato)
	{
		super(id);
		this.contato = contato;
		criarPagina();
	}

	private void criarPagina()
	{
		formulario = new Form<Contato>("formulario", new CompoundPropertyModel<Contato>(contato));
		add(formulario);
		
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		formulario.add(feedback);
		
		criarComponentes();
	}

	private void criarComponentes()
	{
		criarPanelEndereco();
		criarPanelEmail();
		criarPanelTelefone();
		criarBtnSalvar();
	}
	
	private void criarPanelEndereco()
	{
		containerEndereco = new WebMarkupContainer("containerEndereco");
		containerEndereco.setOutputMarkupId(true);
		formulario.add(containerEndereco);
		
		cep = new TextField<Integer>("cep");
		cep.add(new AjaxFormComponentUpdatingBehavior("onblur")
		{

			private static final long serialVersionUID = -5980431044207455543L;

			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				EnderecoVO enderecoEncontrado = contatoService.buscarCep(cep.getModelObject());
				if (enderecoEncontrado != null) {
					getContato().setLogradouro((enderecoEncontrado.getLogradouro()));
					getContato().setBairro((enderecoEncontrado.getBairro()));
				} else {
					error("Cep não encontrado.");
				}
				target.add(containerEndereco, feedback);
			}
		});
		containerEndereco.add(cep);

		containerEndereco.add(new TextField<String>("logradouro"));
		containerEndereco.add(new TextField<String>("bairro"));
		containerEndereco.add(new TextField<String>("complemento"));
		
		estados = new DropDownChoice<Estado>("estado", estadoService.buscarListaEstados());
		estados.setChoiceRenderer(new ChoiceRenderer<Estado>("nome", "id"));
		estados.setModel(new Model<Estado>());
		estados.add(new AjaxFormComponentUpdatingBehavior("onchange")
		{
			private static final long serialVersionUID = -5980431044207455543L;

			protected void onUpdate(AjaxRequestTarget target)
			{
				target.add(cidades);
			}
		});
		containerEndereco.add(estados);

		cidades = new DropDownChoice<Cidade>("cidade", new ArrayList<Cidade>(), new ChoiceRenderer<Cidade>("nome")){
			
			private static final long serialVersionUID = -8573413588932607180L;

			@Override
			protected void onConfigure()
			{
				if(estados.getModelObject() != null)
				{
					setChoices(cidadeService.buscarListaCidades(estados.getModelObject()));
				}
			}
		};
		containerEndereco.add(cidades);
	}

	private void criarPanelTelefone()
	{
		containerTelefones = new WebMarkupContainer("containerTelefones");
		containerTelefones.setOutputMarkupId(true);
		formulario.add(containerTelefones);
		
		containerTelefones.add(new ListView<Telefone>("telefones", getTelefones()){

			private static final long serialVersionUID = -6171565687492579049L;

			@Override
			protected void populateItem(ListItem<Telefone> item)
			{
				Telefone atual = item.getModelObject();
				item.add(new DropDownChoice<Integer>("ddd",new PropertyModel<Integer>(atual, "ddd") , Arrays.asList(21, 11, 71, 85, 51, 31)));
				item.add(new TextField<Integer>("numero", new PropertyModel<Integer>(atual, "numero")));
				item.add(criarBtnNovoTelefone(atual));
				item.add(criarBtnRemoverTelefone(atual));
			}

			private AjaxButton criarBtnNovoTelefone(Telefone atual)
			{
				return new AjaxButton("adicionarTelefone")
				{
					private static final long serialVersionUID = 2740087915303507582L;

					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form)
					{
						getTelefones().add(new Telefone(getContato(), false));
						target.add(containerTelefones);
					}
				};
			}
			
			private AjaxButton criarBtnRemoverTelefone(final Telefone atual)
			{
				return new AjaxButton("removerTelefone")
				{
					private static final long serialVersionUID = 2740087915303507582L;

					protected void onConfigure() {
						setVisible(!atual.getPrimario());
					};
					
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form)
					{
						getTelefones().remove(atual);
						target.add(containerTelefones);
					}

				};
			}
		});
	}

	private void criarPanelEmail()
	{
		containerEmails = new WebMarkupContainer("containerEmails");
		containerEmails.setOutputMarkupId(true);
		formulario.add(containerEmails);
		
		containerEmails.add(new ListView<Email>("emails", getEmails()){

			private static final long serialVersionUID = -6171565687492579049L;

			@Override
			protected void populateItem(ListItem<Email> item)
			{
				Email atual = item.getModelObject();
				item.add(new TextField<String>("email", new PropertyModel<String>(atual, "email")));
				item.add(criarBtnNovoEmail(atual));
				item.add(criarBtnRemoverEmail(atual));
			}

			private AjaxButton criarBtnNovoEmail(Email atual)
			{
				return new AjaxButton("adicionarEmail")
				{
					private static final long serialVersionUID = 2740087915303507582L;

					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form)
					{
						getEmails().add(new Email(getContato(), false));
						target.add(containerEmails);
					}
				};
			}
			
			private AjaxButton criarBtnRemoverEmail(final Email atual)
			{
				return new AjaxButton("removerEmail")
				{
					private static final long serialVersionUID = 2740087915303507582L;

					protected void onConfigure() {
						setVisible(!atual.getPrimario());
					};
					
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form)
					{
						getEmails().remove(atual);
						target.add(containerEmails);
					}
				};
			}
		});
	}
	
	private void criarBtnSalvar()
	{
		formulario.add(new AjaxButton("btnAtualizar")
		{
			private static final long serialVersionUID = -8512551000762802515L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				getContato().setEmails(getEmails());
				getContato().setTelefones(getTelefones());
				contatoService.salvarContato(getContato());
				feedback.success("Informações de contato atualizadas com sucesso.");
				target.add(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
				target.add(feedback);
			}
		});
	}
	
	public Contato getContato()
	{
		if (contato == null) {
			contato = new Contato();
		}
		return contato;
	}

	public List<Email> getEmails()
	{
		if (listaEmail == null) {
			listaEmail = emailService.buscarEmails(getContato());
		}
		return listaEmail;
	}

	private List<Telefone> getTelefones()
	{
		if (listaTelefone == null) {
			listaTelefone = telefoneService.buscarTelefones(getContato());
		}
		return listaTelefone;
	}
}
