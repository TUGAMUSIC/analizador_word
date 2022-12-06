/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package palabra_analizador;

/**
 *
 * @author BOLIS INC.
 */
public class TokenCode {
    String Token; 
    int code; 
    String descripcion; 
    int codeCat;

    public int getCodeCat() {
        return codeCat;
    }

    public void setCodeCat(int codeCat) {
        this.codeCat = codeCat;
    }

    public TokenCode(String Token, int code, String descripcion, int codecat) {
        this.Token = Token;
        this.code = code;
        this.descripcion = descripcion;
        this.codeCat = codecat; 
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TokenCode{" + "Token=" + Token + ", code=" + code + ", descripcion=" + descripcion +", categoria=" + codeCat + '}';
    }
    
    
    
    
    
}
