package com.kthsoft.zkframework.composer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class LoginComposer extends SelectorComposer<Component> {
	private static final long serialVersionUID = -5943754765598298745L;
	
	private static final String DETAULE_USERNAME = "username";
	private static final String DEFAULT_PASSWORD = "password";
	
	@Wire
	private Textbox usernameTxb;
	@Wire
	private Textbox passwordTxb;
	@Wire
	private Label messageLbl;
	@Wire
	private Button loginBtn;

	@Listen("onClick = #loginBtn")
	public void login() {
		String username = usernameTxb.getText();
		String password = passwordTxb.getText();
		
		if (DETAULE_USERNAME.equals(username) && DEFAULT_PASSWORD.equals(password)) {
			Executions.sendRedirect("index.zul");
		} else {
			messageLbl.setValue("Login failed. Either username or password is incorrect!");
			Clients.evalJavaScript("jq(\"$errDiv\").fadeIn(500)");
		}
	}
}
