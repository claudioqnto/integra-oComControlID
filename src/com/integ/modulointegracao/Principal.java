package com.integ.modulointegracao;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.integ.modulointegracao.exception.InvalidSessionException;
import com.integ.modulointegracao.util.SetupDataDb;

/**
 *
 * @author CLAUDIO 21 979059570
 */
public class Principal {

    private int ultimoRegistro;
    private AccessReader al;
    private SettingsReaderBiometric srb;
    private ScheduledExecutorService leitorService;

    public Principal() {
        //SetupDataDb.apagarLog();
        this.al = new AccessReader();
        this.srb = new SettingsReaderBiometric();
        //this.al.logar();
        this.al.checkAccess();
        this.ultimoRegistro = this.al.retornoCounts();
        this.startReader();
    }

    /**
     * startReader()inicia a aplicação, permanece verificando o status do contador, readLog() a cada 2s.
     * Atualiza a hora do leitor biometrico, por meio do método updateHour() a cada 3 horas.      
     */
    private void startReader() {
        this.leitorService = Executors.newSingleThreadScheduledExecutor();
        this.leitorService.scheduleAtFixedRate(this.readLog(), 0, 1500, TimeUnit.MILLISECONDS);
        this.leitorService.scheduleAtFixedRate(this.updateHour(), 0, 3, TimeUnit.HOURS);
    }

    private void stopReader() {
        this.leitorService.shutdownNow();
    }

    /**
     * readLog() verifica se a sessão é válida(true) estabelece comunicação como o leitor biométrico
     * executando um contador e retornando a matricula do usuário fazendo uso do Keyboard para simular teclado.
     */
    private Runnable readLog() {
        return (() -> {
            Keyboard kb = null;
            try {
                kb = new Keyboard();
            } catch (AWTException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (al.validSession()) {
                biometricsListen(kb);
            } else {
                al.checkAccess();
                biometricsListen(kb);

            }
        });
    }

    private void biometricsListen(Keyboard kb) {
        int registroAtual = al.retornoCounts();
        if (ultimoRegistro < registroAtual) {
            al.listarUltimoRegistro();
            ultimoRegistro = registroAtual;
        //System.out.println(al.getEntrega());
            if (al.getEntrega() != null) {
                kb.type(al.getEntrega());
            }
        }
    }

    /**
     * UpdateHour() verifica se sessão é válida(true) e atualiza a hora do leitor biométrico de acordo com o horário
     * da máquina que esta rodando a aplicação.
     */
    private Runnable updateHour() {
        return (() -> {
            if (al.validSession()) {
                System.out.println("atualizando Data/Hora...");
                try {
                    SettingsReaderBiometric s = new SettingsReaderBiometric();
                    s.setupClock(al);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidSessionException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Principal p = new Principal();
    }
}
