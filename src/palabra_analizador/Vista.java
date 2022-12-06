/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palabra_analizador;

import java.util.ArrayList;

/**
 *
 * @author Rojo, Campos, Carbajal
 */
public class Vista extends javax.swing.JFrame {

    ArrayList<TokenCode> base = new ArrayList<>();

    public Vista() {
        initComponents();
        // verbos
        base.add(new TokenCode("ayuda", 101, "Verbo", 11));
        base.add(new TokenCode("juega", 102, "Verbo", 11));
        base.add(new TokenCode("expresa", 103, "Verbo", 11));
        base.add(new TokenCode("ejecuta", 104, "Verbo", 11));
        base.add(new TokenCode("escucha", 105, "Verbo", 12));
        base.add(new TokenCode("espera", 106, "Verbo", 12));
        base.add(new TokenCode("tomo", 107, "Verbo", 13));
        // conectores
        base.add(new TokenCode("en", 211, "Conector", 24));
        base.add(new TokenCode("su", 212, "Conector",24 ));
        base.add(new TokenCode("y", 21, "Conector", 21));
        base.add(new TokenCode("o", 21, "Conector", 21));
        base.add(new TokenCode("u", 21, "Conectar", 22));
        // sujetxs
        base.add(new TokenCode("el", 301, "Sujeto", 31));
        base.add(new TokenCode("ella", 302, "Sujeto", 32));
        base.add(new TokenCode("ellos", 30, "Sujeto", 33));
        base.add(new TokenCode("ellas", 31, "Sujeto", 34));
        // objetos
        base.add(new TokenCode("coche", 501, "Objeto", 53));
        base.add(new TokenCode("audifonos", 502, "Objeto", 52));
        base.add(new TokenCode("usb", 503, "Objeto", 55));
        base.add(new TokenCode("televisor", 504, "Objeto", 54));
        base.add(new TokenCode("sillon", 505, "Objeto", 56));
        base.add(new TokenCode("radio", 506, "Objeto", 52));
        // sustantivo
        
        
        
        
        
    }
    //variables  

    private String var = "", ope = "", num = "", txt = "", aux = "", auxNumero = "";
    private int col = 1, fil = 1, col_aux = 0, cont = 0, isSTART = 0, isError = 0, op = 0, lexer = 0, c = 0, lexer2 = 0;
    private char u;
    private boolean v = false;

    //arreglos
    private String Reservadas1Lapse[] = {
        
    };
    private String Tkns1Lapse[] = {"="};
    private ArrayList<TokenB> LTkns = new ArrayList<TokenB>();
    private ArrayList<TokenC> STkns = new ArrayList<TokenC>();
    private boolean contador = false;

    // metodos
    private void R_find(String a, int col, int fil) {
        for (TokenCode b : base) {
            if (b.getToken().equals(a)) {
                if (a.equals("<html>")) {
                    LTkns.add(new TokenB(" <html> ", col, fil, b.getCode(), b.getDescripcion()));
                    v = true;
                } else {
                    LTkns.add(new TokenB(b.getToken(), col, fil, b.getCode(), b.getDescripcion()));
                    v = true;
                }
            }
        }

        if (v != true) {
            if (aux.length() > 1) {
                String n = "";
                String txt = "";

                for (int i = 0; i < aux.length(); i++) {
                    char c = aux.charAt(i);
                    if (Character.isDigit(c)) {
                        n += c;
                    } else {
                        txt += c;
                    }
                }
                if (n != "") {

                    LTkns.add(new TokenB(n, col, fil, 100, "Numero"));
                }
                if (txt != "") {
                    if (txt.contains("<") || txt.contains(">")) {
                        LTkns.add(new TokenB(txt, col, fil, 400, "Etiqueta no identificada"));
                    } else {
                        LTkns.add(new TokenB(txt, col, fil, 200, "String"));
                    }
                }
            } else {
                u = aux.charAt(0);
                if (Character.isDigit(u)) {
                    LTkns.add(new TokenB(aux, col, fil, 100, "Numero"));
                } else {
                    LTkns.add(new TokenB(aux, col, fil, 200, "String"));
                }
            }

        } else {
            if (v == true) {
                v = false;
            } else {
                v = false;
                aux = "";
            }
        }
    }

