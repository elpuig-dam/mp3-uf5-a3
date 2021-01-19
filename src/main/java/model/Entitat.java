package model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entitat")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Entitat {
    private String nom, tipus, poblacio, comarca;
    private String classificacioGeneral, classificacioEspecifica;
    private String dataInscripcio;

}
