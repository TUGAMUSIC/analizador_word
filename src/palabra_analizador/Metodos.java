/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palabra_analizador;

import java.util.ArrayList;

/**
 *
 * @author BOLIS INC.
 */
public class Metodos {
    
    int index = 0;
    public ArrayList<String> TokenList;
    boolean sinError = true;

    public Metodos() {
        this.TokenList = new ArrayList<String>();
    }
    
    public boolean inicio(){
        //Nomb
        if(TokenList.get(index).equals("N")){
            index++;
            if(TokenList.get(index).equals("o")){
                index++;
            if(TokenList.get(index).equals("m")){
                index++;
            if(TokenList.get(index).equals("b")){
                index++;
        //
            if(TokenList.get(index).equals(" ")){
                index++;
                if(TokenList.get(index).equals("*")){
                    index++;
                    if(TokenList.get(index).equals(identificador())){
                        index++;
                    }
                if(TokenList.get(index).equals("*")){
                    index++;
                    if(TokenList.get(index).equals('\n')){
                        index++;
                        //Inicio
                        if(TokenList.get(index).equals("I")){
                            index++;
                            if(TokenList.get(index).equals("n")){
                                index++;
                            if(TokenList.get(index).equals("i")){
                                index++;
                            if(TokenList.get(index).equals("c")){
                                index++;
                            if(TokenList.get(index).equals("i")){
                                index++;
                            if(TokenList.get(index).equals("o")){
                                index++;
                            if(TokenList.get(index).equals('\n')){
                                index++;
                        //
                                //vls
                                if(TokenList.get(index).equals("v")){
                                    index++;
                                if(TokenList.get(index).equals("l")){
                                    index++;
                                if(TokenList.get(index).equals("s")){
                                    index++;
                                //
                                    variables();
                                    codigo();
                                    //Salir
                                    if(TokenList.get(index).equals("S")){
                                        index++;
                                    if(TokenList.get(index).equals("a")){
                                        index++;
                                    if(TokenList.get(index).equals("l")){
                                        index++;
                                    if(TokenList.get(index).equals("i")){
                                        index++;
                                    if(TokenList.get(index).equals("r")){
                                        return sinError;
                                    }}}}}
                                    //
                                }}}
                        }}}}}}}
                    }
                }
                }
            }
        }}}}
        return false;
    }
    
