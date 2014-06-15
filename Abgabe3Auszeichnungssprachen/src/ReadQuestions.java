import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadQuestions {

	private ArrayList<Questions> theQuestions = new ArrayList<>();

	public void readQst() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document daten = builder.parse("file:Fragen1.xml");

			// getElements(daten.getDocumentElement());

			NodeList nodes = daten.getElementsByTagName("frage");

			
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Questions question = new Questions();
				getElements(nodes.item(i),question);
				theQuestions.add(question);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void getElements(Node node, Questions question) {

		

		node.getTextContent();

		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);

			if (node.getNodeName().equals("fragetext")) {
				//System.out.println("FrageTest:    " + node.getTextContent());
				question.setQuestion(node.getTextContent());
			}

			if (child.getNodeType() == Node.ELEMENT_NODE) {
				getElements((Element) child, question);
			}
		}

		NamedNodeMap attrs = node.getAttributes();

		for (int i = 0; i < attrs.getLength(); i++) {
			Node attr = attrs.item(i);
			String name = attr.getNodeName();
			String value = attr.getNodeValue();

			if (node.getNodeName().equals("frage")) {
				//System.out.println("Themengebiet: " + value);
				question.setTopic(value);
			}

			if (node.getNodeName().equals("antwort")) {
				//System.out.println("Antwort  " + node.getTextContent() + " " + value);
				question.addOption(node.getTextContent());
				if (value.equals("ja")) {
					question.setAnswer(node.getTextContent());
				}
			}

		}
		
		

	}

	public ArrayList<Questions> getQuestions() {
		return theQuestions;
	}

}
