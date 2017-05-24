package com.eju.ess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyTest2 {
	@Test
	public void test1() {

	}

	@Test
	public void test2() {
//		InputStream resouceStream = this.getClass().getClassLoader().getResourceAsStream("D:\\ehousechina\\TSD\\ESS\\Activiti\\activiti-example-05\\src\\main\\resources\\processes\\demo.bpmn");
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in;
		XMLStreamReader xtr;
		try {
			in = new InputStreamReader(new FileInputStream("D:\\ehousechina\\TSD\\ESS\\Activiti\\activiti-example-05\\src\\main\\resources\\processes\\demo.bpmn"), "UTF-8");
			xtr = xif.createXMLStreamReader(in);
			BpmnModel model = new BpmnXMLConverter().convertToBpmnModel(xtr);
			model.getProcessById("");
			/*Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
			for (FlowElement e : flowElements) {
				System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:"+ e.getClass().toString());
			}*/
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