    public boolean identificador(){
        String letras[] = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m",
                           "n","ñ","o","p","q","r","s","t","u","v","w","x","y","z",
                           "A","B","C","D","E","F","G","H","I","J","K","L","M",
                           "N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        if(TokenList.get(index).equals(letras)){
            index++;           
        }else{
            return false;
        }
        return sinError;
    }
    
    public boolean variables(){
        if(TokenList.get(index).equals('\n')){
            index++;
            if(TokenList.get(index).equals(tipo())){
                index++;
                if(TokenList.get(index).equals(" ")){
                    index++;
                    if(TokenList.get(index).equals("'")){
                        index++;
                        while(TokenList.get(index).equals(identificador())){
                            index++;
                        }index++;
                        if(TokenList.get(index).equals("=")){
                            index++;
                            while(TokenList.get(index).equals(num()||identificador())){
                                index++;
                            }index++;
                        }
                        if(TokenList.get(index).equals("'")){
                            index++;
                            return sinError;
                        }
                    }
                }
            }
        }
        return false;
    }
        
    public boolean tipo(){
        //ent
        if(TokenList.get(index).equals("e")){
            index++;
            if(TokenList.get(index).equals("n")){
                index++;
            if(TokenList.get(index).equals("t")){
                return sinError;
        //txt
        }}}else if(TokenList.get(index).equals("t")){
            index++;
            if(TokenList.get(index).equals("x")){
                index++;
            if(TokenList.get(index).equals("t")){
                return sinError;
        //dcm
        }}}else if(TokenList.get(index).equals("d")){
            index++;
            if(TokenList.get(index).equals("c")){
                index++;
            if(TokenList.get(index).equals("m")){
                return sinError;
        //cts
        }}}else if(TokenList.get(index).equals("c")){
            index++;
            if(TokenList.get(index).equals("t")){
                index++;
            if(TokenList.get(index).equals("s")){
                return sinError;
        }}}
        return false;
    }
    
    public boolean num(){
        int numeros[] = new int[]{1,2,3,4,5,6,7,8,9,0};
        if(TokenList.get(index).equals(numeros)){
            return sinError;
        }
        return false;
    }
    
    public boolean codigo(){
        if(TokenList.get(index).equals('\n')){
            index++;
            //si
            if(TokenList.get(index).equals("s")){
                index++;
                if(TokenList.get(index).equals("i")){
                    index++;
                    si();
            //repite
            }}else if(TokenList.get(index).equals("r")){
                index++;
                if(TokenList.get(index).equals("e")){
                    index++;
                if(TokenList.get(index).equals("p")){
                    index++;
                if(TokenList.get(index).equals("i")){
                    index++;
                if(TokenList.get(index).equals("t")){
                    index++;
                if(TokenList.get(index).equals("e")){
                    index++;
                    repite();
            //mientras
            }}}}}}else if(TokenList.get(index).equals("m")){
                index++;
                if(TokenList.get(index).equals("i")){
                    index++;
                if(TokenList.get(index).equals("e")){
                    index++;
                if(TokenList.get(index).equals("n")){
                    index++;
                if(TokenList.get(index).equals("t")){
                    index++;
                if(TokenList.get(index).equals("r")){
                    index++;
                if(TokenList.get(index).equals("a")){
                    index++;
                if(TokenList.get(index).equals("s")){
                    index++;
                    mientras();
            }}}}}}}}else if(TokenList.get(index).equals('\n')){
                index++;
                return sinError;
            }else if(TokenList.get(index).equals(" ")){
                index++;
                return sinError;
            }
        }
        return false;
    }
    
    public boolean si(){
        if(TokenList.get(index).equals(" ")){
            index++;
            condicion();
            if(TokenList.get(index).equals('\n')){
                codigo();
                return sinError;
            }
            
        }
        return false;
    }
    
    public boolean repite(){
        if(TokenList.get(index).equals(var_id())){
            index++;
            if(TokenList.get(index).equals(" ")){
                index++;
                condicion();
                if(TokenList.get(index).equals(" ")){
                    index++;
                    if(TokenList.get(index).equals('[')){
                        index++;
                        codigo();
                        if(TokenList.get(index).equals(']')){
                            index++;
                            return sinError;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean var_id(){
        if(TokenList.get(index).equals("+")|| TokenList.get(index).equals("-")){
            return sinError;
        }
        return false;
    }
    
    public boolean mientras(){
        if(TokenList.get(index).equals(" ")){
            index++;
            condicion();
            if(TokenList.get(index).equals(" ")){
                index++;
                if(TokenList.get(index).equals('[')){
                    index++;
                    codigo();
                    if(TokenList.get(index).equals(']')){
                        index++;
                        return sinError;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean condicion(){
        if(TokenList.get(index).equals(identificador()||num())){
            index++;
            if(TokenList.get(index).equals(con_valor())){
                index++;
                if(TokenList.get(index).equals(identificador()||num())){
                    index++;
                    return sinError;
                }
            }
        }
        return false;
    }
    
    public boolean con_valor(){
        // < >
        if(TokenList.get(index).equals("<")|| TokenList.get(index).equals(">")){
            return sinError;
        // <= >= == !=
        }else if(TokenList.get(index).equals("<")|| TokenList.get(index).equals(">")||
                 TokenList.get(index).equals("=")|| TokenList.get(index).equals("!")){
            index++;
            if(TokenList.get(index).equals("=")){
                return sinError;
            }
        }
        return false;
    }
    
}