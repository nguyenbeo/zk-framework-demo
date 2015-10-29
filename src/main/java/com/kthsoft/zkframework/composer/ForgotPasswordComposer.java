package com.kthsoft.zkframework.composer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class ForgotPasswordComposer extends SelectorComposer<Component> {
	private static final long serialVersionUID = 3409241096209851825L;
	private static final String DEFAULT_EMAIL = "test@test.com";

	@Wire
	private Textbox emailTxb;
	@Wire
	private Label messageLbl;
	@Wire
	private Button confirmEmailBtn;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        confirmEmailBtn.setDisabled(true);
	}
	
	@Listen("onClick = #confirmEmailBtn")
	public void changePassword() {
		String email = emailTxb.getText();
		
		if (DEFAULT_EMAIL.equals(email)) {
			Messagebox.show("Password has been changed and sent to your email", 
					"Password changed", Messagebox.OK, Messagebox.INFORMATION, evt -> {
						 Executions.sendRedirect("login.zul");
					 }
			);
		} else {
			Messagebox.show("Email could not be found", 
					"Error occured", Messagebox.RETRY, Messagebox.ERROR);
		}
	}
	
	@Listen("onChange = #emailTxb")
	public void enableConfirmEmailButton() {
		confirmEmailBtn.setDisabled(false);
	}
}
