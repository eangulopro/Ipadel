package com.example.enriqueangulomendoza.ipadel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by enriqueangulomendoza on 4/10/15.
 */
public class ComunidadXmlHandler {

    public List<Comunidad> parse(FileInputStream xmlFile)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        List<Comunidad> comunidades = new ArrayList<Comunidad>();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(xmlFile);

            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("table");

            for (int i=0; i<items.getLength(); i++)
            {
                Comunidad comunidad = new Comunidad();

                Node item = items.item(i);
                NodeList datosComunidad = item.getChildNodes();

                for (int j=0; j<datosComunidad.getLength(); j++)
                {
                    Node dato = datosComunidad.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("column") && j==1)
                    {
                        String texto = obtenerTexto(dato);
                        comunidad.setIdComunidad(texto);
                    }
                    else if(etiqueta.equals("column") && j == 3)
                    {
                        String texto = obtenerTexto(dato);
                        comunidad.setIdPais(texto);
                    }
                    else if (etiqueta.equals("column") && j==5)
                    {
                        String texto = obtenerTexto(dato);
                        comunidad.setComunidad(texto);
                    }
                }

                comunidades.add(comunidad);
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

        return comunidades;
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
