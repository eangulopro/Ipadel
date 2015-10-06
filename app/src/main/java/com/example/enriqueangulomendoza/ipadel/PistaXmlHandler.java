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
public class PistaXmlHandler {

    public List<Pista> parse(FileInputStream xmlFile)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        List<Pista> pistas = new ArrayList<Pista>();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(xmlFile);

            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("table");

            for (int i=0; i<items.getLength(); i++)
            {
                Pista pista = new Pista();

                Node item = items.item(i);
                NodeList datosPista = item.getChildNodes();

                for (int j=0; j<datosPista.getLength(); j++)
                {
                    Node dato = datosPista.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("column") && j==1)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setIdClub(texto);
                    }
                    else if (etiqueta.equals("column") && j==3)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setMuro_ext(texto);
                    }
                    else if (etiqueta.equals("column") && j==5)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setMuro_ind(texto);
                    }
                    else if (etiqueta.equals("column") && j==7)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setCristal_ext(texto);
                    }
                    else if (etiqueta.equals("column") && j==9)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setCristal_ind(texto);
                    }
                    else if (etiqueta.equals("column") && j==11)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setMixta(texto);
                    }
                    else if (etiqueta.equals("column") && j==13)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setPista_central(texto);
                    }
                    else if (etiqueta.equals("column") && j==15)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setIndividual_ext(texto);
                    }
                    else if (etiqueta.equals("column") && j==17)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setIndividual_ind(texto);
                    }
                    else if (etiqueta.equals("column") && j==19)
                    {
                        String texto = obtenerTexto(dato);
                        pista.setTotal(texto);
                    }
                }

                pistas.add(pista);
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

        return pistas;
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
