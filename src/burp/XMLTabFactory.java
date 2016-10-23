package burp;

import java.io.PrintWriter;

public class XMLTabFactory implements IMessageEditorTabFactory {
	private IBurpExtenderCallbacks m_callbacks;
	private IExtensionHelpers m_helpers;
	private PrintWriter pw;

	public XMLTabFactory(IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers,  PrintWriter pw) {
		this.m_callbacks = callbacks;
		this.m_helpers = helpers;
		this.pw = pw;
	}

	@Override
	public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable) {
		XMLDeserializerTab amfDeserializerTab = new XMLDeserializerTab(controller, editable, m_callbacks, m_helpers, pw);
		return amfDeserializerTab;
	}

}
