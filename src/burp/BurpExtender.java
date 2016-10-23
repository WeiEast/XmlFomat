package burp;

import java.io.PrintWriter;


public class BurpExtender implements IBurpExtender, IMessageEditorTabFactory
{
    private IBurpExtenderCallbacks m_callbacks;
    private IExtensionHelpers m_helpers;
    private PrintWriter pw;

    //
    // implement IBurpExtender
    //
    
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        // keep a reference to our callbacks object
        this.m_callbacks = callbacks;
        
        // obtain an extension helpers object
        m_helpers = callbacks.getHelpers();
        
        // set our extension name
        callbacks.setExtensionName("XML ");
        pw = new PrintWriter(callbacks.getStdout());
        
        // register ourselves as a message editor tab factory
        XMLTabFactory factory = new XMLTabFactory(m_callbacks, m_helpers, pw );

        callbacks.registerMessageEditorTabFactory(factory);
        
        callbacks.registerContextMenuFactory(new XMLMenu(callbacks));
        
        callbacks.registerHttpListener(new XMLHttpListener());
    }

    //
    // implement IMessageEditorTabFactory
    //
    
    @Override
    public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable)
    {
        // create a new instance of our custom editor tab
        return new XMLDeserializerTab(controller, editable, m_callbacks, m_helpers, pw);
    }

   

   
}
