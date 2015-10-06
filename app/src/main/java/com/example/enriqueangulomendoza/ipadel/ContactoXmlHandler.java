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
 * Created by enriqueangulomendoza on 5/10/15.
 */
public class ContactoXmlHandler {

    public List<Contacto> parse(FileInputStream xmlFile)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        List<Contacto> contactos = new ArrayList<Contacto>();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(xmlFile);

            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("table");

            for (int i=0; i<items.getLength(); i++)
            {
                Contacto contacto = new Contacto();

                Node item = items.item(i);
                NodeList datosContacto = item.getChildNodes();

                for (int j=0; j<datosContacto.getLength(); j++)
                {
                    Node dato = datosContacto.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("column") && j==1)
                    {
                        String texto = obtenerTexto(dato);
                        contacto.setIdContacto(texto);
                    }
                    else if (etiqueta.equals("column") && j==3)
                    {
                        String texto = obtenerTexto(dato);
                        contacto.setIdClub(texto);
                    }
                    else if (etiqueta.equals("column") && j==5)
                    {
                        String texto = obtenerTexto(dato);
                        contacto.setCargo(texto);
                    }
                    else if (etiqueta.equals("column") && j==7)
                    {
                        String texto = obtenerTexto(dato);
                        contacto.setNombre(texto);
                    }
                    else if (etiqueta.equals("column") && j==9)
                    {
                        String texto = obtenerTexto(dato);
                        contacto.setEmail(texto);
                    }
                    else if (etiqueta.equals("column") && j==11)
                    {
                        String texto = obtenerTexto(dato);
                        contacto.setMovil(texto);
                    }
                }

                contactos.add(contacto);
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

        return contactos;
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
