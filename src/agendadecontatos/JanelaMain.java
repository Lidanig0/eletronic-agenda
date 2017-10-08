package agendadecontatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class JanelaMain extends javax.swing.JFrame {

	public JanelaMain() {
		initComponents();
		this.setTitle("Agenda Eletrônica");
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	private void initComponents() {
	    
		PanelRoot = new javax.swing.JPanel();
    	PanelDesc = new javax.swing.JPanel();
    	
    	ScrollerContatos = new javax.swing.JScrollPane();
    	ScrollerTelefones = new javax.swing.JScrollPane();
    	ScrollerAddress = new javax.swing.JScrollPane();
        
    	listaDeContatosNaTela = new javax.swing.JList<>();
    	listaDeTelefonesNaTela = new javax.swing.JList<>();
      	listaDeAddressNaTela = new javax.swing.JList<>();
        
      	AddContato = new javax.swing.JButton();
      	AddEndereco = new javax.swing.JButton();
        
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        
        TextName = new javax.swing.JTextField(null);
        TextLastname = new javax.swing.JTextField(null);
        TextIdade = new javax.swing.JTextField(null);
        TextRua = new javax.swing.JTextField(null);
        TextNumero = new javax.swing.JTextField(null);
        TextBairro = new javax.swing.JTextField(null);
        TextComplemento = new javax.swing.JTextField(null);
        TextCidade = new javax.swing.JTextField(null);
        TextEstado = new javax.swing.JTextField(null);
        TextCep = new javax.swing.JTextField(null);
        TextPais = new javax.swing.JTextField(null);
        TextEmail = new javax.swing.JTextField(null);
        TextTelefone = new javax.swing.JTextField(null);
        TextComplemento.setEditable(false);
        TextName.setEditable(false);
        TextLastname.setEditable(false);
        TextIdade.setEditable(false);
        TextRua.setEditable(false);
        TextNumero.setEditable(false);
        TextBairro.setEditable(false);
        TextCidade.setEditable(false);
        TextEstado.setEditable(false);
        TextCep.setEditable(false);
        TextPais.setEditable(false);
        TextEmail.setEditable(false);
        TextTelefone.setEditable(false);
        
        PanelRoot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        if (Main.AGENDA.size() > 0 && index > 0){
    		selecionado = Main.AGENDA.get(JanelaMain.index);
        }
        
        AddContato.setText("Adicionar Contato");
        AddContato.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		AddNewContato(evt);
        	}
        });
	
        AddEndereco.setText("Adicionar Endereço");
        AddEndereco.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		AddNewAddress(evt);
        	}
        });
	  
        Editar();
        
        listaDeContatosNaTela = new JList<String>();
		listaDeContatosNaTela.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				index = listaDeContatosNaTela.getSelectedIndex();
				
				if (index < Main.AGENDA.size() && index >= 0){
					selecionado = Main.AGENDA.get(JanelaMain.index);
					
					TextName.setText(selecionado.nome);
					TextLastname.setText(selecionado.sobrenome);
					TextIdade.setText(selecionado.idade);
					TextEmail.setText(selecionado.email);
					
					RefreshEnd();
					RefreshTelefones();
				}
			}
		});
		
		listaDeContatosNaTela.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	
		    	if (SwingUtilities.isRightMouseButton(evt)) {
		    		
		    		listaDeContatosNaTela.setSelectedIndex(listaDeContatosNaTela.locationToIndex(evt.getPoint()));
		            JPopupMenu menu = new JPopupMenu();
		            JMenuItem itemRemove = new JMenuItem("Excluir");
		            
		            itemRemove.addActionListener(new ActionListener() {
		            	public void actionPerformed(ActionEvent e) {
		            		if (Main.AGENDA.size() > 0){
			                	for (int i = 0; i < selecionado.address.size(); i++) {
			                		if (selecionado.address.size() > 0){
			                			selecionado.address.remove(i);
			                		}
			                	}
			                	for (int j = 0; j < selecionado.telefones.size(); j++) {
			                		if (selecionado.telefones.size() > 0){
				                		selecionado.telefones.remove(j);
			                		}
								}
			                	
			                	RefreshEnd();
			                	RefreshTelefones();
			                				 				
			                	Main.AGENDA.remove(selecionado);
			                	listaDeContatos.remove(JanelaMain.index);
			                	RefreshContato();
		            		}
		                }
		            });
		            menu.add(itemRemove);
		            menu.show(listaDeContatosNaTela, evt.getPoint().x, evt.getPoint().y);            
		        }
		    	
		        if (evt.getClickCount() == 2) {
		        	
		            AddTelefone addTel = new AddTelefone();
		            
		            SetVisible(false);		            
		            addTel.setVisible(true);
		        }
		    }
		});
		
		RefreshContatos();
		
		listaDeAddressNaTela = new JList<String>();
		listaDeAddressNaTela.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				indexAddress = listaDeAddressNaTela.getSelectedIndex();
				RefreshAddress();
				
				if (indexAddress < selecionado.address.size() && indexAddress >= 0 && index >= 0){
					
					TextRua.setText(selecionado.address.get(indexAddress).rua);
					TextCidade.setText(selecionado.address.get(indexAddress).cidade);
					TextEstado.setText(selecionado.address.get(indexAddress).estado);
					TextNumero.setText(selecionado.address.get(indexAddress).numero);
					TextPais.setText(selecionado.address.get(indexAddress).pais);
					TextCep.setText(selecionado.address.get(indexAddress).cep);
					TextComplemento.setText(selecionado.address.get(indexAddress).complemento);
					TextBairro.setText(selecionado.address.get(indexAddress).bairro);
				}
				
			}
		});
		
		listaDeAddressNaTela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				
		    	if (SwingUtilities.isRightMouseButton(evt)) {
		    		
		    		listaDeAddressNaTela.setSelectedIndex(listaDeAddressNaTela.locationToIndex(evt.getPoint()));

		            JPopupMenu menu = new JPopupMenu();
		            JMenuItem itemRemove = new JMenuItem("Excluir");
		            itemRemove.addActionListener(new ActionListener() {
		            	public void actionPerformed(ActionEvent e) {
		            		
		            		if (Main.AGENDA.size() > 0){
			            		if (selecionado.address.size() > 0 && index >= 0){
				                	
				                	selecionado.address.remove(indexAddress);
				                	
				                	RefreshAddress();
				                	RefreshEnd();
				 				}
		            		}
		            	}
		             });
		             menu.add(itemRemove);
		             menu.show(listaDeAddressNaTela, evt.getPoint().x, evt.getPoint().y);            
		         }
		    }
		});
		
		listaDeTelefonesNaTela = new JList<String>();
		listaDeTelefonesNaTela.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				indexTelefones = listaDeTelefonesNaTela.getSelectedIndex();
				
				TextTelefone.setText(null);
				
				if (indexTelefones < selecionado.telefones.size() && indexTelefones >= 0 && index >= 0){
					TextTelefone.setText(
							selecionado.telefones.get(indexTelefones).Countrycode + 
							selecionado.telefones.get(indexTelefones).ddd + 
							selecionado.telefones.get(indexTelefones).telefone
					);
				}
			}
		});
		
		listaDeTelefonesNaTela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				
		    	if (SwingUtilities.isRightMouseButton(evt)) {
		    		
		    		listaDeTelefonesNaTela.setSelectedIndex(listaDeTelefonesNaTela.locationToIndex(evt.getPoint()));

		            JPopupMenu menu = new JPopupMenu();
		            JMenuItem itemRemove = new JMenuItem("Excluir");
		            itemRemove.addActionListener(new ActionListener() {
		            	public void actionPerformed(ActionEvent e) {
		            		if (Main.AGENDA.size() > 0){
		            			if (selecionado.telefones.size() > 0 && index >= 0){
				                	
				                	selecionado.telefones.remove(indexTelefones);
				                	RefreshTelefones();
				 				}
		            		}
		            	}
		             });
		             menu.add(itemRemove);
		             menu.show(listaDeTelefonesNaTela, evt.getPoint().x, evt.getPoint().y);            
		         }
		    }
		});
		
		ScrollerContatos.setViewportView(listaDeContatosNaTela);
		ScrollerTelefones.setViewportView(listaDeTelefonesNaTela);
		ScrollerAddress.setViewportView(listaDeAddressNaTela);
        
        javax.swing.GroupLayout PanelRootLayout = new javax.swing.GroupLayout(PanelRoot);
        PanelRoot.setLayout(PanelRootLayout);
        PanelRootLayout.setHorizontalGroup(
            PanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRootLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddEndereco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrollerContatos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AddContato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelRootLayout.setVerticalGroup(
            PanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollerContatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddContato, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelDesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Nome");
        jLabel2.setText("Sobrenome");
        jLabel3.setText("Email");
        jLabel4.setText("Idade");
        jLabel5.setText("Phone");
        jLabel6.setText("Rua");
        jLabel7.setText("Bairro");
        jLabel8.setText("Cidade");
        jLabel9.setText("CEP");
        jLabel10.setText("Número");
        jLabel11.setText("Complem.");
        jLabel12.setText("Estado");
        jLabel14.setText("Endereços");
        jLabel13.setText("País");
        jLabel15.setText("Telefones");

        javax.swing.GroupLayout PanelDescLayout = new javax.swing.GroupLayout(PanelDesc);
        PanelDesc.setLayout(PanelDescLayout);
        PanelDescLayout.setHorizontalGroup(
            PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDescLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDescLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(PanelDescLayout.createSequentialGroup()
                        .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDescLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextName, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDescLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDescLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextRua, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDescLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDescLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDescLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextCep, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ScrollerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDescLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelDescLayout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(6, 6, 6)
                                        .addComponent(TextEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelDescLayout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(6, 6, 6)
                                        .addComponent(TextComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelDescLayout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(6, 6, 6)
                                        .addComponent(TextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelDescLayout.createSequentialGroup()
                                        .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TextLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TextEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDescLayout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(TextPais, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDescLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ScrollerTelefones, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
            .addGroup(PanelDescLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel14)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelDescLayout.setVerticalGroup(
            PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDescLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDescLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextRua, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextCep, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextPais, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDescLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollerAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScrollerTelefones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
	}
		
	private void AddNewContato(java.awt.event.ActionEvent evt) {                                           
		AddContatos add = new AddContatos();
      
		add.setVisible(true);
		this.setVisible(false);
	}
  
	private void AddNewAddress(java.awt.event.ActionEvent evt) {                                           
		AddEndereco addEnd = new AddEndereco();
		
	    addEnd.setVisible(true);
	    this.setVisible(false);
	}
  
	  private void RefreshAddress(){
		  TextRua.setText(null);
		  TextCidade.setText(null);
		  TextEstado.setText(null);
		  TextNumero.setText(null);
		  TextPais.setText(null);
		  TextCep.setText(null);
		  TextComplemento.setText(null);
		  TextBairro.setText(null);
	  }
  
	  private void RefreshContato(){
		  TextName.setText(null);
		  TextLastname.setText(null);
		  TextIdade.setText(null);
		  TextTelefone.setText(null);
		  TextEmail.setText(null);
		  TextRua.setText(null);
		  TextCidade.setText(null);
		  TextEstado.setText(null);
		  TextNumero.setText(null);
		  TextPais.setText(null);
		  TextCep.setText(null);
		  TextComplemento.setText(null);
		  TextBairro.setText(null);
	  }
  
	  private void SetVisible(boolean value) { this.setVisible(value); }
	  
	  void RefreshTelefones(){
		  DefaultListModel<String> telefones = new DefaultListModel<String>();
		  	
		  	TextTelefone.setText(null);
		  	for (Telefone telefone: selecionado.telefones){
        		telefones.addElement(telefone.Countrycode + 
						telefone.ddd + telefone.telefone);
				} listaDeTelefonesNaTela.setModel(telefones);
	  }
	  
	  void RefreshContatos(){
		  listaDeContatos = new DefaultListModel<String>();

		  for (int i = 0; i < Main.AGENDA.size(); i++) {
			  listaDeContatos.addElement(
					  Main.AGENDA.get(i).nome + " " + 
					  Main.AGENDA.get(i).sobrenome + ", " + 
					  Main.AGENDA.get(i).idade
			);
		} listaDeContatosNaTela.setModel(listaDeContatos);
  }
  
  void RefreshEnd(){
  	RefreshAddress();
  	
  	DefaultListModel<String> address = new DefaultListModel<String>();
  	
  	for (Address endereco: selecionado.address){
				address.addElement(endereco.rua + ", " +  endereco.numero);
			} listaDeAddressNaTela.setModel(address);
  }
  
  private void Editar(){
  	TextName.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextName.setEditable(true);
		   		TextName.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextName.getText() != null && Main.AGENDA.size() > 0){
	                    		selecionado.nome = TextName.getText();
	                    		TextName.setEditable(false);
	                    		RefreshContatos();
	                    	} else {
	                    		TextName.setText(null);
	                    		TextName.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextName.setText(null);
	                    	TextName.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextLastname.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextLastname.setEditable(true);
		   		TextLastname.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextLastname.getText() != null && Main.AGENDA.size() > 0){
	                    		selecionado.sobrenome = TextLastname.getText();
	                    		TextLastname.setEditable(false);
	                    		RefreshContatos();
	                    	} else {
	                    		TextLastname.setText(null);
	                    		TextLastname.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextLastname.setText(null);
	                    	TextLastname.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextLastname.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextLastname.setEditable(true);
		   		TextLastname.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextLastname.getText() != null && Main.AGENDA.size() > 0){
	                    		selecionado.sobrenome = TextLastname.getText();
	                    		TextLastname.setEditable(false);
	                    		RefreshContatos();
	                    	} else {
	                    		TextLastname.setText(null);
	                    		TextLastname.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextLastname.setText(null);
	                    	TextLastname.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextIdade.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextIdade.setEditable(true);
		   		TextIdade.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextIdade.getText() != null && Main.AGENDA.size() > 0){
	                    		selecionado.idade = TextIdade.getText();
	                    		TextIdade.setEditable(false);
	                    		RefreshContatos();
	                    	} else {
	                    		TextIdade.setText(null);
	                    		TextIdade.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextIdade.setText(null);
	                    	TextIdade.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextEmail.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextEmail.setEditable(true);
		   		TextEmail.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextEmail.getText() != null && Main.AGENDA.size() > 0){
	                    		selecionado.email = TextEmail.getText();
	                    		TextEmail.setEditable(false);
	                    		RefreshContatos();
	                    	} else {
	                    		TextEmail.setText(null);
	                    		TextEmail.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextEmail.setText(null);
	                    	TextEmail.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextTelefone.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextTelefone.setEditable(true);
		   		TextTelefone.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    		String[] split = TextTelefone.getText().split(" ");
                    		
	                    	if (TextTelefone.getText() != null && Main.AGENDA.size() > 0 && 
	                    			selecionado.telefones.size() > 0 && indexTelefones >= 0 && 
	                    			split.length == 3){
	                    		selecionado.telefones.get(indexTelefones).Countrycode = split[0] + " ";
	                    		selecionado.telefones.get(indexTelefones).ddd = split[1] + " ";
	                    		selecionado.telefones.get(indexTelefones).telefone = split[2];
	                    		TextTelefone.setEditable(false);
	                    		RefreshTelefones();
	                    	} else {
	                    		TextTelefone.setText(null);
	                    		TextTelefone.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextTelefone.setText(null);
	                    	TextTelefone.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextRua.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextRua.setEditable(true);
		   		TextRua.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextRua.getText() != null && Main.AGENDA.size() > 0 && 
	                    			selecionado.address.size() > 0 && indexAddress >= 0){
	                    		selecionado.address.get(indexAddress).rua = TextRua.getText();
	                    		TextRua.setEditable(false);
	                    		RefreshEnd();
	                    	} else {
	                    		TextRua.setText(null);
	                    		TextRua.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextRua.setText(null);
	                    	TextRua.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextNumero.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextNumero.setEditable(true);
		   		TextNumero.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextNumero.getText() != null && Main.AGENDA.size() > 0 && 
	                    			selecionado.address.size() > 0 && indexAddress >= 0){
	                    		selecionado.address.get(indexAddress).numero = TextNumero.getText();
	                    		TextNumero.setEditable(false);
	                    		RefreshEnd();
	                    	} else {
	                    		TextNumero.setText(null);
	                    		TextNumero.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextNumero.setText(null);
	                    	TextNumero.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextBairro.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextBairro.setEditable(true);
		   		TextBairro.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextBairro.getText() != null && Main.AGENDA.size() > 0 && 
	                    			selecionado.address.size() > 0 && indexAddress >= 0){
	                    		selecionado.address.get(indexAddress).bairro = TextBairro.getText();
	                    		TextBairro.setEditable(false);
	                    		RefreshEnd();
	                    	} else {
	                    		TextBairro.setText(null);
	                    		TextBairro.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextBairro.setText(null);
	                    	TextBairro.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextComplemento.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextComplemento.setEditable(true);
		   		TextComplemento.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextComplemento.getText() != null && Main.AGENDA.size() > 0 && 
	                    			selecionado.address.size() > 0 && indexAddress >= 0){
	                    		selecionado.address.get(indexAddress).complemento = TextComplemento.getText();
	                    		TextComplemento.setEditable(false);
	                    		RefreshEnd();
	                    	} else {
	                    		TextComplemento.setText(null);
	                    		TextComplemento.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextComplemento.setText(null);
	                    	TextComplemento.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextCidade.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextCidade.setEditable(true);
		   		TextCidade.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextCidade.getText() != null && Main.AGENDA.size() > 0 && 
	                    			selecionado.address.size() > 0 && indexAddress >= 0){
	                    		selecionado.address.get(indexAddress).cidade = TextCidade.getText();
	                    		TextCidade.setEditable(false);
	                    		RefreshEnd();
	                    	} else {
	                    		TextCidade.setText(null);
	                    		TextCidade.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextCidade.setText(null);
	                    	TextCidade.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextEstado.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextEstado.setEditable(true);
		   		TextEstado.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextEstado.getText() != null && Main.AGENDA.size() > 0 && 
	                    			selecionado.address.size() > 0 && indexAddress >= 0){
	                    		selecionado.address.get(indexAddress).estado = TextEstado.getText();
	                    		TextEstado.setEditable(false);
	                    		RefreshEnd();
	                    	} else {
	                    		TextEstado.setText(null);
	                    		TextEstado.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextEstado.setText(null);
	                    	TextEstado.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextCep.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextCep.setEditable(true);
		   		TextCep.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextCep.getText() != null && Main.AGENDA.size() > 0 && 
	                    			selecionado.address.size() > 0 && indexAddress >= 0){
	                    		selecionado.address.get(indexAddress).cep = TextCep.getText();
	                    		TextCep.setEditable(false);
	                    		RefreshEnd();
	                    	} else {
	                    		TextCep.setText(null);
	                    		TextCep.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextCep.setText(null);
	                    	TextCep.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  	TextPais.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		   	if (evt.getClickCount() == 2) {
		   		TextPais.setEditable(true);
		   		TextPais.addKeyListener(new KeyAdapter() {
	                public void keyPressed(KeyEvent evt) {
	                    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
	                    	if (TextPais.getText() != null && Main.AGENDA.size() > 0 && 
	                    			selecionado.address.size() > 0 && indexAddress >= 0){
	                    		selecionado.address.get(indexAddress).pais = TextPais.getText();
	                    		TextPais.setEditable(false);
	                    		RefreshEnd();
	                    	} else {
	                    		TextPais.setText(null);
	                    		TextPais.setEditable(false);
	                    	}
	                    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
	                    	TextPais.setText(null);
	                    	TextPais.setEditable(false);
	                    }
	                }
	    		});
		   	}
		}
	});
  }
  
  private javax.swing.JButton AddContato;
  private javax.swing.JButton AddEndereco;
  
  private javax.swing.JPanel PanelDesc;
  private javax.swing.JPanel PanelRoot;
  
  javax.swing.JList<String> listaDeAddressNaTela;
  javax.swing.JList<String> listaDeContatosNaTela;
  javax.swing.JList<String> listaDeTelefonesNaTela;
	
  private javax.swing.JScrollPane ScrollerAddress;
  private javax.swing.JScrollPane ScrollerContatos;
  private javax.swing.JScrollPane ScrollerTelefones;
	
  private javax.swing.JTextField TextBairro;
  private javax.swing.JTextField TextCep;
  private javax.swing.JTextField TextCidade;
  private javax.swing.JTextField TextComplemento;
  private javax.swing.JTextField TextEmail;
  private javax.swing.JTextField TextEstado;
  private javax.swing.JTextField TextIdade;
  private javax.swing.JTextField TextLastname;
  private javax.swing.JTextField TextName;
  private javax.swing.JTextField TextNumero;
  private javax.swing.JTextField TextPais;
  private javax.swing.JTextField TextRua;
  private javax.swing.JTextField TextTelefone;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel15;

  public static int index;
  public static int indexAddress;
  public static int indexTelefones;
  public Contato selecionado;
	
  public DefaultListModel<String> listaDeContatos;
}