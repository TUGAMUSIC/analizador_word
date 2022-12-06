/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package palabra_analizador;

/**
 *
 * @author BOLIS INC.
 */
public class TokenB {
    String token; 
    int linea; 
    int columna; 
    int codigo; 
    String info; 

    public TokenB(String token, int linea, int columna, int codigo, String info) {
        this.token = token;
        this.linea = linea;
        this.columna = columna;
        this.codigo = codigo;
        this.info = info; 
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Token{" + "token=" + token + ", linea=" + linea + ", columna=" + columna + ", codigo=" + codigo + '}';
    }
    
    
    
    
    
    
    
}
