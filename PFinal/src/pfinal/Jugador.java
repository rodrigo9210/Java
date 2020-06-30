/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal;

/**
 *
 * @author Rodrigo
 */
public class Jugador implements Comparable<Jugador>{
    
    private String nombre;
    private int movimientos;
    private String nivel;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.movimientos = 0;
    }

    public Jugador(String nombre, String nivel, int movimientos) {
        this.nombre = nombre;
        this.movimientos = movimientos;
        this.nivel = nivel;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }
    
    public void sumaMovimientos(){
        movimientos++;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    

    @Override
    public String toString() {
        return nombre + " " + nivel + " " +movimientos;
    }

    @Override
    public int compareTo(Jugador otro) {
        return Integer.compare(this.movimientos, otro.movimientos);
    }
    
    
}
