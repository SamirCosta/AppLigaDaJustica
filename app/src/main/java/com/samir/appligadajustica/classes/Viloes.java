package com.samir.appligadajustica.classes;

import java.util.Date;

public class Viloes extends Personagens {
    public String HeroisRivais;
    public String Esconderijos;
    public Date DataAtaques;

    public String getHeroisRivais() {
        return HeroisRivais;
    }

    public void setHeroisRivais(String heroisRivais) {
        HeroisRivais = heroisRivais;
    }

    public String getEsconderijos() {
        return Esconderijos;
    }

    public void setEsconderijos(String esconderijos) {
        Esconderijos = esconderijos;
    }

    public Date getDataAtaques() {
        return DataAtaques;
    }

    public void setDataAtaques(Date dataAtaques) {
        DataAtaques = dataAtaques;
    }
}
