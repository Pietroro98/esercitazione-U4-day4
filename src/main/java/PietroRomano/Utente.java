package PietroRomano;

import java.time.LocalDate;
import java.time.Period;

public class Utente {
    private String nome;
    private String surname;
    private LocalDate dataDiNascita;
    private String luogoDiNascita;

    public Utente(String nome, LocalDate dataDiNascita, String luogoDiNascita) {
        this.nome = nome;
        this.surname = surname;
        this.dataDiNascita = dataDiNascita;
        this.luogoDiNascita = luogoDiNascita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }

    public int getAge() {
        return Period.between(this.dataDiNascita, LocalDate.now()).getYears();
    }


    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", surname='" + surname + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", luogoDiNascita='" + luogoDiNascita + '\'' +
                '}';
    }
}


