package com.example.enriqueangulomendoza.ipadel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by enriqueangulomendoza on 4/10/15.
 */
public class PaisXmlHandler {


    public List<Pais> parse(FileInputStream xmlFile)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        List<Pais> paises = new ArrayList<Pais>();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(xmlFile);

            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("table");

            for (int i=0; i<items.getLength(); i++)
            {
                Pais pais = new Pais();

                Node item = items.item(i);
                NodeList datosPais = item.getChildNodes();

                for (int j=0; j<datosPais.getLength(); j++)
                {
                    Node dato = datosPais.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("column") && j==1)
                    {
                        String texto = obtenerTexto(dato);
                        pais.setIdPais(texto);
                    }
                    else if (etiqueta.equals("column") && j==3)
                    {
                        String texto = obtenerTexto(dato);
                        pais.setPais(texto);

                    }
                }

                paises.add(pais);
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

        return paises;
    }

    private String obtenerTexto(Node dato)
    {
        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();

        for (int k=0;k<fragmentos.getLength();k++)
        {
            texto.append(fragmentos.item(k).getNodeValue());
        }

        return texto.toString();
    }
}