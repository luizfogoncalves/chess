/*
 * Time.java
 *
 * Created on 15 de Novembro de 2006, 14:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package model.utils;

public final class Time {
    
    public int hour;    
    public int minute;    
    public int second;
    public int totalSec;
    
    /** Creates a new instance of Time */
    public Time() {
        this(0,0,0);
    }
    
    public Time(int hour, int minute, int second) {
        /* atribui a hora em termos de segundos */
        this.setTotalSec(second + (minute*60) + (hour*60*60));
        this.updateTime(); // atualiza o tempo (atribui hora, min, sec) com base em totalSec        
    }

    
    /* decrementa os segundos (faz o timer funcionar) */
    public void decSecond() {
        this.setTotalSec(this.totalSec-1);
        this.updateTime();
    }
    
    public void decMinute() {
        this.setTotalSec(this.totalSec-60);
        this.updateTime();        
    }
    
    public void decHour() {
        this.setTotalSec(this.totalSec-3600);
        this.updateTime();        
    }
    
    /* atualiza o timer */
    public void updateTime() {
        this.second = this.getTotalSec()%60;
        this.minute = (int) (this.getTotalSec()/60);
        this.hour = (int) (this.getMinute()/60);       
    }

    public boolean isOver() {
        return this.totalSec == 0;
    }
    
    /* getters and setters */
    /* cotem o tempo total em segundos */
    private int getTotalSec() {
        return totalSec;
    }

    private void setTotalSec(int totalSec) {
        this.totalSec = totalSec;
    }    
    
    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getSecond() {
        return this.second;
    }

    public void setHour(int hours) {
        this.hour = hours;
    }

    public void setMinute(int minutes) {
        this.minute = minutes;
    }

    public void setSecond(int seconds) {
        this.second = seconds;
    }
    
    public String toString(){
               
        String auxHora;
        String auxMinuto;
        String auxSegundo;
        
        if (this.getHour() < 10){
            auxHora = "0"+this.getHour();
        }else{
            auxHora = ""+this.getHour();
        }
        
        if (this.getMinute() < 10){
            auxMinuto = "0"+this.getMinute();
        }else{
            auxMinuto = ""+this.getMinute();
        }
        
        if (this.getSecond() < 10){
            auxSegundo = "0"+this.getSecond();
        }else{
            auxSegundo = ""+this.getSecond();
        }       
        return auxHora+":"+auxMinuto+":"+auxSegundo;          
    }    
}
