package net.mefmor.demo.soap;

import javax.xml.soap.*;
import java.io.IOException;

public class SoapClientApplication {
    public static void main(String[] args) throws SOAPException, IOException {
        SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = factory.createConnection();
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();
        SOAPPart part = message.getSOAPPart();
        SOAPEnvelope envelope = part.getEnvelope();
        SOAPBody body = envelope.getBody();
        Name bodyName = envelope.createName("GetLastTradePrice", "", "http://mefmor.net");
        SOAPBodyElement gltp = body.addBodyElement(bodyName);
        Name content = envelope.createName("symbol");
        SOAPElement symbol = gltp.addChildElement(content);
        symbol.addTextNode("SUNW");
        message.saveChanges();
        message.writeTo(System.out);
        SOAPMessage response = connection.call(message, "http://example.com");
        connection.close();
        System.out.println(response.getSOAPBody().getValue());

    }
}