    private void R(String h) {
        if (h.charAt(cont) == '\n') {
            if (aux != "") {
                R_find(aux, col, fil);
                //System.out.println("WORD " + aux);
                aux = "";
            }
            fil++;
            col = 0;
        }

        if (h.charAt(cont) == ' ') {
            if (aux != "") {
                //System.out.println("WORD espacio " + aux);
                R_find(aux, col, fil);
                aux = "";
            }
            col++;
        }

        if (h.charAt(cont) != ' ' && h.charAt(cont) != '\n') {
            aux += h.charAt(cont);
            //System.out.println("CONTEO "+aux);
        } else {

        }
        if (cont >= h.length() - 1) {

        } else {
            cont++;
            col++;
            R(h);
        }
    }

    private boolean R_lex(ArrayList<TokenB> lex) {
        boolean isCorrect = false;

        if (isError > lex.size() - 1) {
            op = 404;
        } else {
            if (lexer == 99) {
                op = 404;
            } else {
                op = lex.get(isError).getCodigo();
            }
        }
        /*
        try {
            if (lex.get(isError).getCodigo() >= 8 && lex.get(isError).getCodigo() <= 37 || lex.get(isError).getCodigo() == 200) {
                op = 100;
                lexer = 6;
                
            }

            if (lex.get(isError).getCodigo() == 7) {
                lexer = 6;
            }
        } catch (Exception e) {

        }*/

        switch (op) {
            case 404:
                isCorrect = true;
                System.out.println("SINTÁXIS CORRECTA");
                contador = true;
                break;
            case 1:
                if (isSTART == 0 && lexer == 0) {
                    System.out.println(
                            "<!DOCTYPE> correct");
                    lexer = 1;
                    isSTART = 5;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("Se espera <!DOCTYPE>");
                }
                break;
            case 2:
                if (isSTART != 0 && lexer == 1) {
                    System.out.println("<html> correct");
                    lexer = 2;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("Se espera <html>");
                }
                break;
            case 4:

                if (isSTART != 0 && (lexer == 2)) {
                    System.out.println("<head> correct");
                    lexer = 4;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <head>");
                }
                break;
            case 5:
                if (isSTART != 0 && (lexer == 4 || lexer == 39)) {
                    System.out.println("</head> correct");
                    lexer = 5;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </head>");
                }
                break;
            case 200:
                if (isSTART != 0 && (lexer == 100)
                        || lexer == 200 // si mismo
                        || lexer == 8 // <p>
                        || (lexer == 10 || lexer == 12 || lexer == 14 || lexer == 16 || lexer == 18 || lexer == 20)
                        // <titulos> 
                        || (lexer == 24 || lexer == 26)
                        // <tr>, <td> 
                        || lexer == 30 // <a>
                        || lexer == 29 || lexer == 28// <footer>
                        || (lexer == 32 || lexer == 34)
                        // <ul>, <li>
                        || lexer == 36 // div
                        || lexer == 38) {

                    System.out.println("String correct");
                    lexer = 200;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera String");
                }
                break;
            case 6:
                if (isSTART != 0 && (lexer == 5)) {
                    System.out.println("<body> correct");
                    lexer = 6;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <body>");
                }
                break;

            case 7:
                if (isSTART != 0
                        && lexer == 6
                        || // </body>
                        lexer == 9
                        || // </p>
                        (lexer == 11 || lexer == 13 || lexer == 15 || lexer == 17 || lexer == 19 || lexer == 21)
                        || // </titulos> 
                        lexer == 32
                        || // </table>
                        lexer == 29
                        || // </footer>
                        lexer == 33
                        || // </ul>
                        lexer == 37 // </div>
                        ) {
                    System.out.println("</body> correct");
                    lexer = 7;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </body>");
                }
                break;

            case 8:
                if (isSTART != 0
                        && lexer == 6
                        || (lexer == 8 || lexer == 9)
                        || // si mismo
                        (lexer == 36 || lexer == 37)
                        || //div
                        (lexer == 10 || lexer == 11)
                        || //titulos
                        (lexer >= 24 || lexer <= 27)
                        || //table
                        (lexer >= 28 || lexer <= 29)
                        || //footer
                        (lexer >= 30 || lexer <= 31)
                        || // a
                        (lexer >= 34 || lexer <= 35) // li 
                        ) {
                    System.out.println("<p> correct");
                    lexer = 8;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <p>");
                }
                break;

            case 9:
                System.out.println("GET " + lexer);
                if (isSTART != 0
                        && lexer == 8 || lexer == 200 || lexer == 100 // texto
                        // </p>
                        ) {
                    System.out.println("</p> correct");
                    lexer = 9;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </p>");
                }
                break;

            // h1, /h1
            case 10:
                if (isSTART != 0
                        && lexer == 6
                        || (lexer == 8 || lexer == 9)
                        || // si mismo
                        (lexer == 36 || lexer == 37)
                        || //div
                        (lexer >= 24 || lexer <= 27)
                        || //table
                        (lexer >= 28 || lexer <= 29)
                        || //footer
                        (lexer >= 30 || lexer <= 31)
                        || // a
                        (lexer >= 34 || lexer <= 35) // li 
                        ) {
                    System.out.println("<h1> correct");
                    lexer = 10;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <h1>");
                }
                break;

            case 11:
                if (isSTART != 0
                        || lexer == 200
                        || lexer == 100
                        || // texto
                        lexer == 8
                        || // </p>
                        (lexer == 11)
                        || // </h1> 
                        lexer == 27
                        || // </table>
                        lexer == 29
                        || // </footer>
                        (lexer == 35)
                        || // </li>
                        lexer == 37 // </div>
                        ) {
                    System.out.println("</h1> correct");
                    lexer = 11;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </h1>");
                }
                break;

            //
            // table, /table 
            case 22:
                if (isSTART != 0
                        && lexer == 6
                        || (lexer == 23) // si mismo
                        || (lexer == 36 || lexer == 37)
                        || //div
                        (lexer >= 10 || lexer <= 21)
                        || //titulos
                        (lexer >= 28 || lexer <= 29)
                        || //footer
                        (lexer >= 30 || lexer <= 31)
                        || // a
                        (lexer >= 32 || lexer <= 35) // li 
                        || (lexer == 7
                        || (lexer == 8 || lexer == 9) // p
                        )) {
                    System.out.println("<table> correct");
                    lexer = 22;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <table>");
                }
                break;

            case 23:
                //System.out.println("GET "+lexer);
                if (isSTART != 0
                        && (lexer == 25) // si mismo
                        ) {
                    System.out.println("</table> correct");
                    lexer = 23;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </table>");
                }
                break;

            //
            //tr, /tr
            case 24:
                if (isSTART != 0
                        && lexer == 22 // cierre </table>
                        ) {
                    System.out.println("<tr> correct");
                    lexer = 24;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <tr>");
                }
                break;

            case 25:
                if (isSTART != 0
                        && lexer == 27 //cierre </td>
                        || lexer == 200
                        || lexer == 100
                        || lexer == 25 // si mismo
                        ) {
                    System.out.println("</tr> correct");
                    lexer = 25;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </tr>");
                }
                break;

            //
            // td, /tc
            case 26:
                if (isSTART != 0
                        && lexer == 24 || lexer == 27 // cierre </table>
                        ) {
                    System.out.println("<td> correct");
                    lexer = 26;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <td>");
                }
                break;

            case 27:
                if (isSTART != 0
                        && lexer == 26 //cierre </tr>
                        || lexer == 200 // string 
                        || lexer == 100 // numero
                        ) {
                    System.out.println("</td> correct");
                    lexer = 27;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </td>");
                }
                break;

            //
            case 28:
                if (isSTART != 0
                        && lexer == 6
                        || (lexer == 36 || lexer == 37)
                        || // si mismo
                        (lexer == 8 || lexer == 9)
                        || //div
                        (lexer >= 10 || lexer <= 21)
                        || //titulos
                        (lexer >= 22 || lexer <= 27)
                        || //table
                        (lexer >= 28 || lexer <= 29)
                        || //footer
                        (lexer >= 30 || lexer <= 31)
                        || // a
                        (lexer >= 32 || lexer <= 35) // li 
                        ) {
                    System.out.println("<footer> correct");
                    lexer = 28;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <footer>");
                }
                break;

            case 29:
                if (isSTART != 0
                        && lexer == 37
                        || // <div>
                        lexer == 9
                        || // </p>
                        (lexer == 11 || lexer == 13 || lexer == 15 || lexer == 17 || lexer == 19 || lexer == 21)
                        || // </titulos> 
                        (lexer == 25 || lexer == 27 || lexer == 32)
                        || // </table>
                        lexer == 28
                        || // </footer>
                        lexer == 33 || lexer == 35 // </ul>, </li>
                        || lexer == 200) {
                    System.out.println("</footer> correct");
                    lexer = 29;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </footer>");
                }
                break;

            case 36:
                if (isSTART != 0
                        && lexer == 6
                        || (lexer == 36 || lexer == 37)
                        || // si mismo
                        (lexer == 8 || lexer == 9)
                        || //div
                        (lexer >= 10 || lexer <= 21)
                        || //titulos
                        (lexer >= 22 || lexer <= 27)
                        || //table
                        (lexer >= 28 || lexer <= 29)
                        || //footer
                        (lexer >= 30 || lexer <= 31)
                        || // a
                        (lexer >= 32 || lexer <= 35) // li 
                        ) {
                    System.out.println("<div> correct");
                    lexer = 36;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <div>");
                }
                break;

            case 30:
                if (isSTART != 0
                        && lexer == 6
                        || // body
                        (lexer == 36 || lexer == 37)
                        || //div
                        (lexer >= 10 || lexer <= 21)
                        || //titulos
                        (lexer >= 24 || lexer <= 27)
                        || //table
                        (lexer >= 28 || lexer <= 29)
                        || //footer
                        (lexer >= 30 || lexer <= 31)
                        || // a
                        (lexer >= 34 || lexer <= 35) // li 
                        ) {
                    System.out.println("<a> correct");
                    lexer = 30;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <a>");
                }
                break;

            case 31:
                if (isSTART != 0
                        || lexer == 200
                        || // texto
                        lexer == 30
                        || // </a>
                        (lexer == 10 || lexer == 13 || lexer == 15 || lexer == 17 || lexer == 19 || lexer == 21)
                        || // </titulos> 
                        (lexer >= 22 || lexer <= 27)
                        || // </table>
                        lexer == 29
                        || // </footer>
                        lexer == 33
                        || // </ul>
                        lexer == 37 // </div>
                        ) {
                    System.out.println("</a> correct");
                    lexer = 31;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </a>");
                }
                break;

            case 37:
                if (isSTART != 0
                        && lexer == 36
                        || // <div>
                        lexer == 9
                        || // </p>
                        (lexer == 11 || lexer == 13 || lexer == 15 || lexer == 17 || lexer == 19 || lexer == 21)
                        || // </titulos> 
                        (lexer == 22 || lexer == 23 || lexer == 25 || lexer == 27 || lexer == 32)
                        || // </table>
                        lexer == 29
                        || // </footer>
                        lexer == 33 || lexer == 35
                        ||// </ul>, </li>
                        lexer == 31
                        || lexer == 43 || lexer == 41) {
                    System.out.println("</div> correct");
                    lexer = 37;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </div>");
                }
                break;

            case 38:
                if (isSTART != 0 && (lexer == 4)) {
                    System.out.println("<title> correct");
                    lexer = 38;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <title>");
                }
                break;
            case 39:
                if (isSTART != 0 && lexer == 38 || lexer == 200) {
                    System.out.println("</title> correct");
                    lexer = 39;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </title>");
                }
                break;

            // ul, /ul
            case 32:
                if (isSTART != 0
                        && lexer == 6
                        || lexer == 9
                        || lexer == 11
                        || lexer == 26
                        || lexer == 28
                        || lexer == 36
                        || lexer == 42
                        || lexer == 33) {
                    System.out.println("<ul> correct");
                    lexer = 32;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <ul>");
                }
                break;

            case 33:
                if (isSTART != 0
                        && lexer == 32
                        || lexer == 35) {
                    System.out.println("</ul> correct");
                    lexer = 33;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </ul>");
                }
                break;

            // li,/li
            case 34:
                if (isSTART != 0
                        && lexer == 32
                        || lexer == 40
                        || lexer == 35) {
                    System.out.println("<li> correct");
                    lexer = 34;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <li>");
                }
                break;

            case 35:
                if (isSTART != 0
                        && lexer == 34
                        || lexer == 200
                        || lexer == 31) {
                    System.out.println("</li> correct");
                    lexer = 35;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </li>");
                }
                break;

//
            // ol,/ol
            case 40:
                if (isSTART != 0
                        && lexer == 6
                        || lexer == 9
                        || lexer == 11
                        || lexer == 26
                        || lexer == 28
                        || lexer == 36) {
                    System.out.println("<ol> correct");
                    lexer = 40;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <ol>");
                }
                break;

            case 41:
                if (isSTART != 0
                        && lexer == 35
                        || lexer == 41) {
                    System.out.println("</ol> correct");
                    lexer = 41;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </ol>");
                }
                break;

//
            // nav,/nav
            case 42:
                if (isSTART != 0
                        && lexer == 6
                        || lexer == 9
                        || lexer == 11
                        || lexer == 26
                        || lexer == 28
                        || lexer == 36) {
                    System.out.println("<nav> correct");
                    lexer = 42;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera <nav>");
                }
                break;

            case 43:
                if (isSTART != 0
                        && lexer == 42
                        || lexer == 41
                        || lexer == 33) {
                    System.out.println("</nav> correct");
                    lexer = 43;
                    isError++;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </nav>");
                }
                break;

//
            /*case 100:
                System.out.println("Recibo " + op + " y lexer en" + lexer);
                if (isSTART != 0 && (lexer == 6)) {
                    System.out.println(lex.get(isError).getToken() + " correct");
                    isError++;
                    lexer2 = lex.get(isError).getCodigo();
                    System.out.println("LEXER II "+lexer2);
                    R_lex(lex);
                } else {
                    System.out.println("se espera " + lex.get(isError).getToken());
                }
                break;
             */
            case 3:
                if (isSTART != 0 && (lexer == 7)) {
                    System.out.println("</html> correct");
                    lexer = 99;
                    isError++;
                    isCorrect = true;
                    R_lex(lex);
                } else {
                    System.out.println("se espera </html>");
                }
                break;
            case 99:
                if (isSTART != 0 && (lexer == 3)) {
                    System.out.println("fin código");
                    lexer = 99;
                    isError++;

                    R_lex(lex);
                } else {
                    System.out.println("Error");
                }
                break;
        }

        return isCorrect;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        codigoL = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        revisar = new javax.swing.JButton();
        revisar1 = new javax.swing.JButton();
        revisar2 = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        desktop = new javax.swing.JInternalFrame();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        codigoL.setBackground(new java.awt.Color(40, 39, 39));
        codigoL.setColumns(20);
        codigoL.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        codigoL.setForeground(new java.awt.Color(204, 204, 204));
        codigoL.setRows(5);
        codigoL.setText("<!DOCTYPE>\n<html>\n<head>\n<title> Doctors Boostrap </title>\n</head>\n<body>\n<div>\n<nav>\n<ul>\n<li> <a> Menu </a> </li>\n<li> <a> Delivery </a> </li>\n<li> <a> Receta Boostrap </a> </li>\n</ul>\n</nav>\n</div>\n\n<div>\n<p> Tacos Doctors Boostrap </p>\n<ol> \n<li> Tacos </li>\n<li> Gringas </li>\n<li> Tortas </li>\n</ol>\n</div>\n\n<div>\n<h1> Menu </h1>\n<table>\n<tr>\n<td> Pastor </td>\n<td> Bistek </td>\n<td> Bootstrap </td>\n</tr>\n</table>\n</div>\n\n</body>\n</html>");
        jScrollPane1.setViewportView(codigoL);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editor HTML");

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        revisar.setBackground(new java.awt.Color(34, 34, 34));
        revisar.setFont(new java.awt.Font("Batang", 1, 11)); // NOI18N
        revisar.setForeground(new java.awt.Color(255, 255, 255));
        revisar.setText("Léxico");
        revisar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        revisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revisarActionPerformed(evt);
            }
        });
        jPanel2.add(revisar);

        revisar1.setBackground(new java.awt.Color(34, 34, 34));
        revisar1.setFont(new java.awt.Font("Batang", 1, 11)); // NOI18N
        revisar1.setForeground(new java.awt.Color(255, 255, 255));
        revisar1.setText("Sintáctico");
        revisar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        revisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revisar1ActionPerformed(evt);
            }
        });
        jPanel2.add(revisar1);

        revisar2.setBackground(new java.awt.Color(34, 34, 34));
        revisar2.setFont(new java.awt.Font("Batang", 1, 11)); // NOI18N
        revisar2.setForeground(new java.awt.Color(255, 255, 255));
        revisar2.setText("SEM");
        revisar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        revisar2.setEnabled(false);
        revisar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revisar2ActionPerformed(evt);
            }
        });
        jPanel2.add(revisar2);

        limpiar.setBackground(new java.awt.Color(34, 34, 34));
        limpiar.setFont(new java.awt.Font("Batang", 1, 11)); // NOI18N
        limpiar.setForeground(new java.awt.Color(255, 255, 255));
        limpiar.setText("Limpiar");
        limpiar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        limpiar.setEnabled(false);
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });
        jPanel2.add(limpiar);

        desktop.setBackground(new java.awt.Color(34, 34, 34));
        desktop.setMaximizable(true);
        desktop.setResizable(true);
        desktop.setTitle("Tkns");
        desktop.setVisible(true);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop.getContentPane());
        desktop.getContentPane().setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/palabra_analizador/img/Plaza Sésamo - Sésamo La computadora de Beto [EOpJXJ_SeAs - 1034x582 - 0m02s].png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Analizador HTML");
        jPanel3.add(jLabel2);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/palabra_analizador/img/xmissions.jpg"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/palabra_analizador/img/pastor.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(desktop)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(desktop)))
                .addGap(15, 15, 15))
        );

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
TablaTokens m = new TablaTokens();
    int conV = 0;
    private void revisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revisarActionPerformed

        var = "";
        ope = "";
        num = "";
        txt = "";
        aux = "";
        auxNumero = "";
        col = 1;
        fil = 1;
        col_aux = 0;
        cont = 0;
        isSTART = 0;
        isError = 0;
        op = 0;
        lexer = 0;
        c = 0;
        lexer2 = 0;
        boolean v = false;
        Tkns1Lapse = null;
        LTkns.clear();
        STkns.clear();
        txt = codigoL.getText() + " ";

        R(txt);

        m.ASIGNA_MODELO(LTkns);

        if (conV == 0) {
            desktop.add(m);
            conV++;
        } else {
            conV++;
        }
        m.show();
    }//GEN-LAST:event_revisarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        codigoL.setText("");
        //respuesta.setText("...");
        LTkns.clear();
    }//GEN-LAST:event_limpiarActionPerformed
    SintactOK k = new SintactOK();
    int contR = 0;
    private void revisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revisar1ActionPerformed
        R_lex(LTkns);
        if (contador == true) {

            if (contR == 0) {
                k.MSJ.setText("SINTAXIS CORRECTA");
                desktop.add(k);
                contR++;
            } else {
                k.MSJ.setText("SINTAXIS CORRECTA");
                contR++;
            }
            k.show();
        } else {
            if(contR == 0) {
                k.MSJ.setText("SINTAXIS INCORRECTA");
                desktop.add(k);
                contR++;
            }else {
                k.MSJ.setText("SINTAXIS INCORRECTA");
                contR++;
            }
            k.show();
        }
    }//GEN-LAST:event_revisar1ActionPerformed

    private void revisar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revisar2ActionPerformed


    }//GEN-LAST:event_revisar2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea codigoL;
    private static javax.swing.JInternalFrame desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton revisar;
    private javax.swing.JButton revisar1;
    private javax.swing.JButton revisar2;
    // End of variables declaration//GEN-END:variables
}
