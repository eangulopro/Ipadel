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
public class ClubXmlHandler {

    public List<Club> parse(FileInputStream xmlFile)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        List<Club> clubs = new ArrayList<Club>();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(xmlFile);

            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("table");

            for (int i=0; i<items.getLength(); i++)
            {
                Club club = new Club();

                Node item = items.item(i);
                NodeList datosClub = item.getChildNodes();

                for (int j=0; j<datosClub.getLength(); j++)
                {
                    Node dato = datosClub.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("column") && j==1)
                    {
                        String texto = obtenerTexto(dato);
                        club.setIdClub(texto);
                    }
                    else if (etiqueta.equals("column") && j==3)
                    {
                        String texto = obtenerTexto(dato);
                        club.setNombre(texto);
                    }
                    else if (etiqueta.equals("column") && j==5)
                    {
                        String texto = obtenerTexto(dato);
                        club.setDireccion(texto);
                    }
                    else if (etiqueta.equals("column") && j==7)
                    {
                        String texto = obtenerTexto(dato);
                        club.setCP(texto);
                    }
                    else if (etiqueta.equals("column") && j==9)
                    {
                        String texto = obtenerTexto(dato);
                        club.setIdPoblacion(texto);
                    }
                    else if (etiqueta.equals("column") && j==11)
                    {
                        String texto = obtenerTexto(dato);
                        club.setIdProvincia(texto);
                    }
                    else if (etiqueta.equals("column") && j==13)
                    {
                        String texto = obtenerTexto(dato);
                        club.setIdPais(texto);
                    }
                    else if (etiqueta.equals("column") && j==15)
                    {
                        String texto = obtenerTexto(dato);
                        club.setTelefono(texto);
                    }
                    else if (etiqueta.equals("column") && j==17)
                    {
                        String texto = obtenerTexto(dato);
                        club.setWeb(texto);
                    }
                    else if (etiqueta.equals("column") && j==19)
                    {
                        String texto = obtenerTexto(dato);
                        club.setFacebook(texto);
                    }
                    else if (etiqueta.equals("column") && j==21)
                    {
                        String texto = obtenerTexto(dato);
                        club.setTwitter(texto);
                    }
                    else if (etiqueta.equals("column") && j==23)
                    {
                        String texto = obtenerTexto(dato);
                        club.setPistas(texto);
                    }
                    else if (etiqueta.equals("column") && j==25)
                    {
                        String texto = obtenerTexto(dato);
                        club.setObservaciones(texto);
                    }
                    else if (etiqueta.equals("column") && j==27)
                    {
                        String texto = obtenerTexto(dato);
                        club.setEmail(texto);
                    }
                    else if (etiqueta.equals("column") && j==29)
                    {
                        String texto = obtenerTexto(dato);
                        club.setLogo(texto);
                    }
                    else if (etiqueta.equals("column") && j==31)
                    {
                        String texto = obtenerTexto(dato);
                        club.setVer_logo(texto);
                    }
                    else if (etiqueta.equals("column") && j==51)
                    {
                        String texto = obtenerTexto(dato);
                        club.setEstrellas(texto);
                    }
                }

                clubs.add(club);
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

        return clubs;
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
