package burp;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JMenuItem;

import flex.messaging.io.ArrayList;

public class XMLMenu implements IContextMenuFactory {
	private IBurpExtenderCallbacks m_callbacks;

	public XMLMenu(IBurpExtenderCallbacks callbacks) {
		m_callbacks = callbacks;
	}

	@Override
	public List<JMenuItem> createMenuItems(final IContextMenuInvocation invocation) {
		JMenuItem sendXMLToIntruderMenu = new JMenuItem("Send Deserialized XML to Intruder");
		JMenuItem scanXMLMenu = new JMenuItem("Scan XML with predefined insertion points");
		sendXMLToIntruderMenu.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("Menu clicked");
				IHttpRequestResponse[] selectedMessages = invocation.getSelectedMessages();
				for (IHttpRequestResponse iReqResp : selectedMessages) {
					IHttpService httpService = iReqResp.getHttpService();
					m_callbacks.sendToIntruder(httpService.getHost(), httpService.getPort(), (httpService.getProtocol().equals("https") ? true : false),
							XMLUtilities.deserializeProxyItem(iReqResp.getRequest()));
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});

		scanXMLMenu.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				IHttpRequestResponse[] selectedMessages = invocation.getSelectedMessages();
				for (IHttpRequestResponse iReqResp : selectedMessages) {
					IHttpService httpService = iReqResp.getHttpService();
					m_callbacks.doActiveScan(httpService.getHost(), httpService.getPort(), (httpService.getProtocol().equals("https") ? true : false),
							XMLUtilities.serializeProxyItem(iReqResp.getRequest()));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

		});
		List<JMenuItem> menus = new ArrayList();
		menus.add(sendXMLToIntruderMenu);
		menus.add(scanXMLMenu);
		return menus;
	}

}
