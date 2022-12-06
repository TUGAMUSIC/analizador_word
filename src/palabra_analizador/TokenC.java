/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package palabra_analizador;

/**
 *
 * @author Edson Rojo
 */
public class TokenC {

    private String variable;
    private String operador;
    private String numero;
    private String proceso;
    private String ejecucion;

    public TokenC(String variable, String operador, String numero, String proceso, String ejecucion) {
        this.variable = variable;
        this.operador = operador;
        this.numero = numero;
        this.proceso = proceso;
        this.ejecucion = ejecucion;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(String ejecucion) {
        this.ejecucion = ejecucion;
    }

    @Override
    public String toString() {
        return "TokenC{" + "variable=" + variable + ", operador=" + operador + ", numero=" + numero + ", proceso=" + proceso + ", ejecucion=" + ejecucion + '}';
    }

}
