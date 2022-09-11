/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoprogramacionii;

import com.mongodb.DBObject;
import com.mongodb.MongoClientException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Toshiba
 */
public class ConexionDB {
    MongoClient conn;
    String servidor="localhost";
    int puerto = 27017;
    
    MongoDatabase dataBaseSelect;
    
    MongoCollection <Document> Registro;
    public ConexionDB(){
        try{
            this.conn = MongoClients.create("mongodb://"+servidor+":"+ puerto);
            System.out.println("Conexion Exitosa");
        } catch (MongoClientException error){
            System.out.println("Error en conexion; "+error.toString());
        }
    }
    public void mostrarDB(){
        dataBaseSelect = conn.getDatabase("Mongodb");
        System.out.println("DB Seleccionada"+dataBaseSelect.toString());
    }
    public boolean setRegistro(DBObject newRegistro){
        try{
         this.Registro.insertOne((Document) newRegistro);   
            JOptionPane.showMessageDialog(null, "Registro creado con exito!","Importante", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch(MongoClientException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public FindIterable<Document> getRegistros(){
        FindIterable<Document> iterable = null;
        try{
            iterable = this.Registro.find();
        } catch(MongoClientException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante!",JOptionPane.ERROR_MESSAGE);
        }
        return iterable;
    }
    public FindIterable<Document> getRegistrosInsertado(){
    FindIterable<Document> iterable = null;
    try{
        iterable = this.Registro.find();
    } catch(MongoClientException error){
        JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante!",JOptionPane.ERROR_MESSAGE);
    }
      return iterable;
    }
    public boolean deleteRegistros(String id){
        try{
        Bson filter = eq("nombre", id);
        Document doc = this.Registro.findOneAndDelete(filter);
        return true;
        } catch(MongoClientException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public boolean actualizarRegistros(Document data, String id){
        try{
            Bson filter = eq("_id", id);
            Bson updateOperation = set("nombre",data.getString("nombre"));
            UpdateResult updateResult = this.Registro.updateOne(filter, updateOperation);
            System.out.println("=> Updating the doc with {\"student_id\":10000}. Adding comment.");
            System.out.println(updateResult);
            return true;            
        } catch(MongoClientException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
