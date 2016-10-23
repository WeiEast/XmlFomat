package burp;

public class XMLHttpListener implements IHttpListener {

	@Override
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		if (messageIsRequest) {
			if (toolFlag == IBurpExtenderCallbacks.TOOL_INTRUDER || toolFlag == IBurpExtenderCallbacks.TOOL_SCANNER) {
				messageInfo.setRequest(messageInfo.getRequest());
			}
		}
	}
}