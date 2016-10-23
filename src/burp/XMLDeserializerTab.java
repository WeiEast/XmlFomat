package burp;
import java.awt.Component;
import java.io.PrintWriter;


public class XMLDeserializerTab implements IMessageEditorTab{
	
	private boolean editable;
	private ITextEditor txtInput;
	private byte[] currentMessage;
	private IBurpExtenderCallbacks callbacks;
	private IExtensionHelpers helpers;
	private PrintWriter pw;

	public XMLDeserializerTab(IMessageEditorController controller, boolean editable, IBurpExtenderCallbacks callbacks2, IExtensionHelpers helpers2, PrintWriter pw) {
		this.editable = editable;
		callbacks = callbacks2;
		helpers = helpers2;
		// create an instance of Burp's text editor, to display our deserialized
		// data
		txtInput = callbacks.createTextEditor();
		txtInput.setEditable(editable);
		this.pw = pw;
	}

	//
	// implement IMessageEditorTab
	//

	@Override
	public String getTabCaption() {
		return "XML Deserialized";
	}

	@Override
	public Component getUiComponent() {
		return txtInput.getComponent();
	}

	@Override
	public boolean isEnabled(byte[] content, boolean isRequest) {
		// enable this tab for requests containing a data parameter

		return true;
	}

	@Override
	public void setMessage(byte[] content, boolean isRequest) {
		if (content == null) {
			// clear our display
			txtInput.setText(null);
			txtInput.setEditable(false);
		} else {
			pw.println(new String(content));
			// retrieve the data parameter
			txtInput.setText(XMLUtilities.serializeProxyItem(content));
			
			txtInput.setEditable(editable);
		}

		// remember the displayed content
		currentMessage = content;
	}

	@Override
	public byte[] getMessage() {
		// determine whether the user modified the deserialized data
		if (txtInput.isTextModified()) {
			// reserialize the data
			return XMLUtilities.serializeProxyItem(txtInput.getText());
//			return XMLUtilities.serializeProxyItem(txtInput.getText());
//			return currentMessage;
			// helpers.buildParameter("data", input, IParameter.PARAM_BODY));
		} else
			return currentMessage;
	}

	@Override
	public boolean isModified() {
		return txtInput.isTextModified();
	}

	@Override
	public byte[] getSelectedData() {
		return txtInput.getSelectedText();
	}
}