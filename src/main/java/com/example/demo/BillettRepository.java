package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillettRepository {

    //henter knyttningen til databasen
    @Autowired
    private JdbcTemplate db;

    public void lagreBillett (Billett innBillett){
        String sql = "INSERT INTO Billett (film, antall, fornavn, etternavn, telefonnr, epost) VALUES(?, ?, ?, ?, ?, ?)";

        //parametere som skal inn i values:
        db.update(sql, innBillett.getFilm(), innBillett.getAntall(), innBillett.getFornavn(), innBillett.getEtternavn(), innBillett.getTelefonnr(), innBillett.getEpost());
    }

    public List<Billett> hentAlleBilletter(){
        String sql = "SELECT * FROM Billett";
        //Mapper blir ett objekt vi kan kalle på, som mapper kolonnene i klassen (attributtene må stemme med kolonnenavnene)
        List<Billett> alleBilletter = db.query(sql, new BeanPropertyRowMapper(Billett.class));
        return alleBilletter;
    }

    public void slettAlleBilletter (){
        String sql  = "DELETE FROM Billett";
        db.update(sql);
    }
}
